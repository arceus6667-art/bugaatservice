package com.example.model

import com.example.supabase.ActServiceApi
import org.json.JSONArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.data.FoodCatalog
import com.example.data.MoodCatalog

class AppStateManager {
  private val backendScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

  var isLoggedIn by mutableStateOf(false)
  var currentUserRole by mutableStateOf(UserRole.USER)

  // Current active navigation screen
  var currentScreen by mutableStateOf("home") // "home", "cycle", "feelings", "food", "ring_bell", "admin", "self_care", "profile"

  // Affection micro-interactions
  var hugStatusText by mutableStateOf("No messages yet")
  var hasHuggedBack by mutableStateOf(false)

  // Cycle tracking state
  var cycleState by mutableStateOf(CycleState())

  // Moods & symptoms state
  val selectedTagIds = mutableStateListOf<String>()
  var discomfortIntensity by mutableIntStateOf(1)
  var personalMoodNote by mutableStateOf("")
  var shareMoodWithBuga by mutableStateOf(false) // Private by default!

  // Food Ordering state (Strictly NO PRICES anywhere, starts clean with no preselected items)
  val cartItems = mutableStateListOf<CartItem>()
  val favoriteDishIds = mutableStateListOf<String>()
  val activeFoodOrders = mutableStateListOf<FoodOrderRequest>()

  // Ring the Bell state
  var activeBellAlert by mutableStateOf<BellAlert?>(null)

  val pastBellHistory = mutableStateListOf<PastCheckin>()

  // Feedback notifications
  var lastConfirmationNotice by mutableStateOf<String?>(null)

  fun toggleFavorite(dishId: String) {
    if (favoriteDishIds.contains(dishId)) {
      favoriteDishIds.remove(dishId)
    } else {
      favoriteDishIds.add(dishId)
    }
  }

  fun addToCart(dish: FoodDish) {
    val existingIndex = cartItems.indexOfFirst { it.dish.id == dish.id }
    if (existingIndex >= 0) {
      val existing = cartItems[existingIndex]
      cartItems[existingIndex] = existing.copy(quantity = existing.quantity + 1)
    } else {
      cartItems.add(CartItem(dish = dish, quantity = 1))
    }
  }

  fun updateCartQuantity(dishId: String, delta: Int) {
    val existingIndex = cartItems.indexOfFirst { it.dish.id == dishId }
    if (existingIndex >= 0) {
      val existing = cartItems[existingIndex]
      val newQty = existing.quantity + delta
      if (newQty <= 0) {
        cartItems.removeAt(existingIndex)
      } else {
        cartItems[existingIndex] = existing.copy(quantity = newQty)
      }
    }
  }

  fun clearCart() {
    cartItems.clear()
  }

  fun submitFoodOrder(customNote: String, timing: String): FoodOrderRequest {
    val newOrder = FoodOrderRequest(
      referenceId = "#BF-${(105..999).random()}",
      timestamp = "Just now",
      items = cartItems.toList(),
      customNote = customNote.ifBlank { "Please make it with extra love ❤️" },
      timing = timing.ifBlank { "ASAP (Craving!)" },
      status = FoodOrderStatus.REQUEST_SUBMITTED
    )
    activeFoodOrders.add(0, newOrder)
    cartItems.clear()
    return newOrder
  }

  fun triggerRingBell(urgency: String, message: String) {
    activeBellAlert = BellAlert(
      id = "bell_${System.currentTimeMillis() % 10000}",
      timestamp = "Just now",
      urgency = urgency,
      message = message.ifBlank { "Need some devoted partner attention ❤️" },
      status = BellStatus.NOTIFICATION_CREATED
    )
  }

  fun cancelBellAlert() {
    activeBellAlert?.let {
      pastBellHistory.add(0, PastCheckin(
        date = "Today",
        time = "Just now",
        title = "Bell Checked In & Resolved",
        description = "Feeling much better and safe together 💕",
        icon = "verified"
      ))
    }
    activeBellAlert = null
  }


  // Admin operations are persisted first; RLS verifies the admin token.
  fun adminAcknowledgeBell() = updateBellStatus("seen")
  fun adminRespondingBell() = updateBellStatus("responding")
  fun adminResolveBell() = updateBellStatus("resolved")

