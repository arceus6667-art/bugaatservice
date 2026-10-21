package com.example.data

import com.example.model.FoodCuisine
import com.example.model.FoodDish

object FoodCatalog {
  val allDishes: List<FoodDish> = listOf(
    // -------------------------------------------------------------------------
    // 1. REGIONAL INDIAN (40 dishes)
    // -------------------------------------------------------------------------
    // North Indian & Punjabi
    FoodDish(
      id = "ind_1",
      name = "Shahi Paneer & Butter Naan",
      cuisine = FoodCuisine.INDIAN,
      region = "North Indian",
      description = "Melt-in-mouth cottage cheese cubes in royal saffron cashew gravy paired with buttered naan.",
      imageUrl = "https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "High Protein Comfort",
      isFavorite = true
    ),
    FoodDish(
      id = "ind_2",
      name = "Paneer Tikka Wrap",
      cuisine = FoodCuisine.INDIAN,
      region = "Punjabi",
      description = "Tender tandoor-marinated spiced cottage cheese wrapped in flaky flatbread with mint chutney.",
      imageUrl = "https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Soothing & filling"
    ),
    FoodDish(
      id = "ind_3",
      name = "Dal Makhani with Jeera Rice",
      cuisine = FoodCuisine.INDIAN,
      region = "Punjabi",
      description = "Slow simmered overnight black lentils with fresh churned white butter and aromatic cumin basmati.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cozy & Hearty",
      isFavorite = true
    ),
    FoodDish(
      id = "ind_4",
      name = "Amritsari Kulcha with Chole",
      cuisine = FoodCuisine.INDIAN,
      region = "Punjabi",
      description = "Crispy layered stuffed potato kulcha with tangy pindi chickpeas and tamarind pickled onions.",
      imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Spiced"
    ),
    FoodDish(
      id = "ind_5",
      name = "Palak Paneer with Garlic Roti",
      cuisine = FoodCuisine.INDIAN,
      region = "North Indian",
      description = "Silky garlic spinach puree with organic cottage cheese seasoned with hand ground spices.",
      imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Iron & Nutrients"
    ),
    FoodDish(
      id = "ind_6",
      name = "Kashmiri Dum Aloo",
      cuisine = FoodCuisine.INDIAN,
      region = "Kashmiri",
      description = "Golden baby potatoes simmered in an aromatic Kashmiri red chili and fennel yogurt sauce.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Gentle Warmth"
    ),
    FoodDish(
      id = "ind_7",
      name = "Malai Kofta in Velvet Cashew Cream",
      cuisine = FoodCuisine.INDIAN,
      region = "Mughlai",
      description = "Crispy cottage cheese and potato dumplings bathed in a silky smooth tomato cardamom gravy.",
      imageUrl = "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Royal Velvet",
      isJainFriendly = true
    ),
    FoodDish(
      id = "ind_8",
      name = "Chole Bhature",
      cuisine = FoodCuisine.INDIAN,
      region = "Punjabi",
      description = "Fluffy deep fried golden sourdough bhaturas served with spicy slow cooked chickpea stew.",
      imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Ultimate Treat"
    ),
    FoodDish(
      id = "ind_9",
      name = "Kadhai Paneer with Laccha Paratha",
      cuisine = FoodCuisine.INDIAN,
      region = "North Indian",
      description = "Wok tossed bell peppers, tomatoes, and paneer with crushed coriander and Kashmiri chillies.",
      imageUrl = "https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Peppery Delight"
    ),
    FoodDish(
      id = "ind_10",
      name = "Rajma Chawal Bowl",
      cuisine = FoodCuisine.INDIAN,
      region = "North Indian",
      description = "Comforting red kidney beans in thick spiced onion tomato gravy over steamy Himalayan basmati.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Hometown Hug"
    ),

    // South Indian
    FoodDish(
      id = "ind_11",
      name = "Crispy Ghee Podi Masala Dosa",
      cuisine = FoodCuisine.INDIAN,
      region = "South Indian",
      description = "Paper thin fermented rice crepe smeared with fragrant gunpowder podi and spiced potato mash.",
      imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=600&auto=format&fit=crop&q=80",
      isVegan = false,
      comfortBadge = "Crispy Gold"
    ),
    FoodDish(
      id = "ind_12",
      name = "Steaming Idli Sambar Platter",
      cuisine = FoodCuisine.INDIAN,
      region = "South Indian",
      description = "Pillow soft steamed rice cakes with drumstick shallot lentil stew and fresh coconut chutney.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Light on Tummy"
    ),
    FoodDish(
      id = "ind_13",
      name = "Medu Vada with Filter Coffee",
      cuisine = FoodCuisine.INDIAN,
      region = "South Indian",
      description = "Crispy golden lentil doughnuts with ginger, curry leaves, and traditional frothy brass cup coffee.",
      imageUrl = "https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Morning Bliss"
    ),
    FoodDish(
      id = "ind_14",
      name = "Bisi Bele Bath",
      cuisine = FoodCuisine.INDIAN,
      region = "Karnataka",
      description = "Nutritious hot lentil rice stew cooked with seasonal vegetables, tamarind, and spicy Karnataka masala.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Soul Warming"
    ),
    FoodDish(
      id = "ind_15",
      name = "Curd Rice with Pomegranate",
      cuisine = FoodCuisine.INDIAN,
      region = "Tamil Nadu",
      description = "Cooling tempered yogurt rice with mustard seeds, curry leaves, ginger, and ruby pomegranate seeds.",
      imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cooling Calm"
    ),
    FoodDish(
      id = "ind_16",
      name = "Appam with Vegetable Stew",
      cuisine = FoodCuisine.INDIAN,
      region = "Kerala",
      description = "Soft bowl-shaped coconut fermented hoppers with sweet aromatic spiced coconut milk vegetable stew.",
      imageUrl = "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Gentle Velvet"
    ),
    FoodDish(
      id = "ind_17",
      name = "Mysore Masala Dosa",
      cuisine = FoodCuisine.INDIAN,
      region = "Karnataka",
      description = "Crispy crepe coated with spicy red chili garlic chutney and rich golden mashed potatoes.",
      imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Spicy & Crisp"
    ),
    FoodDish(
      id = "ind_18",
      name = "Rava Onion Dosa",
      cuisine = FoodCuisine.INDIAN,
      region = "Andhra",
      description = "Crisp golden semolina crepe with cracked peppercorns, cumin, and sweet caramelized shallots.",
      imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crunchy Gold"
    ),

    // Maharashtrian
    FoodDish(
      id = "ind_19",
      name = "Mumbai Special Pav Bhaji",
      cuisine = FoodCuisine.INDIAN,
      region = "Maharashtrian",
      description = "Buttery mashed vegetables slow cooked with aromatic pav bhaji spices, served with toasted butter pav.",
      imageUrl = "https://images.unsplash.com/photo-1606491956689-2ea866880c84?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Comfort Favorite",
      isFavorite = true
    ),
    FoodDish(
      id = "ind_20",
      name = "Misal Pav with Farsan",
      cuisine = FoodCuisine.INDIAN,
      region = "Maharashtrian",
      description = "Spicy sprouted moth bean curry with crunchy farsan topping, freshly squeezed lime, and soft pav.",
      imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Spicy Kick"
    ),
    FoodDish(
      id = "ind_21",
      name = "Sabudana Khichdi",
      cuisine = FoodCuisine.INDIAN,
      region = "Maharashtrian",
      description = "Pearled tapioca tossed with roasted crunchy peanuts, cumin, green chillies, and fresh coriander.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Gentle Fasting Food",
      isJainFriendly = true
    ),
    FoodDish(
      id = "ind_22",
      name = "Puran Poli with Ghee",
      cuisine = FoodCuisine.INDIAN,
      region = "Maharashtrian",
      description = "Soft whole wheat flatbread stuffed with sweet jaggery cardamom Bengal gram, drizzled with desi ghee.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Sweet Warmth"
    ),
    FoodDish(
      id = "ind_23",
      name = "Batata Vada with Dry Garlic Chutney",
      cuisine = FoodCuisine.INDIAN,
      region = "Maharashtrian",
      description = "Golden gram flour fritters encasing spiced mashed potatoes paired with fiery coconut garlic chutney.",
      imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Rainy Day Snack"
    ),

    // Gujarati & Rajasthani
    FoodDish(
      id = "ind_24",
      name = "Gujarati Dal Dhokli",
      cuisine = FoodCuisine.INDIAN,
      region = "Gujarati",
      description = "Spiced whole wheat pasta diamonds simmered in sweet, tangy, and mildly spiced toor dal soup.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "All-in-one Meal"
    ),
    FoodDish(
      id = "ind_25",
      name = "Jain Dal Khichdi with Ghee",
      cuisine = FoodCuisine.INDIAN,
      region = "Gujarati",
      description = "Soothing yellow moong dal and rice tempered gently with cumin, turmeric, and pure cow ghee.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      isJainFriendly = true,
      comfortBadge = "Tummy Restorer"
    ),
    FoodDish(
      id = "ind_26",
      name = "Steamed Nylon Khaman Dhokla",
      cuisine = FoodCuisine.INDIAN,
      region = "Gujarati",
      description = "Spongy steamed gram flour cakes tempered with mustard seeds, green chillies, and juicy sugar syrup.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Feather Light"
    ),
    FoodDish(
      id = "ind_27",
      name = "Khandvi Rolls with Coconut",
      cuisine = FoodCuisine.INDIAN,
      region = "Gujarati",
      description = "Delicate silken gram flour and buttermilk rolls garnished with mustard seeds and fresh grated coconut.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      isJainFriendly = true,
      comfortBadge = "Silk Touch"
    ),
    FoodDish(
      id = "ind_28",
      name = "Rajasthani Dal Baati Churma",
      cuisine = FoodCuisine.INDIAN,
      region = "Rajasthani",
      description = "Baked whole wheat dough balls dipped in fragrant ghee, accompanied by spicy mixed lentils and sweet churma.",
      imageUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Desert Feast"
    ),
    FoodDish(
      id = "ind_29",
      name = "Rajasthani Gatte ki Sabzi",
      cuisine = FoodCuisine.INDIAN,
      region = "Rajasthani",
      description = "Boiled gram flour dumplings poached in spicy yogurt based gravy seasoned with hing and dry ginger.",
      imageUrl = "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Rich Yogurt Gravy"
    ),
    FoodDish(
      id = "ind_30",
      name = "Ker Sangri with Bajra Roti",
      cuisine = FoodCuisine.INDIAN,
      region = "Rajasthani",
      description = "Traditional desert dried wild berries and desert beans cooked with dry spices and pearl millet rotis.",
      imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Rustic Tradition"
    ),
    FoodDish(
      id = "ind_31",
      name = "Methi Thepla with Chhundo",
      cuisine = FoodCuisine.INDIAN,
      region = "Gujarati",
      description = "Nutritious fenugreek flatbreads spiced with sesame and ajwain, served with sweet grated mango preserve.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Travel Companion"
    ),
    FoodDish(
      id = "ind_32",
      name = "Sev Tameta Nu Shaak",
      cuisine = FoodCuisine.INDIAN,
      region = "Kathiyawadi",
      description = "Tangy tomato curry sweetened with jaggery and topped with crispy spicy gram flour sev.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Sweet & Tangy"
    ),

    // Street Delights
    FoodDish(
      id = "ind_33",
      name = "Pani Puri Royal Shots",
      cuisine = FoodCuisine.INDIAN,
      region = "Street Food",
      description = "Crispy hollow spheres loaded with spiced potato sprouts, sweet tamarind and chilled mint jaljeera.",
      imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Tangy Explosion"
    ),
    FoodDish(
      id = "ind_34",
      name = "Dahi Puri Chaat",
      cuisine = FoodCuisine.INDIAN,
      region = "Street Food",
      description = "Crispy puris bursting with potatoes, smothered in whipped sweet curd, chutneys, and fine sev.",
      imageUrl = "https://images.unsplash.com/photo-1585937421612-70a008356fbe?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Sweet & Savory"
    ),
    FoodDish(
      id = "ind_35",
      name = "Samosa Chaat with Mint Curd",
      cuisine = FoodCuisine.INDIAN,
      region = "Delhi Street",
      description = "Crushed golden potato peas samosas topped with hot ragda, yogurt, and pomegranate pearls.",
      imageUrl = "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Indulgent Warmth"
    ),
    FoodDish(
      id = "ind_36",
      name = "Aloo Tikki Burger (Desi Style)",
      cuisine = FoodCuisine.INDIAN,
      region = "Fusion",
      description = "Crispy spiced potato patty nestled in soft toasted buns with mint mayo, cucumber, and onions.",
      imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Snack Craving"
    ),
    FoodDish(
      id = "ind_37",
      name = "Veg Hakka Chowmein (Indo-Chinese)",
      cuisine = FoodCuisine.INDIAN,
      region = "Indo-Chinese",
      description = "Wok-fried eggless noodles tossed with crisp cabbage, julienned carrots, scallions, and soy.",
      imageUrl = "https://images.unsplash.com/photo-1585032226651-759b368d7246?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Noodle Cravings"
    ),
    FoodDish(
      id = "ind_38",
      name = "Paneer Chilli Dry",
      cuisine = FoodCuisine.INDIAN,
      region = "Indo-Chinese",
      description = "Battered cottage cheese chunks stir fried with bell peppers, green chillies, and garlic soy sauce.",
      imageUrl = "https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Sizzling Spice"
    ),
    FoodDish(
      id = "ind_39",
      name = "Vegetable Dum Biryani with Burani Raita",
      cuisine = FoodCuisine.INDIAN,
      region = "Hyderabadi",
      description = "Fragrant saffron infused long grain rice layered with caramelized onions and aromatic vegetables.",
      imageUrl = "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Royal Aromatic",
      isFavorite = true
    ),
    FoodDish(
      id = "ind_40",
      name = "Tawa Pulao with Kachumber",
      cuisine = FoodCuisine.INDIAN,
      region = "Mumbai",
      description = "Street-style butter fried rice with pav bhaji masala, crunchy capsicum, tomatoes, and peas.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Street Flavor"
    ),

    // -------------------------------------------------------------------------
    // 2. ITALIAN COMFORT (25 dishes)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "ita_1",
      name = "Warm Veg Lasagna",
      cuisine = FoodCuisine.ITALIAN,
      region = "Bologna",
      description = "Layered tender zucchini ribbons, ricotta, spinach, and slow cooked tomato basil marinara sauce.",
      imageUrl = "https://images.unsplash.com/photo-1574894709920-11b28e7367e3?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Comfort rating: 10/10",
      isFavorite = true
    ),
    FoodDish(
      id = "ita_2",
      name = "Margherita Wood-Fired Pizza",
      cuisine = FoodCuisine.ITALIAN,
      region = "Napoli",
      description = "Slow fermented sourdough crust, San Marzano coulis, fresh fior di latte mozzarella, and basil.",
      imageUrl = "https://images.unsplash.com/photo-1604382355076-af4b0eb60143?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crispy Classic",
      isFavorite = true
    ),
    FoodDish(
      id = "ita_3",
      name = "Truffle & Wild Mushroom Risotto",
      cuisine = FoodCuisine.ITALIAN,
      region = "Piedmont",
      description = "Creamy carnaroli rice simmered with porcini, button mushrooms, thyme, and black truffle oil.",
      imageUrl = "https://images.unsplash.com/photo-1633964913295-ceb43826e7c9?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Earthy Velvet",
      isFavorite = true
    ),
    FoodDish(
      id = "ita_4",
      name = "Creamy Garlic Fettuccine Alfredo",
      cuisine = FoodCuisine.ITALIAN,
      region = "Rome",
      description = "Handmade wide pasta ribbons enveloped in a velvety parmesan cream sauce with cracked black pepper.",
      imageUrl = "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Creamy Indulgence"
    ),
    FoodDish(
      id = "ita_5",
      name = "Penne Arrabbiata with Basil",
      cuisine = FoodCuisine.ITALIAN,
      region = "Rome",
      description = "Al dente penne in fiery garlic tomato sauce with Calabrian red chili flakes and fresh olive oil.",
      imageUrl = "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Zesty & Spicy"
    ),
    FoodDish(
      id = "ita_6",
      name = "Quattro Formaggi Pizza (4 Cheese)",
      cuisine = FoodCuisine.ITALIAN,
      region = "Rome",
      description = "Gooey mozzarella, gorgonzola, fontina, and aged parmigiano over thin artisan pizza crust.",
      imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cheese Lover's Dream"
    ),
    FoodDish(
      id = "ita_7",
      name = "Warm Herb Garlic Bread with Cheese",
      cuisine = FoodCuisine.ITALIAN,
      region = "Tuscany",
      description = "Artisan toasted baguette loaded with roasted garlic herb butter and bubbling molten mozzarella.",
      imageUrl = "https://images.unsplash.com/photo-1619535860434-ba1d8fa12536?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Gooey Crunch",
      isFavorite = true
    ),
    FoodDish(
      id = "ita_8",
      name = "Classic Basil Pesto Gnocchi",
      cuisine = FoodCuisine.ITALIAN,
      region = "Genoa",
      description = "Pillow soft potato gnocchi coated in fragrant pine nut genovese pesto with roasted cherry tomatoes.",
      imageUrl = "https://images.unsplash.com/photo-1551183053-bf91a1d81141?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Silky Pesto"
    ),
    FoodDish(
      id = "ita_9",
      name = "Roasted Eggplant Parmesan (Melanzane)",
      cuisine = FoodCuisine.ITALIAN,
      region = "Sicily",
      description = "Baked tender eggplant slices layered with marinara, fresh mozzarella, and sweet basil leaves.",
      imageUrl = "https://images.unsplash.com/photo-1574894709920-11b28e7367e3?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Hearty Bake"
    ),
    FoodDish(
      id = "ita_10",
      name = "Spaghetti Aglio e Olio",
      cuisine = FoodCuisine.ITALIAN,
      region = "Napoli",
      description = "Simple elegance: spaghetti tossed with golden sliced garlic, extra virgin olive oil, and parsley.",
      imageUrl = "https://images.unsplash.com/photo-1551183053-bf91a1d81141?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Pure & Aromatic"
    ),
    FoodDish(
      id = "ita_11",
      name = "Ricotta & Spinach Ravioli in Sage Butter",
      cuisine = FoodCuisine.ITALIAN,
      region = "Tuscany",
      description = "Delicate handmade ravioli pockets drizzled with golden nutty butter and crisp sage leaves.",
      imageUrl = "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Artisan Delicacy"
    ),
    FoodDish(
      id = "ita_12",
      name = "Garden Primavera Sourdough Pizza",
      cuisine = FoodCuisine.ITALIAN,
      region = "Lombardy",
      description = "Topped with grilled bell peppers, artichoke hearts, black olives, sweet corn, and goat cheese.",
      imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Colorful Garden"
    ),
    FoodDish(
      id = "ita_13",
      name = "Classic Tomato & Basil Bruschetta",
      cuisine = FoodCuisine.ITALIAN,
      region = "Central Italy",
      description = "Toasted sourdough ciabatta topped with marinated diced Roma tomatoes, garlic, and balsamic glaze.",
      imageUrl = "https://images.unsplash.com/photo-1572695157366-5e585ab2b69f?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Fresh & Crisp"
    ),
    FoodDish(
      id = "ita_14",
      name = "Zafferano (Saffron) Risotto",
      cuisine = FoodCuisine.ITALIAN,
      region = "Milan",
      description = "Golden glowing arborio rice infused with Spanish saffron threads and aged parmigiano reggiano.",
      imageUrl = "https://images.unsplash.com/photo-1633964913295-ceb43826e7c9?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Golden Glow"
    ),
    FoodDish(
      id = "ita_15",
      name = "Rigatoni alla Vodka (Veg)",
      cuisine = FoodCuisine.ITALIAN,
      region = "Bologna",
      description = "Tube rigatoni pasta tossed in a creamy pink tomato mascarpone reduction with aromatic red herbs.",
      imageUrl = "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Pink Velvet"
    ),
    FoodDish(
      id = "ita_16",
      name = "Caprese Salad with Burrata",
      cuisine = FoodCuisine.ITALIAN,
      region = "Capri",
      description = "Cream filled burrata ball surrounded by heirloom tomatoes, fresh basil, and aged Modena vinegar.",
      imageUrl = "https://images.unsplash.com/photo-1592417817098-8f3d69109853?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cooling Burrata"
    ),
    FoodDish(
      id = "ita_17",
      name = "Mushroom & Fontina Calzone",
      cuisine = FoodCuisine.ITALIAN,
      region = "Napoli",
      description = "Folded pizza pocket stuffed with sautéed wild mushrooms, caramelized shallots, and molten fontina.",
      imageUrl = "https://images.unsplash.com/photo-1604382355076-af4b0eb60143?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Stuffed Pocket"
    ),
    FoodDish(
      id = "ita_18",
      name = "Cacio e Pepe Spaghetti",
      cuisine = FoodCuisine.ITALIAN,
      region = "Rome",
      description = "Three ingredient perfection: pasta, freshly cracked toasted black peppercorns, and pecorino romano.",
      imageUrl = "https://images.unsplash.com/photo-1551183053-bf91a1d81141?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Peppery Comfort"
    ),
    FoodDish(
      id = "ita_19",
      name = "Creamy Polenta with Sautéed Mushrooms",
      cuisine = FoodCuisine.ITALIAN,
      region = "Northern Italy",
      description = "Steaming cornmeal porridge cooked with butter and milk topped with balsamic glazed portobello.",
      imageUrl = "https://images.unsplash.com/photo-1633964913295-ceb43826e7c9?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Porridge"
    ),
    FoodDish(
      id = "ita_20",
      name = "Sun-Dried Tomato & Olive Focaccia",
      cuisine = FoodCuisine.ITALIAN,
      region = "Liguria",
      description = "Thick dimpled olive oil yeast bread studded with rosemary, sea salt, and tangy sun-dried tomatoes.",
      imageUrl = "https://images.unsplash.com/photo-1619535860434-ba1d8fa12536?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Herbed Bread"
    ),
    FoodDish(
      id = "ita_21",
      name = "Minestrone Vegetable Soup",
      cuisine = FoodCuisine.ITALIAN,
      region = "Tuscany",
      description = "Slow simmered white bean, zucchini, tomato, and small shell pasta soup with parmesan rind aroma.",
      imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Nourishing Bowl"
    ),
    FoodDish(
      id = "ita_22",
      name = "Artichoke & Truffle White Pizza",
      cuisine = FoodCuisine.ITALIAN,
      region = "Rome",
      description = "Bianca pizza with garlic cream base, marinated artichoke hearts, mozzarella, and truffle drizzle.",
      imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Gourmet White"
    ),
    FoodDish(
      id = "ita_23",
      name = "Pasta e Fagioli (Pasta & Beans)",
      cuisine = FoodCuisine.ITALIAN,
      region = "Veneto",
      description = "Thick hearty stew of borlotti beans, rosemary, olive oil, and ditalini pasta in tomato broth.",
      imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Warm Pelvic Soother"
    ),
    FoodDish(
      id = "ita_24",
      name = "Tagliolini with Lemon Cream Sauce",
      cuisine = FoodCuisine.ITALIAN,
      region = "Amalfi Coast",
      description = "Fine egg ribbon pasta coated in refreshing Amalfi lemon zest, butter cream, and fresh basil.",
      imageUrl = "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Bright & Uplifting"
    ),
    FoodDish(
      id = "ita_25",
      name = "Parmesan Crusted Polenta Fries",
      cuisine = FoodCuisine.ITALIAN,
      region = "Lombardy",
      description = "Golden crisp polenta batons tossed in rosemary sea salt with spicy arrabbiata dipping sauce.",
      imageUrl = "https://images.unsplash.com/photo-1576107232684-1279f3908594?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crunchy Snack"
    ),

