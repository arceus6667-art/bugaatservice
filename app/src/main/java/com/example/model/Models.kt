package com.example.model

enum class UserRole {
  USER,
  ADMIN
}

enum class MoodCategory(val label: String, val icon: String) {
  ALL("All", "all_inclusive"),
  MOODS("Moods & Emotions", "favorite"),
  PHYSICAL("Physical Symptoms", "healing"),
  PERIOD("Period & Flow", "water_drop"),
  ENERGY("Energy & Appetite", "restaurant"),
  COMFORT("Comfort & Support", "handshake"),
  LIFE("Daily Life", "calendar_month")
}

data class MoodSymptomTag(
  val id: String,
  val name: String,
  val category: MoodCategory,
  val isDefaultSelected: Boolean = false
)

enum class FoodCuisine(val label: String) {
  ALL("All Cuisines"),
  INDIAN("Indian"),
  ITALIAN("Italian"),
  FRENCH("French"),
  MEXICAN("Mexican"),
  ASIAN("Asian"),
  MEDITERRANEAN("Mediterranean"),
  AMERICAN("American"),
  DESSERTS("Desserts & Bakery"),
  BEVERAGES("Beverages & Teas")
}

data class FoodDish(
  val id: String,
  val name: String,
  val cuisine: FoodCuisine,
  val region: String,
  val description: String,
  val imageUrl: String,
  val isVegan: Boolean = false,
  val isJainFriendly: Boolean = false,
  val comfortBadge: String = "Comfort pick",
  val isFavorite: Boolean = false
)

data class CartItem(
  val dish: FoodDish,
  val quantity: Int = 1
)

enum class FoodOrderStatus(val label: String, val userMessage: String) {
  REQUEST_SUBMITTED("Request Submitted", "Request sent to Buga"),
  NOTIFIED("Buga Has Been Notified", "Seen with devotion ❤️"),
  ON_THE_WAY("Buga Is Taking Care of It", "On the way with food 🛵"),
  COMPLETED("Request Completed", "Delivered & enjoyed ✨")
}

data class FoodOrderRequest(
  val referenceId: String,
  val timestamp: String,
  val items: List<CartItem>,
  val customNote: String,
  val timing: String,
  val status: FoodOrderStatus
)

enum class BellStatus(val label: String) {
  NOTIFICATION_CREATED("Notification Sent"),
  SEEN_BY_BUGA("Seen by Buga"),
  BUGA_RESPONDING("Buga Is Responding"),
  REQUEST_CLOSED("Request Closed & Hugged")
}

data class BellAlert(
  val id: String,
  val timestamp: String,
  val urgency: String,
  val message: String,
  val status: BellStatus
)

data class PastCheckin(
  val date: String,
  val time: String,
  val title: String,
  val description: String,
  val icon: String
)

data class CycleState(
  val hasLoggedPeriod: Boolean = false,
  val currentDayOfCycle: Int = 0,
  val totalCycleDays: Int = 28,
  val periodDurationDays: Int = 5,
  val phaseName: String = "No Period Logged",
  val daysUntilNextPeriod: Int = 0,
  val nextPeriodDateText: String = "",
  val nextPeriodRange: String = "",
  val startDate: String = "",
  val endDate: String = "",
  val flowLevel: String = "Medium",
  val crampIntensity: String = "Mild",
  val wellnessNotes: String = "",
  val isSharedWithPartner: Boolean = true,
  val periodDays: Set<String> = emptySet(),
  val noteDays: Map<String, String> = emptyMap()
)
