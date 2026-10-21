package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppStateManager
import com.example.model.BellStatus
import com.example.ui.theme.LavenderSecondaryContainer
import com.example.ui.theme.LavenderSecondaryFixed
import com.example.ui.theme.PlayfairDisplayFontFamily
import com.example.ui.theme.PlusJakartaSansFontFamily
import com.example.ui.theme.RoseOnPrimaryContainer
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.RosePrimaryContainer
import com.example.ui.theme.RosePrimaryFixed
import com.example.ui.theme.RosePrimaryFixedDim
import com.example.ui.theme.SageTertiary
import com.example.ui.theme.SageTertiaryContainer
import com.example.ui.theme.SageTertiaryFixed

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RingBellScreen(
  stateManager: AppStateManager,
  onNavigate: (String) -> Unit
) {
  var selectedUrgency by remember { mutableStateOf("Warm Support") }
  var customNoteText by remember { mutableStateOf("") }
  var showCancelModal by remember { mutableStateOf(false) }
  var showAddNoteModal by remember { mutableStateOf(false) }
  var extraNoteInput by remember { mutableStateOf("") }

  // Heartbeat pulse animation
  val infiniteTransition = rememberInfiniteTransition(label = "bellPulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.96f,
    targetValue = 1.06f,
    animationSpec = infiniteRepeatable(
      animation = tween(1100),
      repeatMode = RepeatMode.Reverse
    ),
    label = "bellScale"
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. Warm Reassurance & Non-Emergency Disclaimer
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("ring_bell_reassurance_card"),
      shape = RoundedCornerShape(26.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
      elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
      Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Box(
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(RosePrimaryContainer),
          contentAlignment = Alignment.Center
        ) {
          Icon(Icons.Default.Favorite, contentDescription = null, tint = RoseOnPrimaryContainer, modifier = Modifier.size(20.dp))
        }

        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = "Need your Buga? Let him know. 💗",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "BUGA at Service connects straight to your loving partner's phone. This is a devoted partner care tool, not an emergency medical service.",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 12.sp,
            lineHeight = 17.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 2.dp)
          )
        }
      }
    }

    // 2. Interactive Bell Composer Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("summon_composer_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        // Glowing Bell Visual Centerpiece
        Box(
          modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(
              Brush.radialGradient(
                colors = listOf(RosePrimaryContainer.copy(alpha = 0.45f), Color.Transparent)
              )
            ),
          contentAlignment = Alignment.Center
        ) {
          Box(
            modifier = Modifier
              .size(90.dp)
              .scale(pulseScale)
              .clip(CircleShape)
              .background(
                Brush.linearGradient(
                  listOf(RosePrimary, RosePrimaryContainer)
                )
              )
              .clickable {
                stateManager.triggerRingBell(selectedUrgency, customNoteText)
              }
              .testTag("center_ring_bell_button"),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.NotificationsActive,
              contentDescription = "Ring Bell Center",
              tint = Color.White,
              modifier = Modifier.size(44.dp)
            )
          }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
          Text(
            text = "HEARTBEAT SUMMON",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp,
            color = RosePrimary
          )
          Text(
            text = "Ready to summon Buga",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Tap the golden bell or choose a gentle reason below",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        // Urgency Level Selector
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = "Attention Urgency Level",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf(
              "🌱 Gentle",
              "☕ Warm Support",
              "🫂 Come Hold Me"
            ).forEach { urgencyOption ->
              val cleanName = urgencyOption.substringAfter(" ").trim()
              val isSelected = selectedUrgency == cleanName
              Surface(
                color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(9999.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(42.dp)
                  .clickable { selectedUrgency = cleanName }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = urgencyOption,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                  )
                }
              }
            }
          }
        }

        // Preset Whispers
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Text(
            text = "Preset Whispers",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          val whispers = listOf(
            "Need a cuddle & forehead kisses 🥺",
            "Cramps are bad today, please check in",
            "Can you call me when you're free?",
            "Craving something sweet! 🍓",
            "Just missing you a lot ❤️"
          )

          FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            whispers.forEach { whisper ->
              Surface(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(9999.dp),
                modifier = Modifier
                  .clickable { customNoteText = whisper }
                  .testTag("preset_whisper_chip")
              ) {
                Text(
                  text = whisper,
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
              }
            }
          }
        }

        // Custom Note Field
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Text(
            text = "Custom Care Note",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          OutlinedTextField(
            value = customNoteText,
            onValueChange = { customNoteText = it },
            placeholder = { Text("Add a little note for Buga (optional)...") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = RosePrimary,
              unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
              unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ),
            minLines = 2
          )
        }

        // Action Button
        Button(
          onClick = {
            stateManager.triggerRingBell(selectedUrgency, customNoteText)
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("submit_bell_action_button"),
          shape = RoundedCornerShape(9999.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = RosePrimary,
            contentColor = Color.White
          )
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(Icons.Default.NotificationsActive, contentDescription = null, modifier = Modifier.size(20.dp))
            Text(
              text = "Ring Bell for Buga",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 14.sp
            )
          }
        }
      }
    }

    // 3. Active Request Progress Card (Live Timeline View)
    stateManager.activeBellAlert?.let { alert ->
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("active_bell_progress_card"),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          // Status Header Banner
          Surface(
            color = RosePrimaryFixed.copy(alpha = 0.5f),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(42.dp)
                  .clip(CircleShape)
                  .background(RosePrimary),
                contentAlignment = Alignment.Center
              ) {
                Icon(Icons.Default.Doorbell, contentDescription = null, tint = Color.White, modifier = Modifier.size(22.dp))
              }

              Column(modifier = Modifier.weight(1f)) {
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                  Surface(color = RosePrimary, shape = RoundedCornerShape(9999.dp)) {
                    Text(
                      text = "BELL RUNG",
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color.White,
                      modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                  }
                  Text(
                    text = alert.timestamp,
                    fontSize = 11.sp,
                    fontFamily = PlusJakartaSansFontFamily,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }

                Text(
                  text = "Your bell has been rung! 🔔 Buga has been notified.",
                  fontFamily = PlayfairDisplayFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 15.sp,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                  text = "He is expected to check in within about 10 minutes.",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 12.sp,
                  color = RosePrimary,
                  fontWeight = FontWeight.SemiBold
                )
              }
            }
          }

          // Active Note Snapshot
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(12.dp),
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Icon(Icons.Default.Sms, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(18.dp))
              Column {
                Text(
                  text = "SUMMON MESSAGE (${alert.urgency})",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 10.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                  text = "“${alert.message}”",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontStyle = FontStyle.Italic,
                  fontSize = 13.sp,
                  color = MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.padding(top = 2.dp)
                )
              }
            }
          }

          // Care Progress Timeline
          Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Text(
              text = "Care Progress Timeline",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Step 1: Sent
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(24.dp)
                  .clip(CircleShape)
                  .background(SageTertiaryContainer),
                contentAlignment = Alignment.Center
              ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = SageTertiary, modifier = Modifier.size(14.dp))
              }
              Column {
                Text("Notification sent to Buga's phone", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                Text("Delivered with priority vibration & sound", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }

            // Step 2: Seen
            val isSeen = alert.status in listOf(BellStatus.SEEN_BY_BUGA, BellStatus.BUGA_RESPONDING, BellStatus.REQUEST_CLOSED)
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(24.dp)
                  .clip(CircleShape)
                  .background(if (isSeen) SageTertiaryContainer else MaterialTheme.colorScheme.surfaceContainerHigh),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  Icons.Default.Check,
                  contentDescription = null,
                  tint = if (isSeen) SageTertiary else MaterialTheme.colorScheme.outlineVariant,
                  modifier = Modifier.size(14.dp)
                )
              }
              Column {
                Text("Seen by Buga", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 12.sp)
                Text(if (isSeen) "Read on mobile widget • Attention focused" else "Pending partner open...", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }

            // Step 3: Responding
            val isResponding = alert.status in listOf(BellStatus.BUGA_RESPONDING, BellStatus.REQUEST_CLOSED)
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(24.dp)
                  .clip(CircleShape)
                  .background(if (isResponding) RosePrimaryContainer else MaterialTheme.colorScheme.surfaceContainerHigh),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  Icons.Default.DirectionsWalk,
                  contentDescription = null,
                  tint = if (isResponding) RosePrimary else MaterialTheme.colorScheme.outlineVariant,
                  modifier = Modifier.size(14.dp)
                )
              }
              Column {
                Text("Active · Buga is on his way", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 12.sp, color = if (isResponding) RosePrimary else MaterialTheme.colorScheme.onSurface)
                Text(if (isResponding) "Bringing treats, warm hugs & care" else "Estimated arrival ~10 minutes", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
              }
            }
          }

          // Active Action Buttons
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Button(
              onClick = { showAddNoteModal = true },
              shape = RoundedCornerShape(9999.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = LavenderSecondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
              ),
              modifier = Modifier.weight(1f)
            ) {
              Icon(Icons.Default.AddComment, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Add Note", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }

            Button(
              onClick = { showCancelModal = true },
              shape = RoundedCornerShape(9999.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
              ),
              modifier = Modifier.weight(1f)
            ) {
              Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Cancel Alert", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
          }
        }
      }
    }

    // 4. Past Bell History
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("past_bell_history_card"),
      shape = RoundedCornerShape(26.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
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
            Icon(Icons.Default.HistoryEdu, contentDescription = null, tint = RosePrimary)
            Text(
              text = "Past Bell History",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 17.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }

          Text(
            text = "Last 7 Days",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          stateManager.pastBellHistory.forEach { item ->
            Surface(
              color = MaterialTheme.colorScheme.surfaceContainerLow,
              shape = RoundedCornerShape(16.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(RosePrimaryFixed),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = when (item.icon) {
                      "spa" -> Icons.Default.Spa
                      "icecream" -> Icons.Default.Icecream
                      else -> Icons.Default.Favorite
                    },
                    contentDescription = null,
                    tint = RosePrimary,
                    modifier = Modifier.size(16.dp)
                  )
                }

                Column(modifier = Modifier.weight(1f)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                  ) {
                    Text(
                      text = item.title,
                      fontFamily = PlusJakartaSansFontFamily,
                      fontWeight = FontWeight.SemiBold,
                      fontSize = 12.sp,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                      text = item.date,
                      fontFamily = PlusJakartaSansFontFamily,
                      fontSize = 10.sp,
                      color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                  }
                  Text(
                    text = "${item.time} · ${item.description}",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 2.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))
  }

  // Cancel Confirmation Modal Dialog
  if (showCancelModal) {
    AlertDialog(
      onDismissRequest = { showCancelModal = false },
      title = {
        Text("Cancel this care request?", fontFamily = PlayfairDisplayFontFamily, fontWeight = FontWeight.Bold)
      },
      text = {
        Text(
          "Are you feeling better or did you find each other? Buga will be notified that everything is okay 💕",
          fontFamily = PlusJakartaSansFontFamily,
          fontSize = 13.sp
        )
      },
      confirmButton = {
        Button(
          onClick = {
            stateManager.cancelBellAlert()
            showCancelModal = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
          shape = RoundedCornerShape(9999.dp)
        ) {
          Text("Yes, I'm Okay")
        }
      },
      dismissButton = {
        TextButton(onClick = { showCancelModal = false }) {
          Text("Keep Active", color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
      },
      shape = RoundedCornerShape(24.dp)
    )
  }

  // Add Note Modal Dialog
  if (showAddNoteModal) {
    AlertDialog(
      onDismissRequest = { showAddNoteModal = false },
      title = {
        Text("Send extra note to Buga", fontFamily = PlayfairDisplayFontFamily, fontWeight = FontWeight.Bold)
      },
      text = {
        OutlinedTextField(
          value = extraNoteInput,
          onValueChange = { extraNoteInput = it },
          placeholder = { Text("e.g. Please bring extra water too!") },
          modifier = Modifier.fillMaxWidth(),
          shape = RoundedCornerShape(16.dp)
        )
      },
      confirmButton = {
        Button(
          onClick = {
            if (extraNoteInput.isNotBlank()) {
              stateManager.activeBellAlert = stateManager.activeBellAlert?.copy(
                message = "${stateManager.activeBellAlert?.message} + [Update: ${extraNoteInput.trim()}]"
              )
              extraNoteInput = ""
            }
            showAddNoteModal = false
          },
          colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
          shape = RoundedCornerShape(9999.dp)
        ) {
          Text("Send to Buga")
        }
      },
      dismissButton = {
        TextButton(onClick = { showAddNoteModal = false }) {
          Text("Dismiss")
        }
      },
      shape = RoundedCornerShape(24.dp)
    )
  }
}
