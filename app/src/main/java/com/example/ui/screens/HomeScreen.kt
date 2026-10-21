package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Doorbell
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.HotTub
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.NightShelter
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WaterDrop
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.FoodCatalog
import com.example.model.AppStateManager
import com.example.ui.theme.LavenderSecondaryContainer
import com.example.ui.theme.LavenderSecondaryFixed
import com.example.ui.theme.LavenderSecondaryFixedDim
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

@Composable
fun HomeScreen(
  stateManager: AppStateManager,
  onNavigate: (String) -> Unit
) {
  var hugButtonText by remember { mutableStateOf("Hug Back 💕") }
  var hugSentToast by remember { mutableStateOf(false) }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 16.dp),
    verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {
    // 1. Personal Greeting & Ambient Aura Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("greeting_hero_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Box(modifier = Modifier.fillMaxWidth()) {
        // Soft gradient blur in corner
        Box(
          modifier = Modifier
            .size(140.dp)
            .align(Alignment.TopEnd)
            .background(
              Brush.radialGradient(
                colors = listOf(RosePrimaryContainer.copy(alpha = 0.25f), Color.Transparent)
              )
            )
        )

        Column(modifier = Modifier.padding(20.dp)) {
          // Weather & Status Pill Row
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = MaterialTheme.colorScheme.surfaceContainerHigh,
              shape = RoundedCornerShape(9999.dp)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Cloud,
                  contentDescription = "Weather",
                  tint = RosePrimary,
                  modifier = Modifier.size(15.dp)
                )
                Text(
                  text = "Wednesday, Oct 24 • Cozy 68°F",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Medium,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }

            // Buga active pulse
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(8.dp)
                  .clip(CircleShape)
                  .background(RosePrimary)
              )
              Text(
                text = "Buga is Active",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = RosePrimary
              )
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          Text(
            text = "Hey, lovely! 💗",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.onSurface
          )

          Text(
            text = "Your comfort matters. Let’s see how you’re feeling today.",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Micro Affection Hug Interaction
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
              ) {
                Box(
                  modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(RosePrimaryFixed),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Hug",
                    tint = RosePrimary,
                    modifier = Modifier.size(16.dp)
                  )
                }
                Text(
                  text = stateManager.hugStatusText,
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurface,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )
              }

              Button(
                onClick = {
                  stateManager.sendHugBack()
                  hugButtonText = "Hug Sent! 💖"
                },
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = if (stateManager.hasHuggedBack) RosePrimary else MaterialTheme.colorScheme.surfaceContainerHigh,
                  contentColor = if (stateManager.hasHuggedBack) Color.White else RosePrimary
                ),
                contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                modifier = Modifier
                  .height(34.dp)
                  .testTag("hug_back_button")
              ) {
                Text(
                  text = if (stateManager.hasHuggedBack) "Hug Sent! 💖" else "Hug Back 💕",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.SemiBold
                )
              }
            }
          }
        }
      }
    }

    // 2. Prominent "Ring the Bell 🔔" Hero Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("ring_the_bell_hero_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
      Box(modifier = Modifier.fillMaxWidth()) {
        Box(
          modifier = Modifier
            .size(150.dp)
            .align(Alignment.TopEnd)
            .background(
              Brush.linearGradient(
                colors = listOf(RosePrimaryFixed.copy(alpha = 0.4f), Color.Transparent)
              )
            )
        )

        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = RosePrimaryFixed,
              shape = RoundedCornerShape(9999.dp)
            ) {
              Text(
                text = "✨ Ready to summon your favorite person",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = RoseOnPrimaryContainer,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
              )
            }
            Icon(
              imageVector = Icons.Default.Bedtime,
              contentDescription = "Moon",
              tint = RosePrimary,
              modifier = Modifier.size(20.dp)
            )
          }

          Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
          ) {
            Box(
              modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(LavenderSecondaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.NotificationsActive,
                contentDescription = "Bell Ringing",
                tint = RosePrimary,
                modifier = Modifier.size(30.dp)
              )
            }
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Need your Buga?",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "One tap alert. He’ll stop what he’s doing and check in within ~10 mins with treats or comfort.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
              )
            }
          }

          // Big Call to action
          Button(
            onClick = { onNavigate("ring_bell") },
            modifier = Modifier
              .fillMaxWidth()
              .height(52.dp)
              .testTag("ring_the_bell_cta_button"),
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
              Icon(
                imageVector = Icons.Default.Doorbell,
                contentDescription = "Doorbell",
                modifier = Modifier.size(22.dp)
              )
              Text(
                text = "Ring the Bell",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
              )
              Text(
                text = "• 1-Tap SOS",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 12.sp,
                color = RosePrimaryFixedDim
              )
            }
          }

          if (stateManager.activeBellAlert != null) {
            Surface(
              color = SageTertiaryFixed.copy(alpha = 0.5f),
              shape = RoundedCornerShape(16.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = "Active Bell",
                  tint = SageTertiary,
                  modifier = Modifier.size(18.dp)
                )
                Column {
                  Text(
                    text = "Bell has been rung! 🔔",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  Text(
                    text = "Status: ${stateManager.activeBellAlert?.status?.label ?: "Notified"}. Buga is on his way.",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }
            }
          }
        }
      }
    }

    // 3. Cycle Summary Widget
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("cycle_summary_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        if (stateManager.cycleState.hasLoggedPeriod) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Column {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(RosePrimary)
                )
                Text(
                  text = "DAY ${stateManager.cycleState.currentDayOfCycle} OF CYCLE",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 11.sp,
                  letterSpacing = 0.5.sp,
                  color = RosePrimary
                )
              }
              Text(
                text = stateManager.cycleState.phaseName,
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 2.dp)
              )
              Text(
                text = "Estimated next period in ${stateManager.cycleState.daysUntilNextPeriod} days (${stateManager.cycleState.nextPeriodDateText})",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }

            // Circular Day Badge
            Box(
              modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainer),
              contentAlignment = Alignment.Center
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                  text = "${stateManager.cycleState.daysUntilNextPeriod}",
                  fontFamily = PlayfairDisplayFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 18.sp,
                  color = RosePrimary,
                  lineHeight = 20.sp
                )
                Text(
                  text = "DAYS LEFT",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 8.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
          }
        } else {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "TRACK YOUR CYCLE 🌸",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                letterSpacing = 0.5.sp,
                color = RosePrimary
              )
              Text(
                text = "No Period Logged Yet",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 19.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 2.dp)
              )
              Text(
                text = "Select your last period start date to calculate your cycle, fertile window & predictions.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }

            Box(
              modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainer),
              contentAlignment = Alignment.Center
            ) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                  text = "--",
                  fontFamily = PlayfairDisplayFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 18.sp,
                  color = RosePrimary,
                  lineHeight = 20.sp
                )
                Text(
                  text = "DAYS LEFT",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 8.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
          }
        }

        // Quick Cycle Action Buttons
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Button(
            onClick = { onNavigate("cycle") },
            modifier = Modifier
              .weight(1f)
              .height(44.dp)
              .testTag("log_period_quick_button"),
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
              contentColor = MaterialTheme.colorScheme.onSurface
            )
          ) {
            Icon(
              imageVector = Icons.Default.EditCalendar,
              contentDescription = "Edit",
              tint = RosePrimary,
              modifier = Modifier.size(17.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Log Period / Update",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp
            )
          }

          Button(
            onClick = { onNavigate("cycle") },
            modifier = Modifier
              .height(44.dp)
              .testTag("full_cycle_view_button"),
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = LavenderSecondaryContainer,
              contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
          ) {
            Text(
              text = "Full Cycle",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp
            )
          }
        }
      }
    }

    // 4. Feelings & Symptoms Summary Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("feelings_summary_card"),
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
          Column {
            Text(
              text = "TODAY'S BALANCE",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              letterSpacing = 0.5.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
              text = "Feelings & Symptoms",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 20.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }

          Box(
            modifier = Modifier
              .size(34.dp)
              .clip(CircleShape)
              .background(SageTertiaryFixed.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Verified,
              contentDescription = "Verified",
              tint = SageTertiary,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        // Active Chips List
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Text(
              text = "Logged Mood:",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Surface(
              color = RosePrimaryFixed,
              shape = RoundedCornerShape(9999.dp)
            ) {
              Text(
                text = "Peaceful & Loved 🌸",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = RoseOnPrimaryContainer,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
              )
            }
          }

          Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "Physical:",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            listOf("Mild Cramps", "Craving Warm Tea", "Mild Fatigue").forEach { tag ->
              Surface(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(9999.dp)
              ) {
                Text(
                  text = tag,
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
              }
            }
          }
        }

        Button(
          onClick = { onNavigate("feelings") },
          modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .testTag("check_in_180_options_button"),
          shape = RoundedCornerShape(9999.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface
          )
        ) {
          Text(
            text = "+ Check in today (180+ options)",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
          )
        }
      }
    }

    // 5. "Something Yummy for You 🍕" Preview Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("food_preview_section_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        Column {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Restaurant,
              contentDescription = "Food",
              tint = RosePrimary,
              modifier = Modifier.size(20.dp)
            )
            Text(
              text = "Something Yummy for You 🍕",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 19.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
          Text(
            text = "Curated pure vegetarian comfort picks for today's cravings",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 2.dp)
          )
        }

        // 3 Featured Dishes
        val featuredDishes = listOf(
          FoodCatalog.allDishes.first { it.id == "ita_1" },
          FoodCatalog.allDishes.first { it.id == "ind_2" },
          FoodCatalog.allDishes.first { it.id == "bev_1" }
        )

        featuredDishes.forEach { dish ->
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              AsyncImage(
                model = dish.imageUrl,
                contentDescription = dish.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .size(62.dp)
                  .clip(RoundedCornerShape(14.dp))
              )

              Column(modifier = Modifier.weight(1f)) {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = dish.name,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                  )
                  Text(
                    text = "🌱 Pure Veg",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = SageTertiary
                  )
                }

                Text(
                  text = dish.description,
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )

                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = dish.comfortBadge,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    color = RosePrimary
                  )

                  Button(
                    onClick = {
                      stateManager.addToCart(dish)
                      onNavigate("food")
                    },
                    shape = RoundedCornerShape(9999.dp),
                    colors = ButtonDefaults.buttonColors(
                      containerColor = RosePrimaryFixed,
                      contentColor = RoseOnPrimaryContainer
                    ),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 2.dp),
                    modifier = Modifier.height(28.dp)
                  ) {
                    Text(
                      text = "Request Buga",
                      fontFamily = PlusJakartaSansFontFamily,
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold
                    )
                  }
                }
              }
            }
          }
        }

        // Link to full catalog
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { onNavigate("food") }
            .padding(vertical = 4.dp),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "Explore 150+ Veg Dishes",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = RosePrimary
          )
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Go",
            tint = RosePrimary,
            modifier = Modifier.size(16.dp)
          )
        }
      }
    }

    // 6. "Little Things That Help 🌸" Carousel
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("little_things_help_card"),
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
          Column {
            Text(
              text = "Little Things That Help 🌸",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 19.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "Recommended care for your luteal prep",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          Icon(
            imageVector = Icons.Default.SelfImprovement,
            contentDescription = "Care",
            tint = RosePrimary,
            modifier = Modifier.size(22.dp)
          )
        }

        // Horizontal Carousel
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // Card 1: Warm Compress
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(200.dp)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(RoundedCornerShape(12.dp))
                  .background(RosePrimaryContainer),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.HotTub,
                  contentDescription = null,
                  tint = RoseOnPrimaryContainer,
                  modifier = Modifier.size(20.dp)
                )
              }
              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = "Warm compress on abdomen",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Eases subtle uterine tension and relaxes pelvic muscle fibers.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 4.dp)
              )
              Text(
                text = "Buga has pad ready →",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = RosePrimary,
                modifier = Modifier.clickable { onNavigate("self_care") }
              )
            }
          }

          // Card 2: Peppermint Tea
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(200.dp)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(RoundedCornerShape(12.dp))
                  .background(SageTertiaryContainer),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.LocalCafe,
                  contentDescription = null,
                  tint = SageOnTertiaryContainer,
                  modifier = Modifier.size(20.dp)
                )
              }
              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = "Sip peppermint tea",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Natural antispasmodic qualities soothe digestion and bloating.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 4.dp)
              )
              Text(
                text = "Brew reminder →",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = RosePrimary,
                modifier = Modifier.clickable { onNavigate("food") }
              )
            }
          }

          // Card 3: 20-min Nap
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.width(200.dp)
          ) {
            Column(modifier = Modifier.padding(14.dp)) {
              Box(
                modifier = Modifier
                  .size(36.dp)
                  .clip(RoundedCornerShape(12.dp))
                  .background(LavenderSecondaryContainer),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.NightShelter,
                  contentDescription = null,
                  tint = MaterialTheme.colorScheme.onSecondaryContainer,
                  modifier = Modifier.size(20.dp)
                )
              }
              Spacer(modifier = Modifier.height(10.dp))
              Text(
                text = "Take a 20-min cozy nap",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Quick hormone re-alignment with gentle white noise.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(vertical = 4.dp)
              )
              Text(
                text = "Start silence timer →",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = RosePrimary,
                modifier = Modifier.clickable { onNavigate("self_care") }
              )
            }
          }
        }
      }
    }

    // 7. Last Loving Check-in Quote Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("last_checkin_quote_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "LAST LOVING CHECK-IN",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Text(
            text = "Yesterday, 8:30 PM",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.6f),
          shape = RoundedCornerShape(20.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Icon(
              imageVector = Icons.Default.FormatQuote,
              contentDescription = "Quote",
              tint = RosePrimary,
              modifier = Modifier.size(24.dp)
            )
            Column {
              Text(
                text = "“Gave foot massage and tucked her in with lavender hot water bottle. She felt peaceful and safe.”",
                fontFamily = PlusJakartaSansFontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 13.sp,
                lineHeight = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "— Logged with devotion by Buga",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = RosePrimary,
                modifier = Modifier.padding(top = 6.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(16.dp))
  }
}
