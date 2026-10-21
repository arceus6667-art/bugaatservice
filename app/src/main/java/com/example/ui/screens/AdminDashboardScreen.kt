package com.example.ui.screens

import com.example.supabase.ActServiceApi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MarkChatUnread
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.StickyNote2
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.AppStateManager
import com.example.model.BellStatus
import com.example.model.FoodOrderStatus
import com.example.model.UserRole
import com.example.ui.theme.LavenderSecondaryContainer
import com.example.ui.theme.LavenderSecondaryFixed
import com.example.ui.theme.PlayfairDisplayFontFamily
import com.example.ui.theme.PlusJakartaSansFontFamily
import com.example.ui.theme.RoseOnPrimaryContainer
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.RosePrimaryContainer
import com.example.ui.theme.RosePrimaryFixed
import com.example.ui.theme.RosePrimaryFixedDim
import com.example.ui.theme.SageOnTertiaryContainer
import com.example.ui.theme.SageTertiary
import com.example.ui.theme.SageTertiaryContainer
import com.example.ui.theme.SageTertiaryFixed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AdminDashboardScreen(
  stateManager: AppStateManager,
  onNavigate: (String) -> Unit
) {
  val coroutineScope = rememberCoroutineScope()
  var shortcutToastMessage by remember { mutableStateOf<String?>(null) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Admin Header & Switcher Banner
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("admin_header_card"),
      shape = RoundedCornerShape(26.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(RosePrimary)
            )
            Text(
              text = "Buga Control Panel 🛠️❤️",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 18.sp,
              color = RosePrimary
            )
          }

          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLowest,
            shape = RoundedCornerShape(9999.dp),
            modifier = Modifier
              .clickable {
                ActServiceApi.signOut()
                stateManager.isLoggedIn = false
                stateManager.currentUserRole = UserRole.USER
                onNavigate("login")
              }
              .testTag("admin_sign_out_button")
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(Icons.Default.ExitToApp, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(16.dp))
              Text("Sign Out", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = RosePrimary)
            }
          }
        }

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Icon(Icons.Default.AdminPanelSettings, contentDescription = null, tint = SageTertiary, modifier = Modifier.size(16.dp))
            Text(
              text = "Logged in as Admin (buga)",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

          }

          Text(
            text = "❤️ Devotion: 99.8%",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = RosePrimary
          )
        }

        // Loving Greeting Box
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLowest,
          shape = RoundedCornerShape(18.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(RosePrimaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.Handshake, contentDescription = null, tint = RoseOnPrimaryContainer, modifier = Modifier.size(20.dp))
            }
            Column {
              Text("Hey Buga!", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.Bold, fontSize = 13.sp)
              Text(
                "Her comfort is in your hands today. Tread softly & shower with warmth.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }

    // 2. Android System Push Simulation (Heads-Up Preview)
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Icon(Icons.Default.PhoneAndroid, contentDescription = null, tint = MaterialTheme.colorScheme.secondary, modifier = Modifier.size(15.dp))
          Text(
            text = "SYSTEM PUSH SIMULATION",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            letterSpacing = 0.5.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
        Text("Android 14 Heads-Up", fontSize = 10.sp, color = MaterialTheme.colorScheme.outline)
      }

      Surface(
        color = MaterialTheme.colorScheme.inverseSurface,
        shape = RoundedCornerShape(20.dp),
        shadowElevation = 6.dp,
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(22.dp)
                  .clip(RoundedCornerShape(6.dp))
                  .background(RosePrimary),
                contentAlignment = Alignment.Center
              ) {
                Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = Color.White, modifier = Modifier.size(13.dp))
              }
              Text(
                text = "BUGA AT SERVICE 🔔",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = RosePrimaryFixed
              )
              Text("• 3m ago", fontSize = 10.sp, color = MaterialTheme.colorScheme.outlineVariant)
            }
          }

          Column(modifier = Modifier.padding(start = 28.dp)) {
            val alert = stateManager.activeBellAlert
            val order = stateManager.activeFoodOrders.firstOrNull()
            Text(
              text = when {
                alert != null -> "Comfort Alert from Bogi 🔔"
                order != null -> "New Food Craving Request 🍕"
                else -> "All Quiet & Peaceful 🌸"
              },
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp,
              color = Color.White
            )
            Text(
              text = when {
                alert != null -> "“${alert.message}”"
                order != null -> "“Request ${order.referenceId}: ${order.items.joinToString { "${it.quantity}x ${it.dish.name}" }}”"
                else -> "“No active bell alerts or pending orders right now. Bogi is resting safely.”"
              },
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.inverseOnSurface.copy(alpha = 0.85f),
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }

          Surface(
            color = Color.White.copy(alpha = 0.1f),
            shape = RoundedCornerShape(9999.dp),
            modifier = Modifier.padding(start = 28.dp)
          ) {
            Text(
              text = "Push notifications simulated (UI preview). Ready for Firebase integration.",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 9.sp,
              color = RosePrimaryFixedDim,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
          }
        }
      }
    }

    // 3. Ring the Bell Notifications (Urgent Alert Card)
    if (stateManager.activeBellAlert != null) {
      val alert = stateManager.activeBellAlert!!
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("admin_bell_alert_card"),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(44.dp)
                  .clip(RoundedCornerShape(16.dp))
                  .background(MaterialTheme.colorScheme.errorContainer),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  Icons.Default.NotificationsActive,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onErrorContainer,
                  modifier = Modifier.size(24.dp)
                )
              }
              Column {
                Text(
                  text = "Ring the Bell Triggered!",
                  fontFamily = PlayfairDisplayFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 18.sp,
                  color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                  text = alert.timestamp,
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }

            Surface(
              color = when (alert.status) {
                BellStatus.NOTIFICATION_CREATED -> MaterialTheme.colorScheme.errorContainer
                BellStatus.SEEN_BY_BUGA -> LavenderSecondaryFixed
                BellStatus.BUGA_RESPONDING -> RosePrimaryContainer
                BellStatus.REQUEST_CLOSED -> SageTertiaryFixed
              },
              shape = RoundedCornerShape(9999.dp)
            ) {
              Text(
                text = alert.status.label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = when (alert.status) {
                  BellStatus.NOTIFICATION_CREATED -> MaterialTheme.colorScheme.onErrorContainer
                  BellStatus.SEEN_BY_BUGA -> MaterialTheme.colorScheme.onSecondaryContainer
                  BellStatus.BUGA_RESPONDING -> RoseOnPrimaryContainer
                  BellStatus.REQUEST_CLOSED -> SageOnTertiaryContainer
                },
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
          }

          // Message Bubble
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Text(
                  text = "Her Personal Request (${alert.urgency}):",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 11.sp,
                  color = RosePrimary
                )
                Text("Direct dispatch", fontSize = 10.sp, fontStyle = FontStyle.Italic, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }

              Text(
                text = "“${alert.message}”",
                fontFamily = PlayfairDisplayFontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 4.dp)
              )
            }
          }

          // Visual Care Suggestion
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(130.dp)
              .clip(RoundedCornerShape(18.dp))
          ) {
            AsyncImage(
              model = "https://images.unsplash.com/photo-1576092768241-dec231879fc3?w=600&auto=format&fit=crop&q=80",
              contentDescription = "Chamomile & blanket",
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxSize()
            )
            Surface(
              color = Color.Black.copy(alpha = 0.65f),
              modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Icon(Icons.Default.TipsAndUpdates, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                Text(
                  text = "Recommended: Peppermint/Ginger tea + Hot water bag ready",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  color = Color.White
                )
              }
            }
          }

          // Admin Response Actions
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Button(
                onClick = { stateManager.adminAcknowledgeBell() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = LavenderSecondaryContainer,
                  contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
              ) {
                Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Acknowledge", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
              }

              Button(
                onClick = { stateManager.adminRespondingBell() },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = RosePrimaryContainer,
                  contentColor = RoseOnPrimaryContainer
                )
              ) {
                Icon(Icons.Default.DirectionsRun, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Responding", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
              }
            }

            Button(
              onClick = { stateManager.adminResolveBell() },
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
              shape = RoundedCornerShape(9999.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = RosePrimary,
                contentColor = Color.White
              )
            ) {
              Icon(Icons.Default.VolunteerActivism, contentDescription = null, modifier = Modifier.size(18.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Resolve with Care & Hugs", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            }
          }
        }
      }
    }

    // 4. Vegetarian Craving Queue (Food Requests)
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("admin_food_queue_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Box(
              modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(SageTertiaryFixed),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.Restaurant, contentDescription = null, tint = SageTertiary, modifier = Modifier.size(18.dp))
            }
            Column {
              Text(
                text = "Vegetarian Craving Queue",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Pure veg comfort meals requested",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Surface(
            color = SageTertiaryContainer,
            shape = RoundedCornerShape(9999.dp)
          ) {
            Text(
              text = "${stateManager.activeFoodOrders.size} Active",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = SageOnTertiaryContainer,
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
          }
        }

        // List Active Orders (STRICTLY NO PRICES / TOTALS!)
        if (stateManager.activeFoodOrders.isEmpty()) {
          Text(
            text = "No pending food requests right now. She is happy & full ✨",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        } else {
          stateManager.activeFoodOrders.forEach { order ->
            Surface(
              color = MaterialTheme.colorScheme.surfaceContainerLow,
              shape = RoundedCornerShape(20.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Text(
                    text = "Request ${order.referenceId}",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = RosePrimary
                  )
                  Text(
                    text = order.timestamp,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }

                // Itemized Dishes
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                  order.items.forEach { cartItem ->
                    Row(
                      verticalAlignment = Alignment.CenterVertically,
                      horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                      Box(
                        modifier = Modifier
                          .size(6.dp)
                          .clip(CircleShape)
                          .background(SageTertiary)
                      )
                      Text(
                        text = "${cartItem.quantity}x ${cartItem.dish.name}",
                        fontFamily = PlusJakartaSansFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                      )
                    }
                  }
                }

                // Note
                if (order.customNote.isNotBlank()) {
                  Surface(
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = RoundedCornerShape(12.dp)
                  ) {
                    Row(
                      modifier = Modifier.padding(8.dp),
                      verticalAlignment = Alignment.Top,
                      horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                      Icon(Icons.Default.StickyNote2, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(14.dp))
                      Text(
                        text = "Her Note: “${order.customNote}” (${order.timing})",
                        fontFamily = PlusJakartaSansFontFamily,
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                      )
                    }
                  }
                }

                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = "Status: ${order.status.label}",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = RosePrimary
                  )
                }

                // Action Buttons for Food
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                  Button(
                    onClick = {
                      stateManager.adminUpdateFoodStatus(order.referenceId, FoodOrderStatus.ON_THE_WAY)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(
                      containerColor = RosePrimaryContainer,
                      contentColor = RoseOnPrimaryContainer
                    )
                  ) {
                    Icon(Icons.Default.TwoWheeler, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Update Status: On My Way with Food 🛵", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                  }

                  Button(
                    onClick = {
                      stateManager.adminUpdateFoodStatus(order.referenceId, FoodOrderStatus.COMPLETED)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(
                      containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                      contentColor = SageTertiary
                    )
                  ) {
                    Icon(Icons.Default.TaskAlt, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Mark Completed (Tummies Happy ✨)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                  }
                }
              }
            }
          }
        }
      }
    }

    // 5. Quick Care Shortcuts for Buga
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("admin_care_shortcuts_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Column {
          Text(
            text = "Quick Care Shortcuts",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Instant loving gestures at one tap",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        // Shortcut 1: Flowers
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLow,
          shape = RoundedCornerShape(18.dp),
          modifier = Modifier
            .fillMaxWidth()
            .clickable {
              shortcutToastMessage = "Flowers logged! Fresh peonies on the way 🌸"
              coroutineScope.launch {
                delay(3000)
                shortcutToastMessage = null
              }
            }
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(38.dp)
                  .clip(CircleShape)
                  .background(RosePrimaryFixed),
                contentAlignment = Alignment.Center
              ) {
                Icon(Icons.Default.LocalFlorist, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(18.dp))
              }
              Column {
                Text("Log flower delivery", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Text("Send peonies & fresh roses", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.outlineVariant)
          }
        }

        // Shortcut 2: Sweet text
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLow,
          shape = RoundedCornerShape(18.dp),
          modifier = Modifier
            .fillMaxWidth()
            .clickable {
              shortcutToastMessage = "Sweet text dispatched: “You are so loved, my angel ❤️”"
              coroutineScope.launch {
                delay(3000)
                shortcutToastMessage = null
              }
            }
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(38.dp)
                  .clip(CircleShape)
                  .background(LavenderSecondaryContainer),
                contentAlignment = Alignment.Center
              ) {
                Icon(Icons.Default.MarkChatUnread, contentDescription = null, tint = MaterialTheme.colorScheme.onSecondaryContainer, modifier = Modifier.size(18.dp))
              }
              Column {
                Text("Send sweet text", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Text("Instant pop-up affection whisper", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.outlineVariant)
          }
        }

        // Shortcut 3: Surprise Veg Treats
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLow,
          shape = RoundedCornerShape(18.dp),
          modifier = Modifier
            .fillMaxWidth()
            .clickable {
              shortcutToastMessage = "Sweet treats queued! Warm berry tart & hot cocoa 🎉"
              coroutineScope.launch {
                delay(3000)
                shortcutToastMessage = null
              }
            }
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(38.dp)
                  .clip(CircleShape)
                  .background(SageTertiaryFixed),
                contentAlignment = Alignment.Center
              ) {
                Icon(Icons.Default.BakeryDining, contentDescription = null, tint = SageTertiary, modifier = Modifier.size(18.dp))
              }
              Column {
                Text("Surprise with vegetarian treats", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                Text("Warm pastries & dark chocolate", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.outlineVariant)
          }
        }

        AnimatedVisibility(
          visible = shortcutToastMessage != null,
          enter = fadeIn(),
          exit = fadeOut()
        ) {
          Surface(
            color = RosePrimary,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Text(
              text = shortcutToastMessage ?: "",
              color = Color.White,
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Medium,
              fontSize = 12.sp,
              textAlign = TextAlign.Center,
              modifier = Modifier.padding(10.dp)
            )
          }
        }
      }
    }

    // 6. Privacy & Medical Zero-Knowledge Wall
    Surface(
      color = MaterialTheme.colorScheme.surfaceContainerHigh,
      shape = RoundedCornerShape(24.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Icon(Icons.Default.Lock, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(18.dp))
          Text(
            text = "Privacy Lock Active 🔒",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(SageTertiary)
          )
        }

        Text(
          text = "Her personal cycle details, discharge logs, and unshared symptoms are private and hidden. Only explicitly shared comfort requests appear here.",
          fontFamily = PlusJakartaSansFontFamily,
          fontSize = 11.sp,
          lineHeight = 16.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            Icon(Icons.Default.VerifiedUser, contentDescription = null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(13.dp))
            Text("Zero-Knowledge Medical Wall", fontSize = 10.sp, color = MaterialTheme.colorScheme.outline)
          }
          Text("• Consent Token: Active", fontSize = 10.sp, color = MaterialTheme.colorScheme.outline)
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))
  }
}
