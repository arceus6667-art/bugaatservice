package com.example.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.data.FoodCatalog
import com.example.data.MoodCatalog

class AppStateManager {
  var isLoggedIn by mutableStateOf(false)
  var currentUserRole by mutableStateOf(UserRole.USER)

  // Current active navigation screen
  var currentScreen by mutableStateOf("home") // "home", "cycle", "feelings", "food", "ring_bell", "admin", "self_care", "profile"

  // Affection micro-interactions
  var hugStatusText by mutableStateOf("Buga sent a morning hug 32m ago")
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

  val pastBellHistory = mutableStateListOf<PastCheckin>(
    PastCheckin(
      date = "Yesterday",
      time = "6:15 PM",
      title = "Hot Tea & Comfort Session",
      description = "Resolved with chamomile hot tea, back rubs & soft kisses 🥰",
      icon = "spa"
    ),
    PastCheckin(
      date = "3 days ago",
      time = "8:40 PM",
      title = "Evening Sweet Craving",
      description = "Buga brought double chocolate gelato & tucked you in warm 🍨",
      icon = "icecream"
    ),
    PastCheckin(
      date = "Monday",
      time = "2:10 PM",
      title = "Afternoon Miss You Alert",
      description = "Answered with a 15-minute sweet voice call & virtual kisses 💖",
      icon = "favorite"
    )
  )

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

  // Admin Actions for Buga
  fun adminAcknowledgeBell() {
    activeBellAlert = activeBellAlert?.copy(status = BellStatus.SEEN_BY_BUGA)
  }

  fun adminRespondingBell() {
    activeBellAlert = activeBellAlert?.copy(status = BellStatus.BUGA_RESPONDING)
  }

  fun adminResolveBell() {
    activeBellAlert?.let {
      pastBellHistory.add(0, PastCheckin(
        date = "Today",
        time = "Just now",
        title = "Tender Care Mission Accomplished",
        description = "Buga arrived with warmth and comfort! Everything resolved with love ❤️",
        icon = "volunteer_activism"
      ))
    }
    activeBellAlert = null
  }

  fun adminUpdateFoodStatus(orderId: String, newStatus: FoodOrderStatus) {
    val index = activeFoodOrders.indexOfFirst { it.referenceId == orderId }
    if (index >= 0) {
      val existing = activeFoodOrders[index]
      activeFoodOrders[index] = existing.copy(status = newStatus)
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
}