  private fun updateBellStatus(status: String) {
    val id = activeBellAlert?.id ?: return
    backendScope.launch {
      try {
        ActServiceApi.updateRequest("bell_requests", id, status, bell = true)
        refreshFromDatabase()
      } catch (e: Exception) {
        lastConfirmationNotice = e.message ?: "Could not update bell status"
      }
    }
  }

  fun adminUpdateFoodStatus(orderId: String, newStatus: FoodOrderStatus) {
    val status = when(newStatus) {
      FoodOrderStatus.REQUEST_SUBMITTED -> "submitted"
      FoodOrderStatus.NOTIFIED -> "acknowledged"
      FoodOrderStatus.ON_THE_WAY -> "in_progress"
      FoodOrderStatus.COMPLETED -> "completed"
    }
    backendScope.launch {
      try {
        ActServiceApi.updateRequest("food_requests", orderId, status)
        refreshFromDatabase()
      } catch (e: Exception) {
        lastConfirmationNotice = e.message ?: "Could not update food status"
      }
    }
  }

  fun toggleMoodTag(tagId: String) {
    if (selectedTagIds.contains(tagId)) {
      selectedTagIds.remove(tagId)
    } else {
      selectedTagIds.add(tagId)
    }
  }

  fun clearAllMoods() {
    selectedTagIds.clear()
  }

  fun sendHugBack() {
    hasHuggedBack = true
    hugStatusText = "Hug back sent to Buga with kisses! 💖"
  }

  fun setPeriodStartDate(
    year: Int,
    month: Int,
    day: Int,
    duration: Int = cycleState.periodDurationDays,
    cycleLength: Int = cycleState.totalCycleDays,
    flow: String = cycleState.flowLevel,
    cramp: String = cycleState.crampIntensity,
    notes: String = cycleState.wellnessNotes
  ) {
    val cal = java.util.Calendar.getInstance().apply {
      set(year, month, day, 0, 0, 0)
      set(java.util.Calendar.MILLISECOND, 0)
    }
    val todayCal = java.util.Calendar.getInstance().apply {
      set(java.util.Calendar.HOUR_OF_DAY, 0)
      set(java.util.Calendar.MINUTE, 0)
      set(java.util.Calendar.SECOND, 0)
      set(java.util.Calendar.MILLISECOND, 0)
    }

    val periodSet = mutableSetOf<String>()
    val tempCal = cal.clone() as java.util.Calendar
    val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
    val displayFormat = java.text.SimpleDateFormat("MMM d, yyyy", java.util.Locale.US)
    val startStr = dateFormat.format(tempCal.time)
    for (i in 0 until duration) {
      periodSet.add(dateFormat.format(tempCal.time))
      tempCal.add(java.util.Calendar.DAY_OF_YEAR, 1)
    }
    tempCal.add(java.util.Calendar.DAY_OF_YEAR, -1)
    val endStr = dateFormat.format(tempCal.time)

    val diffMillis = todayCal.timeInMillis - cal.timeInMillis
    val diffDays = (diffMillis / (1000 * 60 * 60 * 24)).toInt()

    val currentDay = if (diffDays >= 0) {
      (diffDays % cycleLength) + 1
    } else {
      1
    }

    val daysLeft = if (diffDays >= 0) {
      val daysIntoCycle = diffDays % cycleLength
      val remaining = cycleLength - daysIntoCycle
      if (remaining <= 0) cycleLength else remaining
    } else {
      -diffDays
    }

    val cyclesPassed = if (diffDays >= 0) (diffDays / cycleLength) + 1 else 0
    val nextPeriodCal = (cal.clone() as java.util.Calendar).apply {
      add(java.util.Calendar.DAY_OF_YEAR, cyclesPassed * cycleLength)
    }
    val nextPeriodEndCal = (nextPeriodCal.clone() as java.util.Calendar).apply {
      add(java.util.Calendar.DAY_OF_YEAR, duration - 1)
    }

    val nextDateText = displayFormat.format(nextPeriodCal.time)
    val nextRangeText = "${displayFormat.format(nextPeriodCal.time)} – ${displayFormat.format(nextPeriodEndCal.time)}"

    val phase = when {
      currentDay in 1..duration -> "Menstrual Phase 🌸"
      currentDay in (duration + 1)..11 -> "Follicular Phase 🌱"
      currentDay in 12..16 -> "Ovulation Window ✨"
      else -> "Luteal Phase 🌙"
    }

    cycleState = cycleState.copy(
      hasLoggedPeriod = true,
      currentDayOfCycle = currentDay,
      totalCycleDays = cycleLength,
      periodDurationDays = duration,
      phaseName = phase,
      daysUntilNextPeriod = daysLeft,
      nextPeriodDateText = nextDateText,
      nextPeriodRange = nextRangeText,
      startDate = startStr,
      endDate = endStr,
      periodDays = periodSet,
      flowLevel = flow,
      crampIntensity = cramp,
      wellnessNotes = notes
    )
  }

