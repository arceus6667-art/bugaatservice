package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.data.FoodCatalog
import com.example.model.AppStateManager
import com.example.model.FoodCuisine
import com.example.model.FoodDish
import com.example.model.FoodOrderRequest
import com.example.ui.theme.LavenderSecondaryContainer
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
import kotlinx.coroutines.launch

@Composable
fun FoodCatalogScreen(
  stateManager: AppStateManager,
  onNavigate: (String) -> Unit
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedCuisine by remember { mutableStateOf(FoodCuisine.ALL) }
  var onlyVegan by remember { mutableStateOf(false) }
  var onlyJain by remember { mutableStateOf(false) }
  var onlyFavorites by remember { mutableStateOf(false) }

  var showCartSheet by remember { mutableStateOf(false) }
  var showConfirmDialog by remember { mutableStateOf(false) }
  var confirmedOrder by remember { mutableStateOf<FoodOrderRequest?>(null) }
  var chefSuggestionIndex by remember { mutableIntStateOf(0) }

  var customOrderNote by remember { mutableStateOf("") }
  var selectedTiming by remember { mutableStateOf("") }

  val chefPicks = remember {
    listOf(
      FoodCatalog.allDishes.first { it.id == "fre_1" }, // Warm Ratatouille
      FoodCatalog.allDishes.first { it.id == "ita_3" }, // Truffle Risotto
      FoodCatalog.allDishes.first { it.id == "ind_1" }, // Shahi Paneer
      FoodCatalog.allDishes.first { it.id == "bev_1" }  // Hot Chocolate
    )
  }
  val currentChefPick = chefPicks[chefSuggestionIndex % chefPicks.size]

  val filteredDishes = remember(searchQuery, selectedCuisine, onlyVegan, onlyJain, onlyFavorites, stateManager.favoriteDishIds.size) {
    FoodCatalog.allDishes.filter { dish ->
      val matchesCuisine = selectedCuisine == FoodCuisine.ALL || dish.cuisine == selectedCuisine
      val matchesSearch = searchQuery.isBlank() ||
        dish.name.contains(searchQuery, ignoreCase = true) ||
        dish.description.contains(searchQuery, ignoreCase = true) ||
        dish.region.contains(searchQuery, ignoreCase = true)
      val matchesVegan = !onlyVegan || dish.isVegan
      val matchesJain = !onlyJain || dish.isJainFriendly
      val matchesFav = !onlyFavorites || stateManager.favoriteDishIds.contains(dish.id)

      matchesCuisine && matchesSearch && matchesVegan && matchesJain && matchesFav
    }
  }

  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
      // Header & Search Banner
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("food_catalog_header_card"),
        shape = RoundedCornerShape(26.dp),
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
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Something Yummy for You 🍕",
                fontFamily = PlayfairDisplayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = "100% Pure Vegetarian care meals & comfort cravings",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }

            // Cart badge icon button
            Surface(
              color = RosePrimaryFixed,
              shape = RoundedCornerShape(9999.dp),
              modifier = Modifier
                .clickable { showCartSheet = true }
                .testTag("open_cart_sheet_button")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.ShoppingBag,
                  contentDescription = "Cart",
                  tint = RosePrimary,
                  modifier = Modifier.size(16.dp)
                )
                Text(
                  text = "${stateManager.cartItems.sumOf { it.quantity }} items",
                  fontFamily = PlusJakartaSansFontFamily,
                  fontWeight = FontWeight.Bold,
                  fontSize = 12.sp,
                  color = RosePrimary
                )
              }
            }
          }

          // Strict No-Price Reassurance Pill
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            Surface(color = SageTertiaryFixed, shape = RoundedCornerShape(9999.dp)) {
              Text(
                text = "🌱 100% Vegetarian",
                fontSize = 10.sp,
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = SageOnTertiaryContainer,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
            Surface(color = RosePrimaryFixed, shape = RoundedCornerShape(9999.dp)) {
              Text(
                text = "💗 No Payment Required — It's on Buga",
                fontSize = 10.sp,
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = RoseOnPrimaryContainer,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
            Surface(color = LavenderSecondaryContainer, shape = RoundedCornerShape(9999.dp)) {
              Text(
                text = "✨ Vegan & Jain Badges",
                fontSize = 10.sp,
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              )
            }
          }

          // Search Field
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search pizza, pasta, paneer, dim sum, desserts...") },
            leadingIcon = {
              Icon(Icons.Default.Search, contentDescription = "Search", tint = MaterialTheme.colorScheme.outline)
            },
            trailingIcon = {
              if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { searchQuery = "" }) {
                  Icon(Icons.Default.Close, contentDescription = "Clear", modifier = Modifier.size(18.dp))
                }
              }
            },
            modifier = Modifier
              .fillMaxWidth()
              .testTag("food_search_input"),
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

      Spacer(modifier = Modifier.height(10.dp))

      // Buga's Chef Suggestion Card
      Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(22.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(14.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          AsyncImage(
            model = currentChefPick.imageUrl,
            contentDescription = currentChefPick.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(56.dp)
              .clip(RoundedCornerShape(14.dp))
          )

          Column(modifier = Modifier.weight(1f)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "BUGA'S CHEF SUGGESTION",
                fontSize = 9.sp,
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp,
                color = RosePrimary
              )
              Text(
                text = "🎲 Re-roll",
                fontSize = 11.sp,
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                color = RosePrimary,
                modifier = Modifier.clickable { chefSuggestionIndex++ }
              )
            }
            Text(
              text = currentChefPick.name,
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
              color = MaterialTheme.colorScheme.onSurface,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
            Text(
              text = currentChefPick.description,
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }

          Button(
            onClick = {
              stateManager.addToCart(currentChefPick)
              showCartSheet = true
            },
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = RosePrimary,
              contentColor = Color.White
            ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 2.dp),
            modifier = Modifier.height(30.dp)
          ) {
            Text("Want this!", fontSize = 11.sp, fontWeight = FontWeight.Bold)
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Cuisine Selector Chips
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        FoodCuisine.values().forEach { cuisine ->
          val isSelected = selectedCuisine == cuisine
          Surface(
            color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLowest,
            shape = RoundedCornerShape(9999.dp),
            shadowElevation = if (isSelected) 2.dp else 0.dp,
            modifier = Modifier.clickable { selectedCuisine = cuisine }
          ) {
            Text(
              text = cuisine.label,
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
              fontSize = 12.sp,
              color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
              modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp)
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Dietary Filters
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Surface(
          color = if (onlyVegan) SageTertiary else MaterialTheme.colorScheme.surfaceContainerLowest,
          shape = RoundedCornerShape(9999.dp),
          modifier = Modifier.clickable { onlyVegan = !onlyVegan }
        ) {
          Text(
            text = "Vegan Only",
            fontSize = 11.sp,
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = if (onlyVegan) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
          )
        }

        Surface(
          color = if (onlyJain) SageTertiary else MaterialTheme.colorScheme.surfaceContainerLowest,
          shape = RoundedCornerShape(9999.dp),
          modifier = Modifier.clickable { onlyJain = !onlyJain }
        ) {
          Text(
            text = "Jain Friendly",
            fontSize = 11.sp,
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = if (onlyJain) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
          )
        }

        Surface(
          color = if (onlyFavorites) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLowest,
          shape = RoundedCornerShape(9999.dp),
          modifier = Modifier.clickable { onlyFavorites = !onlyFavorites }
        ) {
          Text(
            text = "Favourites ❤️",
            fontSize = 11.sp,
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = if (onlyFavorites) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
          )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
          text = "${filteredDishes.size} dishes",
          fontSize = 11.sp,
          fontFamily = PlusJakartaSansFontFamily,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          modifier = Modifier.align(Alignment.CenterVertically)
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Dish Cards Lazy List
      LazyColumn(
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        items(filteredDishes, key = { it.id }) { dish ->
          DishCard(
            dish = dish,
            isFavorite = stateManager.favoriteDishIds.contains(dish.id),
            onToggleFavorite = { stateManager.toggleFavorite(dish.id) },
            onAddToCart = {
              stateManager.addToCart(dish)
            }
          )
        }

        item {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = "Craving something not listed?",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Medium,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
              text = "💗 Just write it in your care note — Buga's kitchen is always open for you",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              color = RosePrimary,
              modifier = Modifier.padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.height(48.dp))
          }
        }
      }
    }

    // Floating Cart Trigger Bar (when cart has items)
    if (stateManager.cartItems.isNotEmpty()) {
      Surface(
        color = RosePrimary,
        shape = RoundedCornerShape(9999.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 12.dp, start = 20.dp, end = 20.dp)
          .fillMaxWidth()
          .clickable { showCartSheet = true }
          .testTag("floating_cart_bar")
      ) {
        Row(
          modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(Icons.Default.ShoppingBag, contentDescription = null, tint = Color.White)
            Text(
              text = "${stateManager.cartItems.sumOf { it.quantity }} items in Food Bag",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 14.sp,
              color = Color.White
            )
          }

          Text(
            text = "Book My Order 💗 →",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.White
          )
        }
      }
    }

    // Full Cart Modal Bottom Sheet
    if (showCartSheet) {
      CartBottomSheet(
        stateManager = stateManager,
        customNote = customOrderNote,
        onNoteChange = { customOrderNote = it },
        selectedTiming = selectedTiming,
        onTimingChange = { selectedTiming = it },
        onDismiss = { showCartSheet = false },
        onBookOrder = {
          showCartSheet = false
          showConfirmDialog = true
        }
      )
    }

    // Confirmation Alert Dialog
    if (showConfirmDialog) {
      AlertDialog(
        onDismissRequest = { showConfirmDialog = false },
        title = {
          Text(
            text = "Confirm Care Food Request? 💗",
            fontFamily = PlayfairDisplayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp,
            color = MaterialTheme.colorScheme.onSurface
          )
        },
        text = {
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
              text = "Buga will be notified about your selected comfort dishes. Remember, no payment is required — just sit back and relax!",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            stateManager.cartItems.forEach { item ->
              Text(
                text = "• ${item.quantity}x ${item.dish.name}",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
        },
        confirmButton = {
          Button(
            onClick = {
              showConfirmDialog = false
              val order = stateManager.submitFoodOrder(customOrderNote, selectedTiming)
              confirmedOrder = order
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = RosePrimary,
              contentColor = Color.White
            ),
            shape = RoundedCornerShape(9999.dp)
          ) {
            Text("Confirm Order 💗")
          }
        },
        dismissButton = {
          TextButton(onClick = { showConfirmDialog = false }) {
            Text("Go Back", color = MaterialTheme.colorScheme.onSurfaceVariant)
          }
        },
        shape = RoundedCornerShape(24.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
      )
    }

    // Order Confirmed Romantic Screen Dialog
    confirmedOrder?.let { order ->
      OrderConfirmedOverlay(
        order = order,
        onDismiss = { confirmedOrder = null },
        onGoHome = {
          confirmedOrder = null
          onNavigate("home")
        }
      )
    }
  }
}

@Composable
fun DishCard(
  dish: FoodDish,
  isFavorite: Boolean,
  onToggleFavorite: () -> Unit,
  onAddToCart: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .testTag("dish_card_${dish.id}"),
    shape = RoundedCornerShape(22.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      modifier = Modifier.padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      AsyncImage(
        model = dish.imageUrl,
        contentDescription = dish.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(76.dp)
          .clip(RoundedCornerShape(16.dp))
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

          IconButton(
            onClick = onToggleFavorite,
            modifier = Modifier.size(28.dp)
          ) {
            Icon(
              imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
              contentDescription = "Favorite",
              tint = if (isFavorite) RosePrimary else MaterialTheme.colorScheme.outlineVariant,
              modifier = Modifier.size(18.dp)
            )
          }
        }

        Row(
          horizontalArrangement = Arrangement.spacedBy(6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = dish.region,
            fontSize = 10.sp,
            fontFamily = PlusJakartaSansFontFamily,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
          Text(text = "•", fontSize = 10.sp, color = MaterialTheme.colorScheme.outlineVariant)
          Text(
            text = "🌱 Pure Veg",
            fontSize = 10.sp,
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.Bold,
            color = SageTertiary
          )
          if (dish.isVegan) {
            Text(text = "•", fontSize = 10.sp, color = MaterialTheme.colorScheme.outlineVariant)
            Text(
              text = "Vegan",
              fontSize = 10.sp,
              fontFamily = PlusJakartaSansFontFamily,
              color = SageTertiary
            )
          }
          if (dish.isJainFriendly) {
            Text(text = "•", fontSize = 10.sp, color = MaterialTheme.colorScheme.outlineVariant)
            Text(
              text = "Jain",
              fontSize = 10.sp,
              fontFamily = PlusJakartaSansFontFamily,
              color = MaterialTheme.colorScheme.secondary
            )
          }
        }

        Text(
          text = dish.description,
          fontFamily = PlusJakartaSansFontFamily,
          fontSize = 11.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 2,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp),
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
            onClick = onAddToCart,
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = RosePrimaryFixed,
              contentColor = RoseOnPrimaryContainer
            ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 2.dp),
            modifier = Modifier.height(30.dp)
          ) {
            Text(
              text = "+ Add to Request",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 11.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun CartBottomSheet(
  stateManager: AppStateManager,
  customNote: String,
  onNoteChange: (String) -> Unit,
  selectedTiming: String,
  onTimingChange: (String) -> Unit,
  onDismiss: () -> Unit,
  onBookOrder: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black.copy(alpha = 0.5f))
      .clickable { onDismiss() },
    contentAlignment = Alignment.BottomCenter
  ) {
    Surface(
      modifier = Modifier
        .fillMaxWidth()
        .clickable(enabled = false) {}
        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
      color = MaterialTheme.colorScheme.surfaceContainerLowest
    ) {
      Column(
        modifier = Modifier
          .padding(24.dp)
          .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
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
            Icon(Icons.Default.Restaurant, contentDescription = null, tint = RosePrimary)
            Text(
              text = "Care Request to Buga 💗",
              fontFamily = PlayfairDisplayFontFamily,
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
          IconButton(onClick = onDismiss) {
            Icon(Icons.Default.Close, contentDescription = "Close")
          }
        }

        Text(
          text = "Intimate food transmission. No prices, no payment — love is the only ingredient.",
          fontFamily = PlusJakartaSansFontFamily,
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // List of Cart Items
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          stateManager.cartItems.forEach { item ->
            Surface(
              color = MaterialTheme.colorScheme.surfaceContainerLow,
              shape = RoundedCornerShape(18.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                AsyncImage(
                  model = item.dish.imageUrl,
                  contentDescription = item.dish.name,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                )

                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = item.dish.name,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                  )
                  Text(
                    text = "🌱 Pure Vegetarian",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 11.sp,
                    color = SageTertiary
                  )
                }

                // Quantity controls
                Row(
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  IconButton(
                    onClick = { stateManager.updateCartQuantity(item.dish.id, -1) },
                    modifier = Modifier.size(28.dp)
                  ) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease", modifier = Modifier.size(16.dp))
                  }
                  Text(
                    text = "${item.quantity}",
                    fontFamily = PlusJakartaSansFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                  )
                  IconButton(
                    onClick = { stateManager.updateCartQuantity(item.dish.id, 1) },
                    modifier = Modifier.size(28.dp)
                  ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase", modifier = Modifier.size(16.dp))
                  }
                }
              }
            }
          }
        }

        // Custom Note for Buga
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(
              text = "Custom note for Buga",
              fontFamily = PlusJakartaSansFontFamily,
              fontWeight = FontWeight.SemiBold,
              fontSize = 13.sp
            )
            Text(
              text = "Soft whisper",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )
          }

          OutlinedTextField(
            value = customNote,
            onValueChange = onNoteChange,
            placeholder = { Text("Less spicy please and bring warm hugs!") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = RosePrimary,
              unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
            ),
            singleLine = true
          )
        }

        // Arrival timing choices
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          Text(
            text = "When should this arrive?",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
          )
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            listOf("ASAP (Craving!)", "In 45 Mins", "Tonight 8:30 PM").forEach { timing ->
              val isSelected = selectedTiming == timing
              Surface(
                color = if (isSelected) RosePrimary else MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                  .weight(1f)
                  .height(42.dp)
                  .clickable { onTimingChange(timing) }
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = timing,
                    fontFamily = PlusJakartaSansFontFamily,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                  )
                }
              }
            }
          }
        }

        // Romantic Reassurance Banner
        Surface(
          color = RosePrimaryFixed.copy(alpha = 0.5f),
          shape = RoundedCornerShape(16.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(Icons.Default.Favorite, contentDescription = null, tint = RosePrimary, modifier = Modifier.size(16.dp))
            Text(
              text = "This sends a direct romantic food request to your partner Buga. Not an external commercial delivery app. Love is the primary ingredient!",
              fontFamily = PlusJakartaSansFontFamily,
              fontSize = 11.sp,
              color = RoseOnPrimaryContainer
            )
          }
        }

        // Submit Button (STRICT NO-PRICE: NO TOTAL, NO AMOUNT)
        Button(
          onClick = onBookOrder,
          modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .testTag("book_my_order_button"),
          shape = RoundedCornerShape(9999.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = RosePrimary,
            contentColor = Color.White
          )
        ) {
          Text(
            text = "Book My Order 💗",
            fontFamily = PlusJakartaSansFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
          )
        }
      }
    }
  }
}

