package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Opacity
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppStateManager
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun CycleTrackerScreen(
  stateManager: AppStateManager,
  onNavigate: (String) -> Unit
) {
  val coroutineScope = rememberCoroutineScope()
  val todayCal = remember {
    Calendar.getInstance().apply {
      set(Calendar.HOUR_OF_DAY, 0)
      set(Calendar.MINUTE, 0)
      set(Calendar.SECOND, 0)
      set(Calendar.MILLISECOND, 0)
    }
  }

  var displayedYear by remember { mutableIntStateOf(todayCal.get(Calendar.YEAR)) }
  var displayedMonth by remember { mutableIntStateOf(todayCal.get(Calendar.MONTH)) } // 0..11
  var selectedDay by remember { mutableIntStateOf(todayCal.get(Calendar.DAY_OF_MONTH)) }

  var selectedDuration by remember { mutableIntStateOf(stateManager.cycleState.periodDurationDays) }
  var selectedCycleLength by remember { mutableIntStateOf(stateManager.cycleState.totalCycleDays) }
  var selectedFlow by remember { mutableStateOf(stateManager.cycleState.flowLevel) }
  var selectedCramp by remember { mutableStateOf(stateManager.cycleState.crampIntensity) }
  var wellnessNotes by remember { mutableStateOf(stateManager.cycleState.wellnessNotes) }
  var showSavedToast by remember { mutableStateOf(false) }
  var toastMessage by remember { mutableStateOf("") }

  val monthFormat = remember { SimpleDateFormat("MMMM yyyy", Locale.US) }
  val currentMonthCal = remember(displayedYear, displayedMonth) {
    Calendar.getInstance().apply {
      set(Calendar.YEAR, displayedYear)
      set(Calendar.MONTH, displayedMonth)
      set(Calendar.DAY_OF_MONTH, 1)
      set(Calendar.HOUR_OF_DAY, 0)
      set(Calendar.MINUTE, 0)
      set(Calendar.SECOND, 0)
      set(Calendar.MILLISECOND, 0)
    }
  }
  val daysInMonth = currentMonthCal.getActualMaximum(Calendar.DAY_OF_MONTH)
  val firstDayOfWeek = currentMonthCal.get(Calendar.DAY_OF_WEEK) // 1 = Sun, 2 = Mon...
  val startOffset = firstDayOfWeek - 1

  // Selected date key
  val selectedDateKey = remember(displayedYear, displayedMonth, selectedDay) {
    String.format(Locale.US, "%04d-%02d-%02d", displayedYear, displayedMonth + 1, selectedDay)
  }

  // Helpers to check fertile window based on user's logged period
  fun isFertileDate(year: Int, month: Int, day: Int): Boolean {
    if (!stateManager.cycleState.hasLoggedPeriod || stateManager.cycleState.startDate.isBlank()) return false
    val checkCal = Calendar.getInstance().apply {
      set(year, month, day, 0, 0, 0)
      set(Calendar.MILLISECOND, 0)
    }
    val parts = stateManager.cycleState.startDate.split("-")
    if (parts.size != 3) return false
    val startCal = Calendar.getInstance().apply {
      set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt(), 0, 0, 0)
      set(Calendar.MILLISECOND, 0)
    }
    val diffDays = ((checkCal.timeInMillis - startCal.timeInMillis) / (1000 * 60 * 60 * 24)).toInt()
    if (diffDays < 0) return false
    val dayInCycle = (diffDays % stateManager.cycleState.totalCycleDays) + 1
    return dayInCycle in 12..16
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 16.dp),
    verticalArrangement = Arrangement.spacedBy(18.dp)
  ) {
    // 1. Cycle Status Hero Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("cycle_hero_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
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
            Icon(
              imageVector = Icons.Default.Spa,
              contentDescription = "My Cycle",
              tint = RosePrimary,
              modifier = Modifier.size(20.dp)
            )
            Text(
              text = "My Cycle 🌸",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }

          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLowest,
            shape = RoundedCornerShape(9999.dp)
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(6.dp)
                  .clip(CircleShape)
                  .background(if (stateManager.cycleState.hasLoggedPeriod) RosePrimary else MaterialTheme.colorScheme.outlineVariant)
              )
              Text(
                text = if (stateManager.cycleState.hasLoggedPeriod) {
                  "Day ${stateManager.cycleState.currentDayOfCycle} of ${stateManager.cycleState.totalCycleDays}"
                } else {
                  "No Period Logged"
                },
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp,
                color = if (stateManager.cycleState.hasLoggedPeriod) RosePrimary else MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }

        // Circular Gauge + Description
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          // Circular Badge
          Box(
            modifier = Modifier
              .size(76.dp)
              .clip(CircleShape)
              .background(MaterialTheme.colorScheme.surfaceContainerLowest)
              .border(4.dp, if (stateManager.cycleState.hasLoggedPeriod) RosePrimary else MaterialTheme.colorScheme.outlineVariant, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Text(
                text = if (stateManager.cycleState.hasLoggedPeriod) "${stateManager.cycleState.daysUntilNextPeriod}" else "--",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = RosePrimary,
                lineHeight = 26.sp
              )
              Text(
                text = "DAYS LEFT",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }

          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = if (stateManager.cycleState.hasLoggedPeriod) stateManager.cycleState.phaseName else "Select your cycle dates",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp,
              color = RoseOnPrimaryContainer
            )
            Text(
              text = if (stateManager.cycleState.hasLoggedPeriod) {
                "Next period in ${stateManager.cycleState.daysUntilNextPeriod} days"
              } else {
                "No period dates logged yet"
              },
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.padding(top = 2.dp)
            )
            Text(
              text = if (stateManager.cycleState.hasLoggedPeriod) {
                "Expected around ${stateManager.cycleState.nextPeriodDateText}"
              } else {
                "Tap any date on the calendar below to log your period start"
              },
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }

        // Non-Diagnostic Reassurance Banner
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLowest.copy(alpha = 0.85f),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Favorite,
              contentDescription = "Care",
              tint = SageTertiary,
              modifier = Modifier.size(16.dp)
            )
            Text(
              text = "Cycle predictions are estimates based on your logged history. BUGA is for intimate relationship care, not a medical diagnostic tool.",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              lineHeight = 16.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }
        }
      }
    }

    // 2. Interactive Monthly Calendar
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("cycle_calendar_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        // Month Navigation Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
          ) {
            IconButton(
              onClick = {
                if (displayedMonth == 0) {
                  displayedMonth = 11
                  displayedYear -= 1
                } else {
                  displayedMonth -= 1
                }
              },
              modifier = Modifier.size(32.dp)
            ) {
              Icon(Icons.Default.ChevronLeft, contentDescription = "Previous Month")
            }
            Text(
              text = monthFormat.format(currentMonthCal.time),
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(
              onClick = {
                if (displayedMonth == 11) {
                  displayedMonth = 0
                  displayedYear += 1
                } else {
                  displayedMonth += 1
                }
              },
              modifier = Modifier.size(32.dp)
            ) {
              Icon(Icons.Default.ChevronRight, contentDescription = "Next Month")
            }
          }

          Button(
            onClick = {
              displayedYear = todayCal.get(Calendar.YEAR)
              displayedMonth = todayCal.get(Calendar.MONTH)
              selectedDay = todayCal.get(Calendar.DAY_OF_MONTH)
            },
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = LavenderSecondaryContainer,
              contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            modifier = Modifier.height(32.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
              Icon(Icons.Default.Today, contentDescription = null, modifier = Modifier.size(13.dp))
              Text(text = "Today", fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
            }
          }
        }

        // Days of week
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
            Text(
              text = day,
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              textAlign = TextAlign.Center,
              modifier = Modifier.width(36.dp)
            )
          }
        }

        // Dynamic Calendar Grid
        val totalCells = startOffset + daysInMonth
        val numRows = (totalCells + 6) / 7

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          for (rowIndex in 0 until numRows) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              for (colIndex in 0 until 7) {
                val cellIndex = rowIndex * 7 + colIndex
                val dayNum = cellIndex - startOffset + 1

                if (dayNum in 1..daysInMonth) {
                  val dateKey = String.format(Locale.US, "%04d-%02d-%02d", displayedYear, displayedMonth + 1, dayNum)
                  val isSelected = selectedDay == dayNum
                  val isToday = displayedYear == todayCal.get(Calendar.YEAR) &&
                    displayedMonth == todayCal.get(Calendar.MONTH) &&
                    dayNum == todayCal.get(Calendar.DAY_OF_MONTH)
                  val isPeriod = stateManager.cycleState.periodDays.contains(dateKey)
                  val isFertile = isFertileDate(displayedYear, displayedMonth, dayNum)

                  Box(
                    modifier = Modifier
                      .size(36.dp)
                      .clip(CircleShape)
                      .background(
                        when {
                          isToday -> RosePrimary
                          isPeriod -> RosePrimaryFixed
                          isFertile -> LavenderSecondaryFixed
                          isSelected -> MaterialTheme.colorScheme.surfaceContainerHigh
                          else -> Color.Transparent
                        }
                      )
                      .then(
                        if (isSelected && !isToday) {
                          Modifier.border(2.dp, RosePrimary, CircleShape)
                        } else {
                          Modifier
                        }
                      )
                      .clickable { selectedDay = dayNum },
                    contentAlignment = Alignment.Center
                  ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                      Text(
                        text = "$dayNum",
                        fontFamily = PlusJakartaSansFontFamily,
                        fontSize = 12.sp,
                        fontWeight = if (isToday || isPeriod || isFertile || isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = when {
                          isToday -> Color.White
                          isPeriod -> RoseOnPrimaryContainer
                          isSelected -> RosePrimary
                          else -> MaterialTheme.colorScheme.onSurface
                        }
                      )
                      if (isPeriod && !isToday) {
                        Box(
                          modifier = Modifier
                            .size(3.dp)
                            .clip(CircleShape)
                            .background(RosePrimary)
                        )
                      }
                    }
                  }
                } else {
                  Spacer(modifier = Modifier.size(36.dp))
                }
              }
            }
          }
        }

        // Selected Date Action Strip
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLow,
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "Selected Date",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                  text = "${monthFormat.format(currentMonthCal.time).split(" ")[0]} $selectedDay, $displayedYear",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }

              if (stateManager.cycleState.hasLoggedPeriod) {
                TextButton(
                  onClick = {
                    stateManager.clearCycleDates()
                    toastMessage = "Cycle dates cleared."
                    showSavedToast = true
                    coroutineScope.launch {
                      delay(2500)
                      showSavedToast = false
                    }
                  }
                ) {
                  Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(14.dp), tint = MaterialTheme.colorScheme.error)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text("Clear Dates", fontSize = 11.sp, color = MaterialTheme.colorScheme.error)
                }
              }
            }

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Button(
                onClick = {
                  stateManager.setPeriodStartDate(
                    year = displayedYear,
                    month = displayedMonth,
                    day = selectedDay,
                    duration = selectedDuration,
                    cycleLength = selectedCycleLength,
                    flow = selectedFlow,
                    cramp = selectedCramp,
                    notes = wellnessNotes
                  )
                  toastMessage = "🌸 Period starting on ${monthFormat.format(currentMonthCal.time).split(" ")[0]} $selectedDay saved!"
                  showSavedToast = true
                  coroutineScope.launch {
                    delay(3000)
                    showSavedToast = false
                  }
                },
                modifier = Modifier
                  .weight(1f)
                  .height(40.dp)
                  .testTag("set_period_start_button"),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = RosePrimary,
                  contentColor = Color.White
                )
              ) {
                Text("🌸 Set as Period Start", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
              }

              OutlinedButton(
                onClick = {
                  stateManager.togglePeriodDay(selectedDateKey)
                },
                modifier = Modifier
                  .height(40.dp)
                  .testTag("toggle_period_day_button"),
                shape = RoundedCornerShape(9999.dp)
              ) {
                Text(
                  text = if (stateManager.cycleState.periodDays.contains(selectedDateKey)) "Unmark Day" else "+ Mark Day",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.SemiBold
                )
              }
            }
          }
        }

        // Calendar Legend
        Column(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(10.dp)
                  .clip(CircleShape)
                  .background(RosePrimaryFixed)
              )
              Text("Logged Period Day", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(10.dp)
                  .clip(CircleShape)
                  .background(LavenderSecondaryFixed)
              )
              Text("Ovulation Window", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(10.dp)
                  .clip(CircleShape)
                  .background(RosePrimary)
              )
              Text("Today's Date", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
              Box(
                modifier = Modifier
                  .size(10.dp)
                  .clip(CircleShape)
                  .border(1.5.dp, RosePrimary, CircleShape)
              )
              Text("Selected Date", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
          }
        }
      }
    }

    // 3. Period & Symptom Logging Form Card
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("period_logging_form_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
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
              Icon(
                imageVector = Icons.Default.WaterDrop,
                contentDescription = null,
                tint = RoseOnPrimaryContainer,
                modifier = Modifier.size(20.dp)
              )
            }
            Column {
              Text(
                text = "Log Period & Symptoms",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Tap any date above and customize settings below",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
          Icon(
            imageVector = Icons.Default.AutoAwesome,
            contentDescription = null,
            tint = RosePrimaryFixedDim,
            modifier = Modifier.size(20.dp)
          )
        }

        // Period Duration selector
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text(
            text = "Period Duration: $selectedDuration days",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf(3, 4, 5, 6, 7).forEach { days ->
              val isSelected = selectedDuration == days
              Surface(
                color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(36.dp)
                  .clickable { selectedDuration = days }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = "$days d",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 12.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                  )
                }
              }
            }
          }
        }

        // Cycle Length selector
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text(
            text = "Total Cycle Length: $selectedCycleLength days",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf(26, 27, 28, 29, 30, 32).forEach { length ->
              val isSelected = selectedCycleLength == length
              Surface(
                color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(36.dp)
                  .clickable { selectedCycleLength = length }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = "$length",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 12.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                  )
                }
              }
            }
          }
        }

        // Flow Level Selector
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Flow Level",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "$selectedFlow flow",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium,
              color = RosePrimary
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf("Spotting", "Light", "Medium", "Heavy").forEach { flowOption ->
              val isSelected = selectedFlow == flowOption
              Surface(
                color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(44.dp)
                  .clickable { selectedFlow = flowOption }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = flowOption,
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

        // Cramps & Discomfort Scale
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Cramps & Discomfort",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "$selectedCramp level",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium,
              color = SageTertiary
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            listOf("None", "Mild", "Moderate", "Severe").forEach { crampOption ->
              val isSelected = selectedCramp == crampOption
              Surface(
                color = if (isSelected) SageTertiaryContainer else MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(44.dp)
                  .clickable { selectedCramp = crampOption }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = crampOption,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }
            }
          }
        }

        // Personal Wellness Notes
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text(
            text = "Personal Wellness Notes",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurface
          )

          OutlinedTextField(
            value = wellnessNotes,
            onValueChange = { wellnessNotes = it },
            placeholder = { Text("How are you feeling today? Any special care needed...") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = RosePrimary,
              unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
            ),
            minLines = 2
          )
        }

        // Save & Calculate Button
        Button(
          onClick = {
            stateManager.setPeriodStartDate(
              year = displayedYear,
              month = displayedMonth,
              day = selectedDay,
              duration = selectedDuration,
              cycleLength = selectedCycleLength,
              flow = selectedFlow,
              cramp = selectedCramp,
              notes = wellnessNotes
            )
            toastMessage = "✨ Cycle dates and predictions updated successfully!"
            showSavedToast = true
            coroutineScope.launch {
              delay(3000)
              showSavedToast = false
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .testTag("save_period_details_button"),
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
            Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
            Text(
              text = "Save Period & Recalculate 🌸",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 14.sp
            )
          }
        }

        AnimatedVisibility(
          visible = showSavedToast,
          enter = fadeIn(),
          exit = fadeOut()
        ) {
          Text(
            text = toastMessage,
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = SageTertiary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
    }
  }
}
