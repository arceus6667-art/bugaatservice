package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppStateManager
import com.example.model.UserRole
import com.example.ui.theme.LavenderSecondaryContainer
import com.example.ui.theme.PlayfairDisplayFontFamily
import com.example.ui.theme.PlusJakartaSansFontFamily
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.RosePrimaryFixed
import com.example.ui.theme.SageTertiary

@Composable
fun TopCareBar(
  stateManager: AppStateManager,
  title: String = "BUGA at Service ❤️",
  subtitle: String = "Home",
  onBellClick: () -> Unit,
  onProfileClick: () -> Unit
) {
  Surface(
    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
    tonalElevation = 2.dp,
    shadowElevation = 2.dp
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {
      // System Status Bar preview
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "9:41",
          fontFamily = PlusJakartaSansFontFamily,
          fontWeight = FontWeight.SemiBold,
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
          horizontalArrangement = Arrangement.spacedBy(4.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "5G",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Box(
            modifier = Modifier
              .size(width = 16.dp, height = 9.dp)
              .clip(RoundedCornerShape(2.dp))
              .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f))
          )
        }
      }

      // App Header Row
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .height(56.dp)
          .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f)
        ) {
          // Heart App Icon Badge
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(RosePrimaryFixed),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Favorite,
              contentDescription = "BUGA App Logo",
              tint = RosePrimary,
              modifier = Modifier.size(20.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = title,
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 17.sp,
              color = RosePrimary,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
            Text(
              text = subtitle,
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Medium,
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        // Action Icons
        Row(
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          // Bell Shortcut
          IconButton(
            onClick = onBellClick,
            modifier = Modifier
              .size(38.dp)
              .clip(CircleShape)
              .background(LavenderSecondaryContainer)
              .testTag("top_bar_bell_button")
          ) {
            BadgedBox(
              badge = {
                if (stateManager.activeBellAlert != null) {
                  Badge(containerColor = RosePrimary)
                }
              }
            ) {
              Icon(
                imageVector = Icons.Default.NotificationsActive,
                contentDescription = "Ring Bell",
                tint = RosePrimary,
                modifier = Modifier.size(19.dp)
              )
            }
          }

          // Profile / Mode Avatar
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(if (stateManager.currentUserRole == UserRole.ADMIN) SageTertiary else RosePrimary)
              .clickable { onProfileClick() }
              .testTag("top_bar_profile_button"),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = if (stateManager.currentUserRole == UserRole.ADMIN) Icons.Default.SwapHoriz else Icons.Default.Person,
              contentDescription = "Profile",
              tint = Color.White,
              modifier = Modifier.size(19.dp)
            )
          }
        }
      }
    }
  }
}

@Composable
fun BottomNavBar(
  currentScreen: String,
  onNavigate: (String) -> Unit,
  cartItemCount: Int = 0
) {
  NavigationBar(
    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
    tonalElevation = 6.dp,
    modifier = Modifier.testTag("main_bottom_nav_bar")
  ) {
    NavigationBarItem(
      selected = currentScreen == "home",
      onClick = { onNavigate("home") },
      icon = {
        Icon(
          imageVector = Icons.Default.Favorite,
          contentDescription = "Home"
        )
      },
      label = { Text("Home") },
      colors = NavigationBarItemDefaults.colors(
        selectedIconColor = RosePrimary,
        selectedTextColor = RosePrimary,
        indicatorColor = RosePrimaryFixed.copy(alpha = 0.6f),
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
      ),
      modifier = Modifier.testTag("nav_item_home")
    )

    NavigationBarItem(
      selected = currentScreen == "cycle",
      onClick = { onNavigate("cycle") },
      icon = {
        Icon(
          imageVector = Icons.Default.CalendarMonth,
          contentDescription = "My Cycle"
        )
      },
      label = { Text("My Cycle") },
      colors = NavigationBarItemDefaults.colors(
        selectedIconColor = RosePrimary,
        selectedTextColor = RosePrimary,
        indicatorColor = RosePrimaryFixed.copy(alpha = 0.6f),
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
      ),
      modifier = Modifier.testTag("nav_item_cycle")
    )

    NavigationBarItem(
      selected = currentScreen == "feelings",
      onClick = { onNavigate("feelings") },
      icon = {
        Icon(
          imageVector = Icons.Default.Mood,
          contentDescription = "Feelings"
        )
      },
      label = { Text("Feelings") },
      colors = NavigationBarItemDefaults.colors(
        selectedIconColor = RosePrimary,
        selectedTextColor = RosePrimary,
        indicatorColor = RosePrimaryFixed.copy(alpha = 0.6f),
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
      ),
      modifier = Modifier.testTag("nav_item_feelings")
    )

    NavigationBarItem(
      selected = currentScreen == "food",
      onClick = { onNavigate("food") },
      icon = {
        BadgedBox(
          badge = {
            if (cartItemCount > 0) {
              Badge(containerColor = RosePrimary) {
                Text("$cartItemCount")
              }
            }
          }
        ) {
          Icon(
            imageVector = Icons.Default.Restaurant,
            contentDescription = "Food"
          )
        }
      },
      label = { Text("Food 🍕") },
      colors = NavigationBarItemDefaults.colors(
        selectedIconColor = RosePrimary,
        selectedTextColor = RosePrimary,
        indicatorColor = RosePrimaryFixed.copy(alpha = 0.6f),
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
      ),
      modifier = Modifier.testTag("nav_item_food")
    )

    NavigationBarItem(
      selected = currentScreen in listOf("admin", "more", "profile", "self_care"),
      onClick = { onNavigate("more") },
      icon = {
        Icon(
          imageVector = Icons.Default.GridView,
          contentDescription = "More"
        )
      },
      label = { Text("More") },
      colors = NavigationBarItemDefaults.colors(
        selectedIconColor = RosePrimary,
        selectedTextColor = RosePrimary,
        indicatorColor = RosePrimaryFixed.copy(alpha = 0.6f),
        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
      ),
      modifier = Modifier.testTag("nav_item_more")
    )
  }
}
