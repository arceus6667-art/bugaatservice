package com.example.supabase

import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

/**
 * Authenticated REST client for the Act Service Supabase project.
 * NEVER embed passwords or service_role keys in this application.
 * Passwords are sent only to Supabase Auth over HTTPS.
 */
object ActServiceApi {
  private val client = OkHttpClient()
  private val json = "application/json; charset=utf-8".toMediaType()
  private val url: String get() = BuildConfig.SUPABASE_URL.trimEnd('/')
  private val key: String get() = BuildConfig.SUPABASE_ANON_KEY
  private var token: String? = null
  private var refreshToken: String? = null
  var userId: String? = null
    private set
  var isAdmin: Boolean = false
    private set

  fun configured(): Boolean =
    url.startsWith("https://") && ".supabase.co" in url &&
      key.isNotBlank() && !key.startsWith("your-")

  private suspend fun call(path: String, method: String = "GET", body: String? = null,
                           authenticated: Boolean = true): String = withContext(Dispatchers.IO) {
    check(configured()) { "Supabase URL and publishable key must be configured." }
    val builder = Request.Builder().url(url + path)
      .header("apikey", key)
      .header("Accept", "application/json")
      .header("Content-Type", "application/json")
    if (authenticated) builder.header("Authorization", "Bearer " + (token ?: error("Please sign in.")))
    else builder.header("Authorization", "Bearer $key")
    val requestBody = body?.toRequestBody(json)
    builder.method(method, if (method == "GET") null else requestBody)
    client.newCall(builder.build()).execute().use { response ->
      val result = response.body?.string().orEmpty()
      if (!response.isSuccessful) {
        val message = runCatching { JSONObject(result).optString("msg")
          .ifBlank { JSONObject(result).optString("message") } }.getOrNull().orEmpty()
        error(if (message.isBlank()) "Server error (${response.code})" else message)
      }
      result
    }
  }

  /**
   * User-facing IDs remain bogi and buga; internal email identity is implementation-only.
   * These emails must be provisioned once using Supabase Auth Admin API / Dashboard.
   */
  suspend fun signIn(loginId: String, password: String): Boolean {
    val alias = when (loginId.trim().lowercase()) {
      "bogi" -> "bogi@actservice.example"
      "buga" -> "buga@actservice.example"
      else -> error("Incorrect login ID or password.")
    }
    val response = JSONObject(call("/auth/v1/token?grant_type=password", "POST",
      JSONObject().put("email", alias).put("password", password).toString(), false))
    token = response.getString("access_token")
    refreshToken = response.optString("refresh_token")
    val user = response.getJSONObject("user")
    userId = user.getString("id")
    val role = user.optJSONObject("app_metadata")?.optString("role")
    isAdmin = role == "admin"
    // Never grant admin by username alone; authorization is verified by Supabase JWT / RLS.
    if (loginId.trim().lowercase() == "buga" && !isAdmin) {
      signOut()
      error("This account is not authorized as an administrator.")
    }
    if (loginId.trim().lowercase() == "bogi" && isAdmin) {
      signOut()
      error("Administrator must use the authorized administrator login ID.")
    }
    return isAdmin
  }

  fun signOut() {
    token = null
    refreshToken = null
    userId = null
    isAdmin = false
  }

  suspend fun select(table: String, query: String = "?select=*"): JSONArray =
    JSONArray(call("/rest/v1/$table$query"))

  suspend fun insert(table: String, data: JSONObject): JSONObject {
    val result = call("/rest/v1/$table", "POST", data.toString())
    return runCatching { JSONObject(result) }.getOrElse { JSONObject() }
  }

  suspend fun createBellRequest(message: String) {
    val data = JSONObject().put("user_id", userId).put("message", message)
    call("/rest/v1/bell_requests", "POST", data.toString())
  }

  suspend fun createFoodRequest(idsAndQuantities: List<Pair<String, Int>>, notes: String): String {
    val items = JSONArray()
    idsAndQuantities.forEach { (id, qty) ->
      items.put(JSONObject().put("food_item_id", id).put("quantity", qty))
    }
    val payload = JSONObject().put("p_items", items).put("p_notes", notes)
    val raw = call("/rest/v1/rpc/create_food_request", "POST", payload.toString())
    return raw.trim().trim('"')
  }

  suspend fun saveCycle(start: String, end: String?, flow: String?, notes: String) {
    val payload = JSONObject().put("user_id", userId)
      .put("period_start_date", start)
      .put("period_end_date", end ?: JSONObject.NULL)
      .put("flow_level", flow?.lowercase() ?: JSONObject.NULL)
      .put("notes", notes)
    call("/rest/v1/cycle_entries", "POST", payload.toString())
  }

  suspend fun saveCheckin(date: String, note: String, intensity: Int): String {
    val payload = JSONObject().put("user_id", userId)
      .put("checkin_date", date).put("notes", note).put("intensity", intensity)
    // Prefer return=representation so the generated UUID can be used for selections.
    val builder = Request.Builder().url(url + "/rest/v1/daily_checkins")
      .header("apikey", key).header("Authorization", "Bearer " + (token ?: error("Sign in")))
      .header("Content-Type", "application/json")
      .header("Prefer", "return=representation")
      .post(payload.toString().toRequestBody(json))
    return withContext(Dispatchers.IO) {
      client.newCall(builder.build()).execute().use { response ->
        val str = response.body?.string().orEmpty()
        if (!response.isSuccessful) error("Failed to save check-in: ${response.code}")
        JSONArray(str).getJSONObject(0).getString("id")
      }
    }
  }

  suspend fun saveCheckinSelections(checkinId: String, optionIds: List<String>) {
    if (optionIds.isEmpty()) return
    val a = JSONArray()
    optionIds.forEach { a.put(JSONObject().put("checkin_id", checkinId).put("option_id", it)) }
    call("/rest/v1/daily_checkin_selections", "POST", a.toString())
  }
  suspend fun createFoodRequestByNames(items: List<Pair<String, Int>>, notes: String): String {
    val available = select("food_items", "?select=id,name&is_active=eq.true&limit=500")
    val names = mutableMapOf<String,String>()
    for (i in 0 until available.length()) {
      val row = available.getJSONObject(i)
      names[row.getString("name")] = row.getString("id")
    }
    return createFoodRequest(items.map { (name,qty) ->
      Pair(names[name] ?: error("Food catalog item unavailable: $name"), qty)
    }, notes)
  }

  suspend fun saveCheckinSelectionsByLabels(checkinId: String, labels: List<String>) {
    if (labels.isEmpty()) return
    val options = select("wellbeing_options", "?select=id,label&is_active=eq.true&limit=500")
    val names = mutableMapOf<String,String>()
    for (i in 0 until options.length()) {
      val option = options.getJSONObject(i)
      names[option.getString("label")] = option.getString("id")
    }
    saveCheckinSelections(checkinId, labels.map { label ->
      names[label] ?: error("Wellbeing option not available: $label")
    })
  }

}