    // -------------------------------------------------------------------------
    // 3. FRENCH CUISINE & BAKERY (15 dishes)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "fre_1",
      name = "Warm Ratatouille & Garlic Sourdough",
      cuisine = FoodCuisine.FRENCH,
      region = "Provence",
      description = "Provence style stewed zucchini, squash, eggplant and bell peppers in herbed San Marzano tomato coulis.",
      imageUrl = "https://images.unsplash.com/photo-1572449043416-55f4685c9bb7?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Buga's Chef Pick",
      isFavorite = true
    ),
    FoodDish(
      id = "fre_2",
      name = "French Onion Soup (Vegetarian Broth)",
      cuisine = FoodCuisine.FRENCH,
      region = "Paris",
      description = "Slow caramelized sweet onions in rich thyme vegetable broth with a toasted Gruyère cheese crouton.",
      imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Deep Warmth"
    ),
    FoodDish(
      id = "fre_3",
      name = "Potato Dauphinoise (Creamy Gratin)",
      cuisine = FoodCuisine.FRENCH,
      region = "Dauphiné",
      description = "Thinly sliced potatoes baked gently in garlic cream and nutmeg with a golden bubbling crust.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Velvet Bake"
    ),
    FoodDish(
      id = "fre_4",
      name = "Vegetable Quiche Florentine",
      cuisine = FoodCuisine.FRENCH,
      region = "Lorraine",
      description = "Buttery flaky pastry crust filled with tender baby spinach, caramelized onions, and cream custard.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Flaky Pastry"
    ),
    FoodDish(
      id = "fre_5",
      name = "Wild Mushroom Vol-au-Vent",
      cuisine = FoodCuisine.FRENCH,
      region = "Bordeaux",
      description = "Puff pastry hollow case filled with creamy chanterelle and button mushrooms scented with tarragon.",
      imageUrl = "https://images.unsplash.com/photo-1619535860434-ba1d8fa12536?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Airy Pastry"
    ),
    FoodDish(
      id = "fre_6",
      name = "Sweet Crepes with Nutella & Strawberries",
      cuisine = FoodCuisine.FRENCH,
      region = "Brittany",
      description = "Delicate thin French crepes smothered in warm hazelnut spread and fresh sliced juicy berries.",
      imageUrl = "https://images.unsplash.com/photo-1519676867240-f03562e64548?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Instant Sweet Smile",
      isFavorite = true
    ),
    FoodDish(
      id = "fre_7",
      name = "Savory Galette Complète (Veggie)",
      cuisine = FoodCuisine.FRENCH,
      region = "Brittany",
      description = "Earthy buckwheat flour pancake filled with melted Emmental cheese, mushrooms, and spinach.",
      imageUrl = "https://images.unsplash.com/photo-1519676867240-f03562e64548?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Buckwheat Goodness"
    ),
    FoodDish(
      id = "fre_8",
      name = "Butter Croissant with Apricot Jam",
      cuisine = FoodCuisine.FRENCH,
      region = "Paris",
      description = "Layer upon layer of golden crisp puff pastry with a cloud soft honeycomb interior and fruit preserve.",
      imageUrl = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Morning Hug"
    ),
    FoodDish(
      id = "fre_9",
      name = "Almond Frangipane Tart",
      cuisine = FoodCuisine.FRENCH,
      region = "Normandy",
      description = "Sweet pastry shell filled with rich almond cream paste, topped with toasted sliced almonds.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Nutty Comfort"
    ),
    FoodDish(
      id = "fre_10",
      name = "Warm Chocolate Éclair",
      cuisine = FoodCuisine.FRENCH,
      region = "Paris",
      description = "Choux dough pastry piped with silky vanilla bean cream and glazed in glossy bittersweet dark chocolate.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Chocolate Treat"
    ),
    FoodDish(
      id = "fre_11",
      name = "Provençal Vegetable Tian",
      cuisine = FoodCuisine.FRENCH,
      region = "Provence",
      description = "Concentric circles of thinly sliced zucchini, tomatoes, and eggplant baked with thyme and garlic oil.",
      imageUrl = "https://images.unsplash.com/photo-1572449043416-55f4685c9bb7?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Clean Nutrition"
    ),
    FoodDish(
      id = "fre_12",
      name = "Pain au Chocolat (Fresh Baked)",
      cuisine = FoodCuisine.FRENCH,
      region = "Paris",
      description = "Warm butter pastry folding over two batons of dark French chocolate, served fresh from the oven.",
      imageUrl = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Baked Bliss"
    ),
    FoodDish(
      id = "fre_13",
      name = "Caramelized Apple Tarte Tatin",
      cuisine = FoodCuisine.FRENCH,
      region = "Loire Valley",
      description = "Upside-down caramelized pastry where apples are caramelized in butter and sugar before baking.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Caramel"
    ),
    FoodDish(
      id = "fre_14",
      name = "French Baguette with Herb Butter",
      cuisine = FoodCuisine.FRENCH,
      region = "Paris",
      description = "Crusty golden baguette sliced warm and paired with whipped parsley sea salt butter.",
      imageUrl = "https://images.unsplash.com/photo-1619535860434-ba1d8fa12536?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crusty Warmth"
    ),
    FoodDish(
      id = "fre_15",
      name = "Lavender Honey & Lemon Madeleines",
      cuisine = FoodCuisine.FRENCH,
      region = "Lorraine",
      description = "Petite French sponge cakes with distinctive shell shape, infused with culinary lavender and lemon glaze.",
      imageUrl = "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Floral Calm"
    ),

    // -------------------------------------------------------------------------
    // 4. MEXICAN & LATIN (15 dishes)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "mex_1",
      name = "Loaded Guacamole & Black Bean Burrito",
      cuisine = FoodCuisine.MEXICAN,
      region = "Mexican",
      description = "Chipotle spiced black beans, lime cilantro rice, charred corn, avocado guacamole in warm tortilla.",
      imageUrl = "https://images.unsplash.com/photo-1626700051175-6818013e1d4f?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Rich Fiber & Good Fats",
      isFavorite = true
    ),
    FoodDish(
      id = "mex_2",
      name = "Three-Cheese Poblano Quesadilla",
      cuisine = FoodCuisine.MEXICAN,
      region = "Oaxaca",
      description = "Golden toasted flour tortilla stuffed with Oaxaca cheese, roasted poblano peppers, and sour cream.",
      imageUrl = "https://images.unsplash.com/photo-1618040996337-56904b7850b9?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Melty & Crisp"
    ),
    FoodDish(
      id = "mex_3",
      name = "Crispy Avocado Tacos with Mango Salsa",
      cuisine = FoodCuisine.MEXICAN,
      region = "Baja",
      description = "Panko crusted avocado slices nestled in warm corn tortillas with zesty sweet mango salsa.",
      imageUrl = "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Tropical Crunch"
    ),
    FoodDish(
      id = "mex_4",
      name = "Fiesta Nachos Supreme with Queso",
      cuisine = FoodCuisine.MEXICAN,
      region = "Tex-Mex",
      description = "Warm stone-ground tortilla chips drenched in creamy jalapeño cheese sauce, black beans, and pico.",
      imageUrl = "https://images.unsplash.com/photo-1513456852971-30c0b8199d4d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Movie Night Must"
    ),
    FoodDish(
      id = "mex_5",
      name = "Vegetarian Enchiladas Verdes",
      cuisine = FoodCuisine.MEXICAN,
      region = "Central Mexico",
      description = "Rolled corn tortillas filled with spinach and sweet corn, baked in tangy roasted tomatillo sauce.",
      imageUrl = "https://images.unsplash.com/photo-1534352956036-cd81e27dd615?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Zesty & Cheesy"
    ),
    FoodDish(
      id = "mex_6",
      name = "Chipotle Sweet Potato & Black Bean Bowl",
      cuisine = FoodCuisine.MEXICAN,
      region = "Modern Mexican",
      description = "Roasted sweet potato cubes, spiced beans, quinoa, creamy lime tahini, and toasted pumpkin seeds.",
      imageUrl = "https://images.unsplash.com/photo-1543339308-43e59d6b73a6?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Nourishing Glow"
    ),
    FoodDish(
      id = "mex_7",
      name = "Street Style Corn in a Cup (Elote)",
      cuisine = FoodCuisine.MEXICAN,
      region = "Mexico City",
      description = "Charred sweet corn kernels with cotija cheese, mild chili powder, cilantro, and fresh lime juice.",
      imageUrl = "https://images.unsplash.com/photo-1551782450-a2132b4ba21d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Sweet & Smokey"
    ),
    FoodDish(
      id = "mex_8",
      name = "Refried Pinto Bean Dip with Tortillas",
      cuisine = FoodCuisine.MEXICAN,
      region = "Northern Mexico",
      description = "Slow cooked pinto beans whipped with olive oil, cumin, and melted cheese, served with warm chips.",
      imageUrl = "https://images.unsplash.com/photo-1513456852971-30c0b8199d4d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Dip"
    ),
    FoodDish(
      id = "mex_9",
      name = "Cinnamon Sugar Churros with Warm Cocoa",
      cuisine = FoodCuisine.MEXICAN,
      region = "Mexico City",
      description = "Crispy golden piped pastry dusted with fragrant Ceylon cinnamon sugar and Mexican dark chocolate dip.",
      imageUrl = "https://images.unsplash.com/photo-1624300629298-e9de39c13be5?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Pure Happiness",
      isFavorite = true
    ),
    FoodDish(
      id = "mex_10",
      name = "Black Bean & Corn Flautas",
      cuisine = FoodCuisine.MEXICAN,
      region = "Sinaloa",
      description = "Crispy rolled corn tortillas filled with spiced beans, topped with shredded lettuce and crema.",
      imageUrl = "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crispy Flutes"
    ),
    FoodDish(
      id = "mex_11",
      name = "Mushroom & Spinach Fajitas Platter",
      cuisine = FoodCuisine.MEXICAN,
      region = "Sonora",
      description = "Sizzling skillet of caramelized onions, bell peppers, portobello mushrooms, served with warm tortillas.",
      imageUrl = "https://images.unsplash.com/photo-1626700051175-6818013e1d4f?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Sizzling Warmth"
    ),
    FoodDish(
      id = "mex_12",
      name = "Tostadas with Refried Beans & Avocado",
      cuisine = FoodCuisine.MEXICAN,
      region = "Oaxaca",
      description = "Crunchy flat corn tortillas layered with creamy beans, crisp shredded cabbage, and sliced avocado.",
      imageUrl = "https://images.unsplash.com/photo-1534352956036-cd81e27dd615?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Crunch & Cream"
    ),
    FoodDish(
      id = "mex_13",
      name = "Mexican Hot Chocolate with Chili & Cinnamon",
      cuisine = FoodCuisine.MEXICAN,
      region = "Puebla",
      description = "Rich dark chocolate frothed with cinnamon, vanilla bean, and a tiny pinch of warming chili.",
      imageUrl = "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warming Spiced Cup"
    ),
    FoodDish(
      id = "mex_14",
      name = "Chuchitos (Vegetarian Steamed Tamales)",
      cuisine = FoodCuisine.MEXICAN,
      region = "Guatemala / Chiapas",
      description = "Masa corn dough parcels filled with herbed tomato sauce and peppers steamed in corn husks.",
      imageUrl = "https://images.unsplash.com/photo-1551782450-a2132b4ba21d?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Steamed Warmth"
    ),
    FoodDish(
      id = "mex_15",
      name = "Sopapillas with Agave & Cinnamon",
      cuisine = FoodCuisine.MEXICAN,
      region = "New Mexico",
      description = "Puffy light golden fried dough pillows served warm with sweet agave nectar drizzle.",
      imageUrl = "https://images.unsplash.com/photo-1624300629298-e9de39c13be5?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Fluffy Sweet Pillows"
    ),

    // -------------------------------------------------------------------------
    // 5. ASIAN & CHINESE (20 dishes)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "asi_1",
      name = "Steamed Vegetable Dim Sum Basket",
      cuisine = FoodCuisine.ASIAN,
      region = "Cantonese",
      description = "6 pieces crystal dumplings packed with edamame, water chestnut, shiitake mushrooms, and sesame oil.",
      imageUrl = "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Light & Steamy",
      isFavorite = true
    ),
    FoodDish(
      id = "asi_2",
      name = "Thai Green Vegetable Curry with Jasmine Rice",
      cuisine = FoodCuisine.ASIAN,
      region = "Thai",
      description = "Fragrant lemongrass, kaffir lime, and coconut milk broth loaded with baby corn, tofu, and bamboo shoots.",
      imageUrl = "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Aromatic Coconut",
      isFavorite = true
    ),
    FoodDish(
      id = "asi_3",
      name = "Comforting Vegetable Miso Ramen",
      cuisine = FoodCuisine.ASIAN,
      region = "Japanese",
      description = "Rich white miso and mushroom dashi broth with springy noodles, bok choy, sweet corn, and nori.",
      imageUrl = "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Steaming Soul Broth"
    ),
    FoodDish(
      id = "asi_4",
      name = "Vegetable Pad Thai with Crushed Peanuts",
      cuisine = FoodCuisine.ASIAN,
      region = "Thai",
      description = "Stir-fried flat rice noodles in tangy tamarind sauce with pressed tofu, bean sprouts, and lime wedge.",
      imageUrl = "https://images.unsplash.com/photo-1559314809-0d155014e29e?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Sweet & Tangy"
    ),
    FoodDish(
      id = "asi_5",
      name = "Crispy Vegetable Spring Rolls",
      cuisine = FoodCuisine.ASIAN,
      region = "Chinese",
      description = "Golden shatteringly crisp pastry rolls filled with glass noodles, cabbage, and sweet chili plum dip.",
      imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Crispy Delight"
    ),
    FoodDish(
      id = "asi_6",
      name = "Crispy Tofu Bao Buns (2 pcs)",
      cuisine = FoodCuisine.ASIAN,
      region = "Taiwanese",
      description = "Cloud soft steamed lotus leaf buns filled with crispy panko tofu, pickled cucumber, and hoisin sauce.",
      imageUrl = "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Pillow Soft"
    ),
    FoodDish(
      id = "asi_7",
      name = "Wok-Tossed Edamame Fried Rice",
      cuisine = FoodCuisine.ASIAN,
      region = "Chinese",
      description = "Jasmine rice stir-fried in smoking wok with ginger, green peas, carrots, sweet corn, and sesame oil.",
      imageUrl = "https://images.unsplash.com/photo-1603133872878-684f208fb84b?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Aromatic Grains"
    ),
    FoodDish(
      id = "asi_8",
      name = "Sweet & Sour Crispy Cauliflower",
      cuisine = FoodCuisine.ASIAN,
      region = "Hong Kong",
      description = "Crisp tempura cauliflower florets tossed with pineapple chunks and peppers in ruby glaze.",
      imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Zesty Crunch"
    ),
    FoodDish(
      id = "asi_9",
      name = "Korean Bibimbap with Gochujang (Veg)",
      cuisine = FoodCuisine.ASIAN,
      region = "Korean",
      description = "Steamed short grain rice topped with seasoned spinach, bean sprouts, shiitake, and sweet spicy sauce.",
      imageUrl = "https://images.unsplash.com/photo-1553163147-622ab57be1c7?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Vibrant Bowl"
    ),
    FoodDish(
      id = "asi_10",
      name = "Silken Tofu & Seaweed Soup",
      cuisine = FoodCuisine.ASIAN,
      region = "Japanese",
      description = "Delicate comforting warm broth with cubes of silken tofu, scallions, and wakame sea greens.",
      imageUrl = "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Gentle Hydration"
    ),
    FoodDish(
      id = "asi_11",
      name = "Dan Dan Noodles with Crushed Peanuts (Veg)",
      cuisine = FoodCuisine.ASIAN,
      region = "Sichuan",
      description = "Chewy noodles tossed in spicy toasted sesame oil, preserved mustard greens, and toasted peanuts.",
      imageUrl = "https://images.unsplash.com/photo-1585032226651-759b368d7246?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Fiery & Nutty"
    ),
    FoodDish(
      id = "asi_12",
      name = "Mango Sticky Rice with Warm Coconut Cream",
      cuisine = FoodCuisine.ASIAN,
      region = "Thai",
      description = "Sweet glutinous rice steamed in coconut milk served with fresh fragrant honey mango slices.",
      imageUrl = "https://images.unsplash.com/photo-1519676867240-f03562e64548?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Tropical Heaven",
      isFavorite = true
    ),
    FoodDish(
      id = "asi_13",
      name = "Japanese Vegetable Curry & Rice",
      cuisine = FoodCuisine.ASIAN,
      region = "Japanese",
      description = "Rich, thick, and mildly sweet brown curry with tender carrots and potatoes over steamed rice.",
      imageUrl = "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Hearty & Mild"
    ),
    FoodDish(
      id = "asi_14",
      name = "Korean Tteokbokki in Mild Sweet Sauce",
      cuisine = FoodCuisine.ASIAN,
      region = "Korean",
      description = "Chewy cylinder rice cakes simmered in sweet savory fermented chili broth with cabbage.",
      imageUrl = "https://images.unsplash.com/photo-1553163147-622ab57be1c7?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Chewy Comfort"
    ),
    FoodDish(
      id = "asi_15",
      name = "Tom Kha Hed (Coconut Galangal Soup)",
      cuisine = FoodCuisine.ASIAN,
      region = "Thai",
      description = "Velvety coconut milk soup infused with fresh galangal, lemongrass, lime leaf, and oyster mushrooms.",
      imageUrl = "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Soothing Immunity"
    ),
    FoodDish(
      id = "asi_16",
      name = "Vegetable Yakisoba Noodles",
      cuisine = FoodCuisine.ASIAN,
      region = "Japanese",
      description = "Stir-fried noodles with crunchy cabbage, shiitake, pickled red ginger, and savory Worcestershire.",
      imageUrl = "https://images.unsplash.com/photo-1585032226651-759b368d7246?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Savory Wok"
    ),
    FoodDish(
      id = "asi_17",
      name = "Vietnamese Rice Paper Fresh Spring Rolls",
      cuisine = FoodCuisine.ASIAN,
      region = "Vietnamese",
      description = "Translucent rice wrappers filled with fresh mint, basil, rice vermicelli, cucumber, and peanut dip.",
      imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Fresh & Crisp"
    ),
    FoodDish(
      id = "asi_18",
      name = "Vegetable Udon Noodle Soup",
      cuisine = FoodCuisine.ASIAN,
      region = "Japanese",
      description = "Thick chewy wheat noodles in steaming kombu kelp broth topped with tempura flakes and scallions.",
      imageUrl = "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Warm Slurp"
    ),
    FoodDish(
      id = "asi_19",
      name = "Scallion Pancakes (Flaky Layered)",
      cuisine = FoodCuisine.ASIAN,
      region = "Taiwanese",
      description = "Chewy layered flatbread folded with fresh scallions and sesame oil, pan-fried to crisp perfection.",
      imageUrl = "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Flaky Crisp"
    ),
    FoodDish(
      id = "asi_20",
      name = "Matcha Green Tea Steamed Buns",
      cuisine = FoodCuisine.ASIAN,
      region = "Kyoto",
      description = "Fluffy green tea bao filled with sweet red azuki bean paste, served warm.",
      imageUrl = "https://images.unsplash.com/photo-1563245372-f21724e3856d?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Zen Sweetness"
    ),

    // -------------------------------------------------------------------------
    // 6. MEDITERRANEAN & MIDDLE EASTERN (15 dishes)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "med_1",
      name = "Falafel Platter with Creamy Hummus",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Levantine",
      description = "Crispy herbed chickpea fritters, silky sesame tahini hummus, pickled turnips, and warm pita.",
      imageUrl = "https://images.unsplash.com/photo-1593001874117-c99c800e3eb7?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Plant Protein",
      isFavorite = true
    ),
    FoodDish(
      id = "med_2",
      name = "Greek Spanakopita (Spinach & Feta Pie)",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Greek",
      description = "Golden crisp layers of phyllo pastry filled with fresh spinach, dill, and tangy crumbled feta cheese.",
      imageUrl = "https://images.unsplash.com/photo-1608897013039-887f21d8c804?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crispy Phyllo"
    ),
    FoodDish(
      id = "med_3",
      name = "Authentic Greek Salad with Kalamata Olives",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Greek Islands",
      description = "Vine ripe tomatoes, crisp cucumber, red onions, Greek feta block, dried oregano, and olive oil.",
      imageUrl = "https://images.unsplash.com/photo-1540420773420-3366772f4999?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crisp & Clean"
    ),
    FoodDish(
      id = "med_4",
      name = "Roasted Eggplant Baba Ganoush with Pita",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Lebanese",
      description = "Smoky charred eggplant blended with rich tahini, garlic, lemon, and pomegranate seeds.",
      imageUrl = "https://images.unsplash.com/photo-1593001874117-c99c800e3eb7?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Smoky Velvet"
    ),
    FoodDish(
      id = "med_5",
      name = "Shakshuka with Paneer Cubes (Egg-Free)",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "North African",
      description = "Richly spiced tomato and roasted red bell pepper stew topped with seared cottage cheese cubes.",
      imageUrl = "https://images.unsplash.com/photo-1590412200988-a436970781fa?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warming Spices",
      isFavorite = true
    ),
    FoodDish(
      id = "med_6",
      name = "Stuffed Grape Leaves (Dolmas)",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Greek / Turkish",
      description = "Tender vine leaves rolled with herb-infused rice, pine nuts, currants, and lemon olive oil.",
      imageUrl = "https://images.unsplash.com/photo-1540420773420-3366772f4999?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Delicate Herb"
    ),
    FoodDish(
      id = "med_7",
      name = "Warm Halloumi Skewers with Mint Pesto",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Cypriot",
      description = "Golden seared squeaky halloumi cheese with grilled cherry tomatoes and refreshing mint olive drizzle.",
      imageUrl = "https://images.unsplash.com/photo-1592417817098-8f3d69109853?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cheesy & Warm"
    ),
    FoodDish(
      id = "med_8",
      name = "Turkish Pide Flatbread with Spinach & Cheese",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Turkish",
      description = "Boat-shaped artisan flatbread stuffed with spiced spinach, mozzarella, and feta cheese.",
      imageUrl = "https://images.unsplash.com/photo-1608897013039-887f21d8c804?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Boat Flatbread"
    ),
    FoodDish(
      id = "med_9",
      name = "Warm Lentil & Lemon Shorba",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Middle Eastern",
      description = "Golden Egyptian red lentil soup cooked with cumin, carrots, and topped with crispy pita chips.",
      imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Gentle Soup"
    ),
    FoodDish(
      id = "med_10",
      name = "Mujadara (Lentil, Rice & Crispy Onions)",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Levantine",
      description = "Spiced brown lentils and basmati rice crowned with mountain of deep caramelized sweet onions.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Ancient Comfort"
    ),
    FoodDish(
      id = "med_11",
      name = "Pistachio Honey Baklava",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Turkish",
      description = "Crisp, flaky layers of phyllo dough stuffed with crushed pistachios and drenched in rosewater syrup.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crispy Honey Sweet"
    ),
    FoodDish(
      id = "med_12",
      name = "Lebanese Tabbouleh Herb Salad",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Lebanese",
      description = "Finely chopped flat leaf parsley, fresh mint, diced tomatoes, bulgur, and extra virgin olive oil.",
      imageUrl = "https://images.unsplash.com/photo-1540420773420-3366772f4999?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Vitamin Rich"
    ),
    FoodDish(
      id = "med_13",
      name = "Briam (Greek Roasted Vegetables)",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Greek",
      description = "Potatoes, zucchini, red onions, and bell peppers roasted in generous olive oil, garlic, and oregano.",
      imageUrl = "https://images.unsplash.com/photo-1572449043416-55f4685c9bb7?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Rustic Roasted"
    ),
    FoodDish(
      id = "med_14",
      name = "Pita Bread Pocket with Grilled Halloumi",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Levantine",
      description = "Freshly baked puffed pita stuffed with seared halloumi, cucumbers, mint leaves, and za'atar.",
      imageUrl = "https://images.unsplash.com/photo-1593001874117-c99c800e3eb7?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Pocket of Love"
    ),
    FoodDish(
      id = "med_15",
      name = "Moroccan Vegetable Tagine with Couscous",
      cuisine = FoodCuisine.MEDITERRANEAN,
      region = "Moroccan",
      description = "Slow cooked chickpeas, apricots, sweet carrots, and zucchini seasoned with ras el hanout.",
      imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Exotic Warmth"
    ),

    // -------------------------------------------------------------------------
    // 7. AMERICAN COMFORT & BURGERS (15 dishes)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "ame_1",
      name = "Gourmet Truffle Mushroom Veggie Burger",
      cuisine = FoodCuisine.AMERICAN,
      region = "Classic Diner",
      description = "House made black bean & quinoa patty, sautéed portobello, Swiss cheese, and truffle aioli on brioche.",
      imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Juicy & Satisfying",
      isFavorite = true
    ),
    FoodDish(
      id = "ame_2",
      name = "Three-Cheese Baked Mac & Cheese",
      cuisine = FoodCuisine.AMERICAN,
      region = "Southern Comfort",
      description = "Elbow macaroni enveloped in sharp cheddar, gruyère, and gouda cheese sauce with toasted breadcrumbs.",
      imageUrl = "https://images.unsplash.com/photo-1543339308-43e59d6b73a6?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Ultimate Comfort",
      isFavorite = true
    ),
    FoodDish(
      id = "ame_3",
      name = "Artisan Grilled Cheese & Creamy Tomato Soup",
      cuisine = FoodCuisine.AMERICAN,
      region = "Cozy Classic",
      description = "Golden butter-griddled sourdough bursting with aged cheddar, paired with roasted tomato basil soup.",
      imageUrl = "https://images.unsplash.com/photo-1528735602780-2552fd46c7af?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Soul Soother",
      isFavorite = true
    ),
    FoodDish(
      id = "ame_4",
      name = "Crispy Sweet Potato Fries with Honey Mustard",
      cuisine = FoodCuisine.AMERICAN,
      region = "Diner Favorite",
      description = "Golden sweet potato batons sprinkled with sea salt and smoked paprika, served with creamy dip.",
      imageUrl = "https://images.unsplash.com/photo-1576107232684-1279f3908594?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Sweet & Salty"
    ),
    FoodDish(
      id = "ame_5",
      name = "Loaded Vegetarian Chili with Jalapeño Cornbread",
      cuisine = FoodCuisine.AMERICAN,
      region = "Texas Style",
      description = "Rich smoky three-bean and corn chili simmered with cocoa, served with sweet warm honey cornbread.",
      imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm & Hearty"
    ),
    FoodDish(
      id = "ame_6",
      name = "Avocado Toast on Sourdough with Seeds",
      cuisine = FoodCuisine.AMERICAN,
      region = "California",
      description = "Thick toasted sourdough smothered in mashed Haas avocado, hemp hearts, chili flakes, and lemon.",
      imageUrl = "https://images.unsplash.com/photo-1525351484163-7529414344d8?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Clean Glow"
    ),
    FoodDish(
      id = "ame_7",
      name = "Golden Beer-Battered Onion Rings",
      cuisine = FoodCuisine.AMERICAN,
      region = "Midwest Diner",
      description = "Colossal sweet onions dunked in light batter and fried crispy, served with smoky BBQ ranch dip.",
      imageUrl = "https://images.unsplash.com/photo-1639024471285-05c875691168?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crispy Crunch"
    ),
    FoodDish(
      id = "ame_8",
      name = "BBQ Pulled Jackfruit Burger",
      cuisine = FoodCuisine.AMERICAN,
      region = "Southern",
      description = "Tender shredded young jackfruit slow simmered in sweet smoky barbecue sauce with crisp coleslaw.",
      imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Smoky BBQ"
    ),
    FoodDish(
      id = "ame_9",
      name = "Warm Apple Crisp with Vanilla Ice Cream",
      cuisine = FoodCuisine.AMERICAN,
      region = "New England",
      description = "Cinnamon spiced baked granny smith apples under a crunchy brown sugar oat streusel with cold cream.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm & Cold Bliss",
      isFavorite = true
    ),
    FoodDish(
      id = "ame_10",
      name = "Fluffy Buttermilk Pancakes with Maple Syrup",
      cuisine = FoodCuisine.AMERICAN,
      region = "Diner Classic",
      description = "Triple stack of cloud-like golden pancakes topped with melting butter and pure Vermont amber syrup.",
      imageUrl = "https://images.unsplash.com/photo-1528207776546-365bb710ee93?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Sunday Morning"
    ),
    FoodDish(
      id = "ame_11",
      name = "Crispy Mozzarella Sticks with Marinara",
      cuisine = FoodCuisine.AMERICAN,
      region = "Sports Bar Classic",
      description = "Herbed Italian breaded mozzarella sticks with a molten stringy cheese pull, served warm.",
      imageUrl = "https://images.unsplash.com/photo-1531749668029-2db88e4276c7?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cheese Pull"
    ),
    FoodDish(
      id = "ame_12",
      name = "New York Style Soft Pretzels with Beer Cheese",
      cuisine = FoodCuisine.AMERICAN,
      region = "New York",
      description = "Oven fresh salted pretzel knot with chewy crust and fluffy center, served with warm cheddar dip.",
      imageUrl = "https://images.unsplash.com/photo-1568471173242-461f0a730452?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Bakery"
    ),
    FoodDish(
      id = "ame_13",
      name = "Classic Caesar Salad with Garlic Croutons (Veg)",
      cuisine = FoodCuisine.AMERICAN,
      region = "Classic",
      description = "Crisp Romaine lettuce hearts, aged parmesan ribbons, seasoned croutons, and creamy vegetarian Caesar dressing.",
      imageUrl = "https://images.unsplash.com/photo-1540420773420-3366772f4999?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crisp & Punchy"
    ),
    FoodDish(
      id = "ame_14",
      name = "Black Bean Burger with Chipotle Mayo",
      cuisine = FoodCuisine.AMERICAN,
      region = "West Coast",
      description = "Spiced black bean and sweet corn patty with butterhead lettuce, sliced tomato, and brioche bun.",
      imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Protein Fuel"
    ),
    FoodDish(
      id = "ame_15",
      name = "Cheddar & Broccoli Soup in Sourdough Bowl",
      cuisine = FoodCuisine.AMERICAN,
      region = "San Francisco",
      description = "Velvety sharp cheddar cheese soup with tender florets of broccoli served inside a hollowed round bread.",
      imageUrl = "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Bread Bowl Joy"
    ),

    // -------------------------------------------------------------------------
    // 8. DESSERTS, BAKERY & SWEETS (20 dishes)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "des_1",
      name = "Warm Molten Belgian Chocolate Lava Cake",
      cuisine = FoodCuisine.DESSERTS,
      region = "Bakery",
      description = "Rich 70% dark cocoa sponge with an oozy chocolate fudge center, dusted with snowy sugar.",
      imageUrl = "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cramp relief & Happy",
      isFavorite = true
    ),
    FoodDish(
      id = "des_2",
      name = "Classic Italian Tiramisu (Egg-Free)",
      cuisine = FoodCuisine.DESSERTS,
      region = "Veneto",
      description = "Espresso-soaked ladyfingers layered with velvety sweet mascarpone cream and cocoa dust.",
      imageUrl = "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Velvet Delight",
      isFavorite = true
    ),
    FoodDish(
      id = "des_3",
      name = "Warm Fudgy Brownie with Vanilla Gelato",
      cuisine = FoodCuisine.DESSERTS,
      region = "Artisan Bakery",
      description = "Dense chocolate fudge brownie served hot out of the oven alongside Madagascar vanilla bean ice cream.",
      imageUrl = "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Hot & Cold Perfection",
      isFavorite = true
    ),
    FoodDish(
      id = "des_4",
      name = "New York Strawberry Cheesecake (Egg-Free)",
      cuisine = FoodCuisine.DESSERTS,
      region = "New York",
      description = "Creamy baked cheesecake on buttery graham cracker crust topped with ruby strawberry compote.",
      imageUrl = "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Creamy & Tart"
    ),
    FoodDish(
      id = "des_5",
      name = "Royal Kesari Rasmalai",
      cuisine = FoodCuisine.DESSERTS,
      region = "Bengali",
      description = "Pillowy cottage cheese discs soaked in saffron-cardamom clotted milk, garnished with sliced pistachios.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Silken Sweet"
    ),
    FoodDish(
      id = "des_6",
      name = "Warm Gulab Jamun with Rabdi",
      cuisine = FoodCuisine.DESSERTS,
      region = "North Indian",
      description = "Golden fried milk solid dumplings soaked in rose syrup, paired with slow simmered condensed milk rabdi.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cozy Sweetness"
    ),
    FoodDish(
      id = "des_7",
      name = "Belgian Waffles with Nutella & Berries",
      cuisine = FoodCuisine.DESSERTS,
      region = "Belgian",
      description = "Deep pocketed crisp golden waffles loaded with hazelnut spread, fresh raspberries, and maple.",
      imageUrl = "https://images.unsplash.com/photo-1562376552-0d160a2f238d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Crispy Waffle"
    ),
    FoodDish(
      id = "des_8",
      name = "Dark Chocolate Sea Salt Gelato",
      cuisine = FoodCuisine.DESSERTS,
      region = "Italian",
      description = "Ultra dense and creamy dark chocolate gelato finished with flaky Maldon sea salt crystals.",
      imageUrl = "https://images.unsplash.com/photo-1567206563064-6f60f40a2b57?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Rich Chilled"
    ),
    FoodDish(
      id = "des_9",
      name = "Warm Cinnamon Roll with Cream Cheese Icing",
      cuisine = FoodCuisine.DESSERTS,
      region = "Swedish / American",
      description = "Oven warm spiral yeast dough filled with brown sugar cinnamon, covered in melting white glaze.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Aromatic Spiral",
      isFavorite = true
    ),
    FoodDish(
      id = "des_10",
      name = "Red Velvet Cupcake with Swirled Frosting",
      cuisine = FoodCuisine.DESSERTS,
      region = "Southern Bakery",
      description = "Velvety cocoa crumb sponge crowned with tangy vanilla cream cheese swirl.",
      imageUrl = "https://images.unsplash.com/photo-1587668178277-295251f900ce?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Sweet Little Joy"
    ),
    FoodDish(
      id = "des_11",
      name = "Warm Blueberries & Cream Scones",
      cuisine = FoodCuisine.DESSERTS,
      region = "British",
      description = "Crumbly tender baked pastry packed with sweet blueberries, served with clotted cream and jam.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Teatime Treat"
    ),
    FoodDish(
      id = "des_12",
      name = "Mango Passion Fruit Panna Cotta (Veg Agar)",
      cuisine = FoodCuisine.DESSERTS,
      region = "Italian",
      description = "Silky vanilla cream set with plant agar, topped with vibrant mango and passion fruit coulis.",
      imageUrl = "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Silky & Tropical"
    ),
    FoodDish(
      id = "des_13",
      name = "Warm Sticky Toffee Pudding",
      cuisine = FoodCuisine.DESSERTS,
      region = "British",
      description = "Dense date sponge soaked in buttery warm caramel toffee sauce with a scoop of custard.",
      imageUrl = "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Toffee Blanket"
    ),
    FoodDish(
      id = "des_14",
      name = "Pistachio Kulfi on a Stick",
      cuisine = FoodCuisine.DESSERTS,
      region = "North Indian",
      description = "Traditional slow simmered caramelized milk ice cream dense with saffron, cardamom, and nuts.",
      imageUrl = "https://images.unsplash.com/photo-1567206563064-6f60f40a2b57?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Rich Traditional"
    ),
    FoodDish(
      id = "des_15",
      name = "Alfonso Mango Sorbet",
      cuisine = FoodCuisine.DESSERTS,
      region = "Artisan Churn",
      description = "Pure sweet Ratnagiri Alphonso mango pulp churned into a refreshing, dairy-free frozen sorbet.",
      imageUrl = "https://images.unsplash.com/photo-1567206563064-6f60f40a2b57?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Pure Fruit"
    ),
    FoodDish(
      id = "des_16",
      name = "Dutch Stroopwafel (Warmed over Coffee)",
      cuisine = FoodCuisine.DESSERTS,
      region = "Dutch",
      description = "Thin waffle wafer with gooey cinnamon caramel syrup center, warmed to melting perfection.",
      imageUrl = "https://images.unsplash.com/photo-1562376552-0d160a2f238d?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Melting Caramel"
    ),
    FoodDish(
      id = "des_17",
      name = "Warm Banana Bread Slice with Walnut Butter",
      cuisine = FoodCuisine.DESSERTS,
      region = "Home Bakery",
      description = "Super moist ripe banana loaf with toasted walnuts, served thick sliced and toasted warm.",
      imageUrl = "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Like Home"
    ),
    FoodDish(
      id = "des_18",
      name = "Lemon Olive Oil Cake",
      cuisine = FoodCuisine.DESSERTS,
      region = "Mediterranean",
      description = "Moist crumb sponge fragrant with Meyer lemon zest and mild Italian olive oil, lightly glazed.",
      imageUrl = "https://images.unsplash.com/photo-1587668178277-295251f900ce?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Gentle Citrus"
    ),
    FoodDish(
      id = "des_19",
      name = "Double Chocolate Chunk Soft Cookie",
      cuisine = FoodCuisine.DESSERTS,
      region = "Bakery",
      description = "Gooey centered, crispy edged dark and milk chocolate cookie served warm with chocolate pools.",
      imageUrl = "https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warm Cookie"
    ),
    FoodDish(
      id = "des_20",
      name = "Rose Petal Gulkand Kheer",
      cuisine = FoodCuisine.DESSERTS,
      region = "Indian",
      description = "Fragrant rice pudding infused with sun-cooked damask rose petals, milk, and silver almonds.",
      imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Romantic Rose"
    ),

    // -------------------------------------------------------------------------
    // 9. BEVERAGES & SOOTHING TEAS (15 items)
    // -------------------------------------------------------------------------
    FoodDish(
      id = "bev_1",
      name = "Sweet Hot Chocolate with Fluffy Marshmallows",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Cozy Cafe",
      description = "Velvet dark hot cocoa simmered with creamy oat milk and crowned with pillowy marshmallows.",
      imageUrl = "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Instant mood lifter",
      isFavorite = true
    ),
    FoodDish(
      id = "bev_2",
      name = "Chamomile & Lavender Soothing Tea",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Herbal Apothecary",
      description = "Whole organic chamomile blossoms paired with French lavender buds and a swirl of wildflower honey.",
      imageUrl = "https://images.unsplash.com/photo-1576092768241-dec231879fc3?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Cramp Ease & Sleep"
    ),
    FoodDish(
      id = "bev_3",
      name = "Fresh Ginger & Cardamom Masala Chai",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Indian Street",
      description = "Freshly crushed ginger root and green cardamom brewed with robust Assam tea and whole milk.",
      imageUrl = "https://images.unsplash.com/photo-1576092768241-dec231879fc3?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Warming Energy"
    ),
    FoodDish(
      id = "bev_4",
      name = "Peppermint Lemon Digestif Tea",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Herbal",
      description = "Crisp refreshing peppermint leaves brewed with a lemon slice to relieve bloating and tummy heaviness.",
      imageUrl = "https://images.unsplash.com/photo-1576092768241-dec231879fc3?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Anti-Bloat Soother"
    ),
    FoodDish(
      id = "bev_5",
      name = "Golden Turmeric Ashwagandha Latte",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Ayurvedic",
      description = "Steamed almond milk with organic turmeric root, adaptogenic ashwagandha, black pepper, and cinnamon.",
      imageUrl = "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Deep Restoration"
    ),
    FoodDish(
      id = "bev_6",
      name = "Rose Water & Cardamom Sweet Lassi",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Punjabi",
      description = "Thick churned creamy yogurt whipped with fragrant rose syrup, crushed cardamom, and pistachio slivers.",
      imageUrl = "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Cooling & Romantic"
    ),
    FoodDish(
      id = "bev_7",
      name = "Sparkling Fresh Mint & Lime Mojito (Virgin)",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Tropical",
      description = "Crushed garden spearmint muddled with Key limes, raw cane sugar, and effervescent sparkling soda.",
      imageUrl = "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Zesty & Uplifting"
    ),
    FoodDish(
      id = "bev_8",
      name = "Iced Peach White Tea",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Artisan Brew",
      description = "Delicate white tea infused with sweet peach nectar and served over hand carved clear ice cubes.",
      imageUrl = "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Gentle Fruity"
    ),
    FoodDish(
      id = "bev_9",
      name = "Kyoto Ceremonial Matcha Oat Latte",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Japanese",
      description = "Vibrant stone ground Uji matcha whisked into velvety steamed oat milk with a hint of vanilla.",
      imageUrl = "https://images.unsplash.com/photo-1536256263959-770b48d82b0a?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Focused Calm"
    ),
    FoodDish(
      id = "bev_10",
      name = "Fresh Pressed Watermelon & Mint Cooler",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Summer Harvest",
      description = "Cold pressed sweet ruby watermelon juice with fresh mint and a touch of Himalayan pink salt.",
      imageUrl = "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Pure Hydration"
    ),
    FoodDish(
      id = "bev_11",
      name = "Warm Spiced Apple Cider",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Orchard Classic",
      description = "Pressed unfiltered apple juice simmered with whole cinnamon sticks, cloves, star anise, and orange.",
      imageUrl = "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Autumn Blanket"
    ),
    FoodDish(
      id = "bev_12",
      name = "Raspberry Hibiscus Iced Elixir",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Botanical",
      description = "Tart and ruby red steeped hibiscus flower tea sweetened with wild raspberries and agave.",
      imageUrl = "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Antioxidant Glow"
    ),
    FoodDish(
      id = "bev_13",
      name = "Tender Coconut Water with Flesh",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Tropical Coastal",
      description = "Natural isotonic pure coconut water with delicate spoonable coconut jelly flesh.",
      imageUrl = "https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?w=600&auto=format&fit=crop&q=80",
      isVegan = true,
      comfortBadge = "Electrolyte Boost"
    ),
    FoodDish(
      id = "bev_14",
      name = "Salted Caramel Cold Brew with Sweet Cream",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Modern Cafe",
      description = "18-hour steeped smooth cold brew coffee topped with thick salted caramel cold foam.",
      imageUrl = "https://images.unsplash.com/photo-1517256064527-09c73fc73e38?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Gentle Afternoon Pick"
    ),
    FoodDish(
      id = "bev_15",
      name = "Warm Lavender Milk with Honey (Nightcap)",
      cuisine = FoodCuisine.BEVERAGES,
      region = "Bedtime Elixir",
      description = "Warm whole milk steeped with lavender flowers and raw honey to prepare for deep comforting sleep.",
      imageUrl = "https://images.unsplash.com/photo-1542990253-0d0f5be5f0ed?w=600&auto=format&fit=crop&q=80",
      comfortBadge = "Deep Dreamy Sleep",
      isFavorite = true
    )
  )
}
