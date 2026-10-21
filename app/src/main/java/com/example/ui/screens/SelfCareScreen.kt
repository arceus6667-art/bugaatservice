package com.example.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HotTub
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.NightShelter
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.SelfImprovement
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppStateManager
import com.example.ui.theme.LavenderSecondaryContainer
import com.example.ui.theme.PlayfairDisplayFontFamily
import com.example.ui.theme.PlusJakartaSansFontFamily
import com.example.ui.theme.RoseOnPrimaryContainer
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.RosePrimaryContainer
import com.example.ui.theme.RosePrimaryFixed
import com.example.ui.theme.SageOnTertiaryContainer
import com.example.ui.theme.SageTertiary
import com.example.ui.theme.SageTertiaryContainer

@Composable
fun SelfCareScreen(
  stateManager: AppStateManager,
  onNavigate: (String) -> Unit
) {
  var isBreathingActive by remember { mutableStateOf(false) }

  // Breathing animation (4s inhale, 4s hold, 6s exhale)
  val infiniteTransition = rememberInfiniteTransition(label = "breathingAnim")
  val breathScale by infiniteTransition.animateFloat(
    initialValue = 0.8f,
    targetValue = 1.25f,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 4000, easing = LinearEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "breathScale"
  )

  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 14.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header
    Card(
      modifier = Modifier.fillMaxWidth(),
      shape = RoundedCornerShape(26.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Row(
        modifier = Modifier.padding(18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        Box(
          modifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .background(RosePrimaryFixed),
          contentAlignment = Alignment.Center
        ) {
          Icon(Icons.Default.SelfImprovement, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(24.dp))
        }

        Column {
          Text(
            text = "Little Things That Help 🌸",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
          Text(
            text = "Comfort rituals, gentle pacing & partner connection",
            fontFamily = PlusJakartaSansFontFamily,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    // Interactive Calming Breathing Exercise
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .testTag("breathing_exercise_card"),
      shape = RoundedCornerShape(28.dp),
      colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
      elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
      Column(
        modifier = Modifier.padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        Text(
          text = "Calming 4-7-8 Breathing Guide",
          fontFamily = PlayfairDisplayFontFamily,
          fontWeight = FontWeight.Bold,
          fontSize = 18.sp,
          color = MaterialTheme.colorScheme.onSurface
        )
        Text(
          text = "Soothes uterine cramping & calms the nervous system gently.",
          fontFamily = PlusJakartaSansFontFamily,
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center
        )

        // Animated Breathing Orb
        Box(
          modifier = Modifier
            .size(130.dp)
            .clip(CircleShape)
            .background(RosePrimaryFixed.copy(alpha = 0.5f)),
          contentAlignment = Alignment.Center
        ) {
          Box(
            modifier = Modifier
              .size(90.dp)
              .scale(if (isBreathingActive) breathScale else 1f)
              .clip(CircleShape)
              .background(
                Brush.radialGradient(
                  colors = listOf(RosePrimary, RosePrimaryContainer)
                )
              ),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = if (isBreathingActive) "Breathe..." else "Tap to\nBreathe",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = Color.White,
              textAlign = TextAlign.Center
            )
          }
        }

        Button(
          onClick = { isBreathingActive = !isBreathingActive },
          shape = RoundedCornerShape(9999.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = if (isBreathingActive) LavenderSecondaryContainer else RosePrimary,
            contentColor = if (isBreathingActive) MaterialTheme.colorScheme.onSecondaryContainer else Color.White
          ),
          modifier = Modifier.height(42.dp)
        ) {
          Text(
            text = if (isBreathingActive) "Pause Exercise" else "Start 2-Min Reset",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
          )
        }
      }
    }

    // Care Ritual Cards
    val careCards = listOf(
      Triple("Warm compress on lower abdomen", "Eases smooth muscle spasms and enhances pelvic blood flow. Ask Buga to heat the lavender bag.", Icons.Default.HotTub),
      Triple("Hydration & Peppermint/Ginger tea", "Relieves bloating, supports digestive comfort, and warms the stomach naturally.", Icons.Default.LocalCafe),
      Triple("20-min Power Rest / Nap", "Aligns hormones and reduces afternoon fatigue with soft white noise.", Icons.Default.NightShelter),
      Triple("Gentle pelvic & cat-cow stretches", "Relieves lower back pressure and decompresses the spine gently.", Icons.Default.DirectionsWalk),
      Triple("Comforting bedtime melody", "Reduces cortisol and sets up restful sleep.", Icons.Default.MusicNote),
      Triple("Send Buga a tender note", "Share what you need — emotional comfort is just a heartbeat away.", Icons.Default.Favorite)
    )

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
      careCards.forEach { (title, desc, icon) ->
        Surface(
          color = MaterialTheme.colorScheme.surfaceContainerLowest,
          shape = RoundedCornerShape(20.dp),
          shadowElevation = 1.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
          ) {
            Box(
              modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(LavenderSecondaryContainer),
              contentAlignment = Alignment.Center
            ) {
              Icon(icon, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(20.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = title,
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = desc,
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                lineHeight = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp)
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
  }
}