@Composable
fun OrderConfirmedOverlay(
  order: FoodOrderRequest,
  onDismiss: () -> Unit,
  onGoHome: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black.copy(alpha = 0.6f))
      .padding(24.dp),
    contentAlignment = Alignment.Center
  ) {
    Surface(
      shape = RoundedCornerShape(32.dp),
      color = MaterialTheme.colorScheme.surfaceContainerLowest,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(
        modifier = Modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Celebration Heart
        Box(
          modifier = Modifier
            .size(76.dp)
            .clip(CircleShape)
            .background(RosePrimaryFixed),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Heart",
            tint = RosePrimary,
            modifier = Modifier.size(40.dp)
          )
        }

        // Exact Required Primary Message
        Text(
          text = "“Your order will be delivered, ma’am. 💗 No payment required — just sit back and relax.”",
          fontFamily = PlayfairDisplayFontFamily,
          fontWeight = FontWeight.Bold,
          fontSize = 19.sp,
          color = RosePrimary,
          textAlign = TextAlign.Center,
          lineHeight = 26.sp
        )

        // Exact Required Supporting Line
        Text(
          text = "Your food request has been sent to Buga. He’ll take care of it!",
          fontFamily = PlusJakartaSansFontFamily,
          fontSize = 13.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center
        )

        // Reference ID & Dispatched items
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
                text = "REQUEST REF: ${order.referenceId}",
                fontFamily = PlusJakartaSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = RosePrimary
              )
              Text(
                text = "Pure Vegetarian",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = SageTertiary
              )
            }

            Spacer(modifier = Modifier.height(8.dp))

            order.items.forEach { item ->
              Text(
                text = "• ${item.quantity}x ${item.dish.name}",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
            }

            if (order.customNote.isNotBlank()) {
              Text(
                text = "Note: “${order.customNote}”",
                fontFamily = PlusJakartaSansFontFamily,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 6.dp)
              )
            }
          }
        }

        // Action Buttons
        Button(
          onClick = onGoHome,
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
          shape = RoundedCornerShape(9999.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = RosePrimary,
            contentColor = Color.White
          )
        ) {
          Text("Back to Home", fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold)
        }

        TextButton(onClick = onDismiss) {
          Text("View My Request", color = RosePrimary, fontFamily = PlusJakartaSansFontFamily, fontWeight = FontWeight.SemiBold)
        }
      }
    }
  }
}