  fun togglePeriodDay(dateKey: String) {
    val currentSet = cycleState.periodDays.toMutableSet()
    if (currentSet.contains(dateKey)) {
      currentSet.remove(dateKey)
    } else {
      currentSet.add(dateKey)
    }
    cycleState = cycleState.copy(
      hasLoggedPeriod = currentSet.isNotEmpty(),
      periodDays = currentSet
    )
  }

  fun clearCycleDates() {
    cycleState = CycleState()
  }
  // Load persisted records for the signed-in account. Supabase RLS controls visibility.
  suspend fun refreshFromDatabase() {
    if (ActServiceApi.userId == null) return

    val rows = ActServiceApi.loadFoodRequests()
    val itemRows = ActServiceApi.loadFoodRequestItems()
    val catalog = ActServiceApi.select("food_items", "?select=id,name&is_active=eq.true&limit=500")
    val names = mutableMapOf<String, FoodDish>()
    for (i in 0 until catalog.length()) {
      val row = catalog.getJSONObject(i)
      val dish = FoodCatalog.allDishes.firstOrNull { it.name == row.getString("name") }
      if (dish != null) names[row.getString("id")] = dish
    }
    val restoredOrders = mutableListOf<FoodOrderRequest>()
    for (i in 0 until rows.length()) {
      val order = rows.getJSONObject(i)
      val orderId = order.getString("id")
      val cart = mutableListOf<CartItem>()
      for (j in 0 until itemRows.length()) {
        val item = itemRows.getJSONObject(j)
        if (item.getString("food_request_id") == orderId) {
          val dish = names[item.getString("food_item_id")]
          if (dish != null) cart.add(CartItem(dish, item.getInt("quantity")))
        }
      }
      val status = when (order.getString("status")) {
        "acknowledged" -> FoodOrderStatus.NOTIFIED
        "in_progress" -> FoodOrderStatus.ON_THE_WAY
        "completed", "cancelled" -> FoodOrderStatus.COMPLETED
        else -> FoodOrderStatus.REQUEST_SUBMITTED
      }
      restoredOrders.add(FoodOrderRequest(orderId, order.optString("created_at"),
        cart, order.optString("notes"), "Requested", status))
    }
    activeFoodOrders.clear()
    activeFoodOrders.addAll(restoredOrders)

    val bells = ActServiceApi.loadBellRequests()
    var active: BellAlert? = null
    for (i in 0 until bells.length()) {
      val item = bells.getJSONObject(i)
      val status = when(item.getString("status")) {
        "pending" -> BellStatus.NOTIFICATION_CREATED
        "seen" -> BellStatus.SEEN_BY_BUGA
        "responding" -> BellStatus.BUGA_RESPONDING
        else -> null
      }
      if (status != null) {
        active = BellAlert(item.getString("id"), item.optString("created_at"),
          "Warm Support", item.optString("message"), status)
        break
      }
    }
    activeBellAlert = active

    if (currentUserRole == UserRole.USER) {
      val cycleRows = ActServiceApi.loadCycleEntries()
      if (cycleRows.length() > 0) {
        val entry = cycleRows.getJSONObject(0)
        val start = LocalDate.parse(entry.getString("period_start_date"))
        val end = entry.optString("period_end_date").takeIf { it.isNotBlank() && it != "null" }
          ?.let { LocalDate.parse(it) }
        val duration = end?.let { ChronoUnit.DAYS.between(start, it).toInt() + 1 } ?: 5
        setPeriodStartDate(start.year, start.monthValue - 1, start.dayOfMonth,
          duration = duration.coerceIn(1,15),
          flow = entry.optString("flow_level", "medium"),
          notes = entry.optString("notes"))
      }
    }
  }

}
