package com.example.supabase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthRequest(
  val email: String,
  val password: String,
  val data: Map<String, String>? = null
)

@JsonClass(generateAdapter = true)
data class AuthResponse(
  @Json(name = "access_token") val accessToken: String? = null,
  @Json(name = "refresh_token") val refreshToken: String? = null,
  @Json(name = "user") val user: AuthUser? = null,
  @Json(name = "msg") val msg: String? = null,
  @Json(name = "error_description") val errorDescription: String? = null
)

@JsonClass(generateAdapter = true)
data class AuthUser(
  val id: String,
  val email: String? = null,
  @Json(name = "app_metadata") val appMetadata: Map<String, Any?>? = null,
  @Json(name = "user_metadata") val userMetadata: Map<String, Any?>? = null
)

data class UserSession(
  val accessToken: String,
  val refreshToken: String,
  val userId: String,
  val email: String,
  val role: String, // "admin" or "authenticated" (normal user)
  val displayName: String
)

@JsonClass(generateAdapter = true)
data class ProfileDto(
  val id: String,
  @Json(name = "display_name") val displayName: String,
  @Json(name = "created_at") val createdAt: String? = null
)

@JsonClass(generateAdapter = true)
data class CycleEntryDto(
  val id: String? = null,
  @Json(name = "user_id") val userId: String,
  @Json(name = "period_start_date") val periodStartDate: String,
  @Json(name = "period_end_date") val periodEndDate: String? = null,
  @Json(name = "flow_level") val flowLevel: String? = null,
  @Json(name = "cramp_intensity") val crampIntensity: String? = null,
  val notes: String? = null,
  @Json(name = "created_at") val createdAt: String? = null
)

@JsonClass(generateAdapter = true)
data class DailyCheckinDto(
  val id: String? = null,
  @Json(name = "user_id") val userId: String,
  @Json(name = "checkin_date") val checkinDate: String,
  val notes: String? = null,
  val intensity: Int? = null,
  @Json(name = "share_with_partner") val shareWithPartner: Boolean = false,
  @Json(name = "created_at") val createdAt: String? = null
)

@JsonClass(generateAdapter = true)
data class CheckinSelectionDto(
  @Json(name = "checkin_id") val checkinId: String,
  @Json(name = "option_id") val optionId: String
)

@JsonClass(generateAdapter = true)
data class FoodRequestDto(
  val id: String? = null,
  @Json(name = "reference_id") val referenceId: String,
  @Json(name = "user_id") val userId: String,
  val status: String,
  val notes: String? = null,
  val timing: String? = null,
  @Json(name = "created_at") val createdAt: String? = null
)

@JsonClass(generateAdapter = true)
data class FoodRequestItemDto(
  val id: String? = null,
  @Json(name = "food_request_id") val foodRequestId: String,
  @Json(name = "food_item_id") val foodItemId: String,
  val quantity: Int,
  @Json(name = "item_notes") val itemNotes: String? = null
)

@JsonClass(generateAdapter = true)
data class BellRequestDto(
  val id: String? = null,
  @Json(name = "user_id") val userId: String,
  val message: String? = null,
  val urgency: String = "Warm Support",
  val status: String = "pending",
  @Json(name = "created_at") val createdAt: String? = null,
  @Json(name = "acknowledged_at") val acknowledgedAt: String? = null,
  @Json(name = "resolved_at") val resolvedAt: String? = null
)

@JsonClass(generateAdapter = true)
data class AdminNotificationDto(
  val id: String,
  @Json(name = "event_type") val eventType: String,
  @Json(name = "food_request_id") val foodRequestId: String? = null,
  @Json(name = "bell_request_id") val bellRequestId: String? = null,
  val title: String,
  val message: String,
  @Json(name = "created_at") val createdAt: String,
  @Json(name = "read_at") val readAt: String? = null
)
