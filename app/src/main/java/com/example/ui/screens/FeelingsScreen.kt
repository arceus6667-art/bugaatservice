package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.LockPerson
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.WaterDrop
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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MoodCatalog
import com.example.model.AppStateManager
import com.example.model.MoodCategory
import com.example.ui.theme.LavenderSecondaryContainer
import com.example.ui.theme.PlayfairDisplayFontFamily
import com.example.ui.theme.PlusJakartaSansFontFamily
import com.example.ui.theme.RoseOnPrimaryContainer
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.RosePrimaryContainer
import com.example.ui.theme.RosePrimaryFixed
import com.example.ui.theme.RosePrimaryFixedDim
import com.example.ui.theme.SageTertiary
import com.example.ui.theme.SageTertiaryContainer
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeelingsScreen(
  stateManager: AppStateManager,
  onNavigate: (String) -> Unit
) {
  val coroutineScope = rememberCoroutineScope()
  var searchQuery by remember { mutableStateOf("") }
  var selectedCategory by remember { mutableStateOf(MoodCategory.ALL) }
  var showToast by remember { mutableStateOf(false) }

  val filteredTags = remember(searchQuery, selectedCategory) {
    MoodCatalog.allTags.filter { tag ->
      val matchesCat = selectedCategory == MoodCategory.ALL || tag.category == selectedCategory
      val matchesSearch = searchQuery.isBlank() || tag.name.contains(searchQuery, ignoreCase = true)
      matchesCat && matchesSearch
    }
  }

  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = 20.dp, vertical = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // 1. Header & Live Counter Section
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("feelings_header_card"),
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
            verticalAlignment = Alignment.Top
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Daily Check-in & Feelings 💭",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = RosePrimary
              )
              Text(
                text = "Select anything you're experiencing today. No judgment, only love.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
              )
            }
            Box(
              modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(RosePrimaryContainer.copy(alpha = 0.4f)),
              contentAlignment = Alignment.Center
            ) {
              Icon(Icons.Default.Spa, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(20.dp))
            }
          }

          // Live Counter Pill Bar
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(9999.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Favorite,
                  contentDescription = null,
                  tint = RosePrimary,
                  modifier = Modifier.size(16.dp)
                )
                Text(
                  text = "${stateManager.selectedTagIds.size} items selected",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontWeight = FontWeight.SemiBold,
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }

              Text(
                text = "Clear all",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = RosePrimary,
                modifier = Modifier
                  .clickable { stateManager.clearAllMoods() }
                  .testTag("clear_all_moods_button")
              )
            }
          }

          // Search Input Bar
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search 180+ moods, sensations, cravings...") },
            leadingIcon = {
              Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.outline)
            },
            trailingIcon = {
              if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { searchQuery = "" }) {
                  Icon(Icons.Default.Close, contentDescription = "Clear search", modifier = Modifier.size(18.dp))
                }
              }
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("tag_search_input"),
            shape = RoundedCornerShape(9999.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = RosePrimary,
              unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
              unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
              focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest
            ),
            singleLine = true
          )
        }
      }

      // 2. Quick Filter Category Tabs
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        listOf(
          MoodCategory.ALL to "All (203)",
          MoodCategory.MOODS to "Moods (50)",
          MoodCategory.PHYSICAL to "Physical (50)",
          MoodCategory.PERIOD to "Flow (28)",
          MoodCategory.ENERGY to "Sleep & Food (35)",
          MoodCategory.COMFORT to "Care (22)",
          MoodCategory.LIFE to "Life (18)"
        ).forEach { (cat, label) ->
          val isSelected = selectedCategory == cat
          Surface(
            color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLowest,
            shape = RoundedCornerShape(9999.dp),
            shadowElevation = if (isSelected) 2.dp else 0.dp,
            modifier = Modifier
              .clickable { selectedCategory = cat }
              .testTag("cat_pill_${cat.name.lowercase()}")
          ) {
            Text(
              text = label,
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              fontSize = 12.sp,
              color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
            )
          }
        }
      }

      // 3. Discomfort Intensity Selector
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("discomfort_intensity_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh.copy(alpha = 0.6f))
      ) {
        Column(
          modifier = Modifier.padding(18.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
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
              Icon(Icons.Default.Tune, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(18.dp))
              Text(
                text = "Discomfort Intensity",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
            }

            Surface(
              color = MaterialTheme.colorScheme.surfaceContainerLowest,
              shape = RoundedCornerShape(9999.dp)
            ) {
              Text(
                text = when (stateManager.discomfortIntensity) {
                  1 -> "Mild (1)"
                  2 -> "Moderate (2)"
                  3 -> "Uncomfy (3)"
                  else -> "Intense (4)"
                },
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = RosePrimary,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
              )
            }
          }

          Text(
            text = "How intense is your physical sensation right now? We'll adjust your daily care pace.",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf(
              1 to "Mild\nLevel 1",
              2 to "Moderate\nLevel 2",
              3 to "Uncomfy\nLevel 3",
              4 to "Intense\nLevel 4"
            ).forEach { (level, title) ->
              val isSelected = stateManager.discomfortIntensity == level
              Surface(
                color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLowest,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(48.dp)
                  .clickable { stateManager.discomfortIntensity = level }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = title,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 10.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                  )
                }
              }
            }
          }
        }
      }

      // 4. Tags Flow Grid
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("tag_catalog_card"),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
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
            Text(
              text = "${selectedCategory.label} (${filteredTags.size})",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 16.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = "Tap to toggle",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          if (filteredTags.isEmpty()) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Text(
                text = "No matching tags found",
                fontFamily = PlayfairDisplayFontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "Try another keyword or write in your personal note below.",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
              )
            }
          } else {
            FlowRow(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp),
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              filteredTags.forEach { tag ->
                val isSelected = stateManager.selectedTagIds.contains(tag.id)
                Surface(
                  color = if (isSelected) RosePrimaryContainer else MaterialTheme.colorScheme.surfaceContainerLow,
                  shape = RoundedCornerShape(9999.dp),
                  modifier = Modifier
                    .clip(RoundedCornerShape(9999.dp))
                    .clickable { stateManager.toggleMoodTag(tag.id) }
                    .testTag("tag_chip_${tag.id}")
                ) {
                  Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {
                    if (isSelected) {
                      Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = RoseOnPrimaryContainer,
                        modifier = Modifier.size(14.dp)
                      )
                    }
                    Text(
                      text = tag.name,
                      fontFamily = PlusJakartaSansFontFamily,
                      fontSize = 12.sp,
                      fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                      color = if (isSelected) RoseOnPrimaryContainer else MaterialTheme.colorScheme.onSurface
                    )
                  }
                }
              }
            }
          }
        }
      }

      // 5. Personal Note & Privacy Toggle
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("personal_note_card"),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(Icons.Default.EditNote, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(18.dp))
            Text(
              text = "Personal Note",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 17.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }

          OutlinedTextField(
            value = stateManager.personalMoodNote,
            onValueChange = { stateManager.personalMoodNote = it },
            placeholder = { Text("Anything more specific on your heart? (e.g. craving warm chamomile tea, tense neck...)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = RosePrimary,
              unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
              unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
            ),
            minLines = 3
          )

          // Strict Privacy Share Toggle (OFF by default)
          Surface(
            color = MaterialTheme.colorScheme.surfaceContainerLow,
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(14.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
              ) {
                Box(
                  modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(LavenderSecondaryContainer),
                  contentAlignment = Alignment.Center
                ) {
                  Icon(
                    imageVector = Icons.Default.LockPerson,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.size(18.dp)
                  )
                }
                Column {
                  Text(
                    text = "Share summary with Buga?",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  Text(
                    text = if (stateManager.shareMoodWithBuga) "Shared with Buga ❤️" else "Private to you only",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 11.sp,
                    color = if (stateManager.shareMoodWithBuga) RosePrimary else MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              }

              Switch(
                checked = stateManager.shareMoodWithBuga,
                onCheckedChange = { stateManager.shareMoodWithBuga = it },
                colors = SwitchDefaults.colors(
                  checkedThumbColor = Color.White,
                  checkedTrackColor = RosePrimary
                ),
                modifier = Modifier.testTag("mood_privacy_toggle_switch")
              )
            }
          }
        }
      }

      // 6. Sticky Save Button Area
      Surface(
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
        shape = RoundedCornerShape(9999.dp),
        shadowElevation = 6.dp,
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 4.dp)
      ) {
        Button(
          onClick = {
            showToast = true
            coroutineScope.launch {
              delay(3000)
              showToast = false
            }
          },
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("save_checkin_button"),
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
            Icon(Icons.Default.Favorite, contentDescription = null, modifier = Modifier.size(18.dp))
            Text(
              text = "Save Check-in (${stateManager.selectedTagIds.size} selected) ❤️",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 14.sp
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))
    }

    // Interactive Toast Notification
    AnimatedVisibility(
      visible = showToast,
      enter = fadeIn(),
      exit = fadeOut(),
      modifier = Modifier
        .align(Alignment.TopCenter)
        .padding(top = 16.dp)
    ) {
      Surface(
        color = MaterialTheme.colorScheme.inverseSurface,
        shape = RoundedCornerShape(9999.dp),
        shadowElevation = 10.dp
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Icon(Icons.Default.Favorite, contentDescription = null, tint = RosePrimaryFixedDim, modifier = Modifier.size(18.dp))
          Text(
            text = "Check-in tenderly saved for today ❤️",
            color = MaterialTheme.colorScheme.inverseOnSurface,
            fontSize = 13.sp,
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Medium
          )
        }
      }
    }
  }
}
