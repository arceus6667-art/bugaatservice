package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppStateManager
import com.example.model.UserRole
import com.example.supabase.ActServiceApi
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
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

@Composable
fun LoginScreen(
  stateManager: AppStateManager,
  onLoginSuccess: (UserRole) -> Unit
) {
  val loginScope = rememberCoroutineScope()
  var loginBusy by remember { mutableStateOf(false) }
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var isPasswordVisible by remember { mutableStateOf(false) }
  var errorMessage by remember { mutableStateOf<String?>(null) }
  var isSplashActive by remember { mutableStateOf(true) }

  // Splash pulse animation
  val infiniteTransition = rememberInfiniteTransition(label = "heartPulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 0.95f,
    targetValue = 1.08f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseScale"
  )

  LaunchedEffect(Unit) {
    kotlinx.coroutines.delay(1800)
    isSplashActive = false
  }

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = MaterialTheme.colorScheme.background
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      // Soft Ambient Color Halos
      Box(
        modifier = Modifier
          .size(280.dp)
          .align(Alignment.TopEnd)
          .scale(1.2f)
          .background(
            brush = Brush.radialGradient(
              colors = listOf(RosePrimaryContainer.copy(alpha = 0.35f), Color.Transparent)
            )
          )
      )
      Box(
        modifier = Modifier
          .size(260.dp)
          .align(Alignment.BottomStart)
          .background(
            brush = Brush.radialGradient(
              colors = listOf(LavenderSecondaryContainer.copy(alpha = 0.5f), Color.Transparent)
            )
          )
      )

      if (isSplashActive) {
        // Splash State
        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          Box(
            modifier = Modifier
              .size(110.dp)
              .scale(pulseScale)
              .clip(CircleShape)
              .background(
                Brush.linearGradient(
                  listOf(RosePrimary, RosePrimaryContainer)
                )
              ),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Favorite,
              contentDescription = "Romantic Care Heart",
              tint = Color.White,
              modifier = Modifier.size(54.dp)
            )
          }

          Spacer(modifier = Modifier.height(24.dp))

          Text(
            text = "BUGA at Service ❤️",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = RosePrimary,
            textAlign = TextAlign.Center
          )

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "“A little care, a lot of love.”",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
          )
        }
      } else {
        // Login Form
        Column(
          modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
        ) {
          // Brand Header
          Box(
            modifier = Modifier
              .size(72.dp)
              .clip(CircleShape)
              .background(RosePrimaryFixed),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Favorite,
              contentDescription = "Logo",
              tint = RosePrimary,
              modifier = Modifier.size(36.dp)
            )
          }

          Spacer(modifier = Modifier.height(16.dp))

          Text(
            text = "BUGA at Service ❤️",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = RosePrimary
          )

          Text(
            text = "A little care, a lot of love.",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
          )

          // Sign In Form
          Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
          ) {
            Column(
              modifier = Modifier.padding(20.dp),
              verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
              Text(
                text = "Sign In",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurface
              )

              OutlinedTextField(
                value = username,
                onValueChange = {
                  username = it
                  errorMessage = null
                },
                label = { Text("Login ID") },
                leadingIcon = {
                  Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = RosePrimary
                  )
                },
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("login_username_input"),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = RosePrimary,
                  focusedLabelColor = RosePrimary,
                  unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                ),
                singleLine = true
              )

              OutlinedTextField(
                value = password,
                onValueChange = {
                  password = it
                  errorMessage = null
                },
                label = { Text("Password") },
                leadingIcon = {
                  Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Lock Icon",
                    tint = RosePrimary
                  )
                },
                trailingIcon = {
                  IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                      imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                      contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                  }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                  .fillMaxWidth()
                  .testTag("login_password_input"),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                  focusedBorderColor = RosePrimary,
                  focusedLabelColor = RosePrimary,
                  unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                ),
                singleLine = true
              )

              if (errorMessage != null) {
                Text(
                  text = errorMessage ?: "",
                  color = MaterialTheme.colorScheme.error,
                  fontSize = 12.sp,
                  fontFamily = PlusJakartaSansFontFamily
                )
              }

              Button(
                onClick = {
                  if (!loginBusy) {
                    loginBusy = true
                    loginScope.launch {
                      try {
                        val admin = ActServiceApi.signIn(username, password)
                        stateManager.currentUserRole = if (admin) UserRole.ADMIN else UserRole.USER
                        stateManager.currentScreen = if (admin) "admin" else "home"
                        stateManager.isLoggedIn = true
                        onLoginSuccess(stateManager.currentUserRole)
                      } catch (e: Exception) {
                        errorMessage = e.message ?: "Unable to sign in."
                      } finally {
                        loginBusy = false
                      }
                    }
                  }
                },
                modifier = Modifier
                  .fillMaxWidth()
                  .height(50.dp)
                  .testTag("login_submit_button"),
                shape = RoundedCornerShape(9999.dp),
                colors = ButtonDefaults.buttonColors(
                  containerColor = RosePrimary,
                  contentColor = Color.White
                )
              ) {
                Text(
                  text = "Sign In with Love ❤️",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontWeight = FontWeight.SemiBold,
                  fontSize = 15.sp
                )
              }
            }
          }
        }
      }
    }
  }
}
