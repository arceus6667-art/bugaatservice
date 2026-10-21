package com.example

import com.example.data.FoodCatalog
import com.example.data.MoodCatalog
import com.example.model.AppStateManager
import com.example.model.BellStatus
import com.example.model.FoodOrderStatus
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun foodCatalog_hasAtLeast150VegetarianDishes() {
    assertTrue(
      "Expected at least 150 distinct vegetarian dishes, found: ${FoodCatalog.allDishes.size}",
      FoodCatalog.allDishes.size >= 150
    )
    val uniqueIds = FoodCatalog.allDishes.map { it.id }.toSet()
    assertEquals("All dish IDs must be distinct", FoodCatalog.allDishes.size, uniqueIds.size)
  }

  @Test
  fun moodCatalog_hasAtLeast180UniqueTags() {
    assertTrue(
      "Expected at least 180 mood and symptom tags, found: ${MoodCatalog.allTags.size}",
      MoodCatalog.allTags.size >= 180
    )
    val uniqueIds = MoodCatalog.allTags.map { it.id }.toSet()
    assertEquals("All tag IDs must be distinct", MoodCatalog.allTags.size, uniqueIds.size)
  }

  @Test
  fun appStateManager_ringBellAndAdminAcknowledgeFlow() {
    val state = AppStateManager()
    state.triggerRingBell("Come Hold Me", "Missing you so much!")

    assertNotNull(state.activeBellAlert)
    assertEquals(BellStatus.NOTIFICATION_CREATED, state.activeBellAlert?.status)
    assertEquals("Missing you so much!", state.activeBellAlert?.message)

    state.adminAcknowledgeBell()
    assertEquals(BellStatus.SEEN_BY_BUGA, state.activeBellAlert?.status)

    state.adminRespondingBell()
    assertEquals(BellStatus.BUGA_RESPONDING, state.activeBellAlert?.status)

    state.adminResolveBell()
    assertEquals(null, state.activeBellAlert)
  }

  @Test
  fun appStateManager_foodOrderingWithoutPrice() {
    val state = AppStateManager()
    state.clearCart()
    val dish = FoodCatalog.allDishes.first()
    state.addToCart(dish)
    assertEquals(1, state.cartItems.size)

    val order = state.submitFoodOrder("Please make it warm", "ASAP")
    assertEquals(0, state.cartItems.size)
    assertEquals(FoodOrderStatus.REQUEST_SUBMITTED, order.status)
    assertTrue(state.activeFoodOrders.any { it.referenceId == order.referenceId })
  }

  @Test
  fun appStateManager_noPreselectedDemoValues() {
    val state = AppStateManager()
    // Food state has no preselected items
    assertTrue("Cart should start empty", state.cartItems.isEmpty())
    assertTrue("Favorite dishes should start empty", state.favoriteDishIds.isEmpty())
    assertTrue("Active food orders should start empty", state.activeFoodOrders.isEmpty())

    // Moods & symptoms have no preselected items
    assertTrue("Mood tag selections should start empty", state.selectedTagIds.isEmpty())
    assertTrue("Personal mood note should start empty", state.personalMoodNote.isEmpty())

    // Cycle tracker has no preselected dates
    assertTrue("Cycle state should not have logged period by default", !state.cycleState.hasLoggedPeriod)
    assertEquals(0, state.cycleState.currentDayOfCycle)
    assertTrue("Period days list should start empty", state.cycleState.periodDays.isEmpty())
  }

  @Test
  fun appStateManager_userDateSelectionAndCalculation() {
    val state = AppStateManager()
    // User sets period start date
    state.setPeriodStartDate(
      year = 2026,
      month = 9, // October (0-indexed)
      day = 10,
      duration = 5,
      cycleLength = 28
    )

    assertTrue("Period should now be logged", state.cycleState.hasLoggedPeriod)
    assertEquals(5, state.cycleState.periodDays.size)
    assertTrue(state.cycleState.periodDays.contains("2026-10-10"))
    assertTrue(state.cycleState.periodDays.contains("2026-10-14"))
    assertTrue(state.cycleState.currentDayOfCycle > 0)
    assertTrue(state.cycleState.phaseName.isNotBlank())

    // User can toggle a day
    state.togglePeriodDay("2026-10-15")
    assertTrue(state.cycleState.periodDays.contains("2026-10-15"))
    state.togglePeriodDay("2026-10-15")
    assertTrue(!state.cycleState.periodDays.contains("2026-10-15"))

    // User can clear cycle dates
    state.clearCycleDates()
    assertTrue(!state.cycleState.hasLoggedPeriod)
    assertTrue(state.cycleState.periodDays.isEmpty())
  }
}
