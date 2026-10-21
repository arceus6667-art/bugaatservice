package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.example.model.AppStateManager
import com.example.model.UserRole
import com.example.ui.components.BottomNavBar
import com.example.ui.components.TopCareBar
import com.example.ui.screens.AdminDashboardScreen
import com.example.ui.screens.CycleTrackerScreen
import com.example.ui.screens.FeelingsScreen
import com.example.ui.screens.FoodCatalogScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LoginScreen
import com.example.ui.screens.ProfileSettingsScreen
import com.example.ui.screens.RingBellScreen
import com.example.ui.screens.SelfCareScreen
import com.example.ui.theme.BugaAtServiceTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      val stateManager = remember { AppStateManager() }

      BugaAtServiceTheme {
        BugaApp(stateManager = stateManager)
      }
    }
  }
}

@Composable
fun BugaApp(stateManager: AppStateManager) {
  LaunchedEffect(stateManager.isLoggedIn, stateManager.currentUserRole) {
    while (stateManager.isLoggedIn) {
      try {
        stateManager.refreshFromDatabase()
        stateManager.lastConfirmationNotice = null
      } catch (e: Exception) {
        stateManager.lastConfirmationNotice = e.message ?: "Could not refresh data"
      }
      delay(15000L)
    }
  }

  if (!stateManager.isLoggedIn) {
    LoginScreen(
      stateManager = stateManager,
      onLoginSuccess = { role ->
        stateManager.currentScreen = if (role == UserRole.ADMIN) "admin" else "home"
      }
    )
    return
  }

  // Handle hardware back navigation gracefully
  BackHandler(enabled = stateManager.currentScreen != "home" && stateManager.currentScreen != "admin") {
    stateManager.currentScreen = if (stateManager.currentUserRole == UserRole.ADMIN) "admin" else "home"
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .testTag("app_scaffold"),
    containerColor = MaterialTheme.colorScheme.background,
    topBar = {
      TopCareBar(
        stateManager = stateManager,
        title = if (stateManager.currentUserRole == UserRole.ADMIN) "Buga Control Panel 🛠️❤️" else "BUGA at Service ❤️",
        subtitle = when (stateManager.currentScreen) {
          "home" -> "Home"
          "cycle" -> "My Cycle"
          "feelings" -> "My Feelings"
          "food" -> "Something Yummy 🍕"
          "ring_bell" -> "Ring the Bell 🔔"
          "admin" -> "Buga Command Center"
          "self_care" -> "Little Things That Help 🌸"
          "more", "profile" -> "Preferences & Care"
          else -> "Sanctuary"
        },
        onBellClick = {
          stateManager.currentScreen = "ring_bell"
        },
        onProfileClick = {
          stateManager.currentScreen = "more"
        }
      )
    },
    bottomBar = {
      BottomNavBar(
        currentScreen = stateManager.currentScreen,
        onNavigate = { screenKey ->
          stateManager.currentScreen = screenKey
        },
        cartItemCount = stateManager.cartItems.sumOf { it.quantity }
      )
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      Crossfade(
        targetState = stateManager.currentScreen,
        label = "screenTransition"
      ) { targetScreen ->
        when (targetScreen) {
          "home" -> HomeScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          "cycle" -> CycleTrackerScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          "feelings" -> FeelingsScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          "food" -> FoodCatalogScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          "ring_bell" -> RingBellScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          "admin" -> AdminDashboardScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          "self_care" -> SelfCareScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          "more", "profile" -> ProfileSettingsScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
          else -> HomeScreen(
            stateManager = stateManager,
            onNavigate = { stateManager.currentScreen = it }
          )
        }
      }
    }
  }
}
