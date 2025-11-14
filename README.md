# 🎮 Jetpack Compose Pokédex

A modern Android application built with **Jetpack Compose** that displays Pokémon data from the [PokéAPI](https://pokeapi.co/). Browse through all Pokémon, view detailed stats, types, and more in a beautiful, Material Design interface.

---

## 📱 Features

### 🏠 Pokemon List Screen
- **Grid Layout**: Displays Pokémon in a 2-column grid format
- **Infinite Scrolling**: Pagination support to load Pokémon as you scroll
- **Dynamic Colors**: Each Pokémon card shows the dominant color extracted from its sprite
- **Search Functionality**: Search bar to find Pokémon by name or number
- **Smooth Animations**: Card animations and transitions
- **Pokemon Cards**: Each card displays:
  - Pokémon sprite image
  - Pokémon name (capitalized)
  - Beautiful gradient background based on dominant color

### 🔍 Pokemon Detail Screen
- **Comprehensive Stats Display**: View all base stats with animated progress bars
- **Pokemon Information**:
  - Pokémon ID and Name (e.g., "#18 Pidgeot")
  - High-quality sprite image
  - Type badges with color-coded backgrounds
  - Weight (in kg) with icon
  - Height (in meters) with icon
- **Base Stats Visualization**:
  - HP (Yellow) ⚡
  - Attack (Red) 🗡️
  - Defense (Blue) 🛡️
  - Special Attack (Purple) ✨
  - Special Defense (Pink) 💫
  - Speed (Green) 💨
- **Interactive Elements**:
  - Back button for easy navigation
  - Scrollable content
  - Animated stat bars on screen load
- **Visual Design**:
  - Background color matches Pokémon's dominant color
  - White card with rounded corners for readability
  - Material Design components

---

## 🏗️ Architecture & Tech Stack

### **Modern Android Development**
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (1.4.3)
- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 33 (Android 13)

### **Architecture Pattern**
- **MVVM (Model-View-ViewModel)**: Clean separation of concerns
- **Repository Pattern**: Centralized data management
- **Unidirectional Data Flow**: Predictable state management

### **Dependency Injection**
- **Dagger Hilt (2.44)**: For dependency injection
- **@HiltViewModel**: ViewModel injection
- **@Inject**: Constructor injection

### **Networking**
- **Retrofit (2.9.0)**: REST API client
- **Gson Converter**: JSON serialization/deserialization
- **OkHttp (4.9.0)**: HTTP client
- **Logging Interceptor**: Network request/response logging

### **Asynchronous Programming**
- **Kotlin Coroutines (1.7.1)**: For asynchronous operations
- **Flow**: Reactive data streams
- **ViewModelScope**: Lifecycle-aware coroutines

### **Image Loading**
- **Coil (2.4.0)**: Modern image loading library for Compose
- **SubcomposeAsyncImage**: Async image loading with Compose

### **Navigation**
- **Navigation Compose (2.6.0)**: Type-safe navigation
- **NavHost**: Navigation graph management
- **Deep linking support**: Navigate with parameters (color, name)

### **UI & Design**
- **Material Design 3**: Modern Material components
- **Palette API**: Extract dominant colors from images
- **Custom Color Schemes**: Type-specific colors (Fire, Water, Grass, etc.)
- **Animated Progress Bars**: Smooth stat animations

### **Additional Libraries**
- **Timber (4.7.1)**: Logging utility
- **ConstraintLayout Compose (1.0.1)**: Advanced layouts
- **AndroidX Core KTX (1.10.1)**: Kotlin extensions

---

## 📂 Project Structure

```
app/
├── src/main/java/com/plcoding/jetpackcomposepokedex/
│   ├── data/
│   │   ├── model/
│   │   │   └── PokemonListEntry.kt          # Pokemon list item data model
│   │   └── remote/
│   │       ├── PokeApi.kt                   # Retrofit API interface
│   │       └── responses/                   # API response models
│   │           ├── pokemons.kt              # Complete Pokemon data model
│   │           ├── Stat.kt                  # Pokemon stats
│   │           ├── Type.kt                  # Pokemon types
│   │           └── ... (other response models)
│   │
│   ├── di/
│   │   └── AppModule.kt                     # Hilt dependency injection module
│   │
│   ├── PokemonList/
│   │   ├── pokemonListScreen.kt             # Home screen UI
│   │   └── PoketmonListViewModel.kt         # List screen ViewModel
│   │
│   ├── PokedomDetail/
│   │   ├── pokemonDetailScreen.kt           # Detail screen UI
│   │   └── PokemonDetailsViewModel.kt       # Detail screen ViewModel
│   │
│   ├── repository/
│   │   └── PoketmonRepository.kt            # Data repository
│   │
│   ├── ui/theme/
│   │   ├── Color.kt                         # App color palette
│   │   ├── Theme.kt                         # App theme
│   │   ├── Type.kt                          # Typography
│   │   └── Shape.kt                         # Shapes
│   │
│   ├── util/
│   │   ├── Constant.kt                      # App constants
│   │   ├── Resource.kt                      # Sealed class for API states
│   │   └── PokemonParse.kt                  # Utility functions
│   │
│   ├── MainActivity.kt                      # Main activity & navigation
│   └── pokedexApplication.kt                # Application class
│
└── res/
    ├── drawable/
    │   ├── ic_weight.xml                    # Weight icon
    │   ├── ic_height.xml                    # Height icon
    │   └── ic_international_pok_mon_logo.xml # Pokemon logo
    └── values/
        ├── colors.xml                       # Color resources
        ├── strings.xml                      # String resources
        └── themes.xml                       # Theme resources
```

---

## 🎨 Color System

### **Pokemon Type Colors**
- 🟤 Normal: `#A8A77A`
- 🔥 Fire: `#EE8130`
- 💧 Water: `#6390F0`
- ⚡ Electric: `#F7D02C`
- 🌿 Grass: `#7AC74C`
- ❄️ Ice: `#96D9D6`
- 🥊 Fighting: `#C22E28`
- ☠️ Poison: `#A33EA1`
- 🌍 Ground: `#E2BF65`
- 🕊️ Flying: `#A98FF3`
- 🔮 Psychic: `#F95587`
- 🐛 Bug: `#A6B91A`
- 🪨 Rock: `#B6A136`
- 👻 Ghost: `#735797`
- 🐉 Dragon: `#6F35FC`
- 🌑 Dark: `#705746`
- ⚙️ Steel: `#B7B7CE`
- 🧚 Fairy: `#D685AD`

### **Stat Colors**
- HP: Yellow (`#F5FF00`)
- Attack: Red (with 66% opacity)
- Defense: Blue (with 44% opacity)
- Special Attack: Purple (with 57% opacity)
- Special Defense: Pink (with 70% opacity)
- Speed: Green (with 55% opacity)

---

## 🚀 Key Implementations

### **1. Pagination & Infinite Scrolling**
```kotlin
// Automatically loads more Pokemon as user scrolls
if (it >= itemCount - 1 && !endReached) {
    viewModel.loadPokemonPaginated()
}
```

### **2. Dominant Color Extraction**
```kotlin
// Extracts dominant color from Pokemon sprite
fun calDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
    val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
    Palette.from(bitmap).generate { palette ->
        palette?.dominantSwatch?.rgb?.let { colorValue ->
            onFinish(Color(colorValue))
        }
    }
}
```

### **3. Type-Safe Navigation**
```kotlin
composable(
    "pokemon_detail_screen/{dominantColor}/{pokemonName}",
    arguments = listOf(
        navArgument("dominantColor") { type = NavType.IntType },
        navArgument("pokemonName") { type = NavType.StringType }
    )
) { backStackEntry ->
    val dominantColor = remember {
        val color = backStackEntry.arguments?.getInt("dominantColor")
        color?.let { Color(it) } ?: Color.White
    }
    val pokemonName = remember {
        backStackEntry.arguments?.getString("pokemonName")
    }
    pokemonDetailscreen(
        dominantColor = dominantColor,
        pokemonName = pokemonName?.lowercase() ?: "",
        navController = navController
    )
}
```

### **4. Animated Stats**
```kotlin
// Stats animate with delay for a cascading effect
val curPercent = animateFloatAsState(
    targetValue = if(animationPlayed) {
        statValue / statMaxValue.toFloat()
    } else 0f,
    animationSpec = tween(
        animDuration,
        animDelay
    )
)
```

### **5. Grid Layout with LazyColumn**
```kotlin
// Two-column grid using row-based approach
LazyColumn(
    contentPadding = PaddingValues(vertical = 8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(itemCount) {
        pokedexRow(rowIndex = it, entries = pokemonList, navController)
    }
}
```

---

## 🔧 Build Configuration

### **Gradle Configuration**
- **Android Gradle Plugin**: 7.4.2
- **Kotlin**: 1.8.10
- **Gradle**: 7.5
- **Java Version**: 11

### **Compose Compiler**
- **Compose Version**: 1.4.3
- **Kotlin Compiler Extension**: 1.4.3

---

## 📦 Installation

### **Prerequisites**
- Android Studio (Arctic Fox or later)
- JDK 11 or higher
- Android SDK 33
- Minimum Android device/emulator with API 21+

### **Steps**
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/JetpackComposePokedex.git
   cd JetpackComposePokedex
   ```

2. **Open in Android Studio**:
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Sync Gradle**:
   - Android Studio will automatically start syncing
   - Wait for dependencies to download

4. **Run the app**:
   - Connect an Android device or start an emulator
   - Click the "Run" button (▶️) or press `Shift + F10`

---

## 🌐 API Integration

### **PokéAPI**
- Base URL: `https://pokeapi.co/api/v2/`
- **Endpoints Used**:
  - `GET /pokemon?limit={limit}&offset={offset}` - List of Pokémon
  - `GET /pokemon/{name}` - Detailed Pokémon information

### **Image Source**
- Pokemon sprites: `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/{id}.png`

---

## 🎯 Future Enhancements

- [ ] Add search functionality with filtering
- [ ] Implement favorites/bookmarks
- [ ] Add evolution chain display
- [ ] Show moves and abilities
- [ ] Add Pokemon comparison feature
- [ ] Implement offline mode with Room database
- [ ] Add sound effects and animations
- [ ] Support for multiple languages
- [ ] Dark mode support
- [ ] Unit and UI tests

---

## 👨‍💻 Developer

**Ankit Gujare**
- 📧 Email: ankitgujare008@gmail.com
- 📱 Phone: +91 7498167962
- 📍 Location: Pune / Amravati, India
- 💼 Title: Android | Java | Backend Developer | Software Trainer

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 🙏 Acknowledgments

- [PokéAPI](https://pokeapi.co/) - The RESTful Pokémon API
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern UI toolkit
- [Philipp Lackner](https://www.youtube.com/c/PhilippLackner) - Original tutorial inspiration
- Pokemon Company - For the amazing Pokemon franchise

---

## 🐛 Known Issues

If you encounter any issues, please report them in the [Issues](https://github.com/yourusername/JetpackComposePokedex/issues) section.

---

## 📸 Screenshots

> Add screenshots of your app here
![Image Alt](https://github.com/Ankitgujare/PokedexApp/blob/f620b6d7f4f6f17c210d5cefb13d42d4c1f61f0e/WhatsApp%20Image%202025-11-14%20at%208.10.46%20PM%20(1).jpeg)
![Image Alt](https://github.com/Ankitgujare/PokedexApp/blob/f620b6d7f4f6f17c210d5cefb13d42d4c1f61f0e/WhatsApp%20Image%202025-11-14%20at%208.10.46%20PM.jpeg)
![Image Alt](https://github.com/Ankitgujare/PokedexApp/blob/f620b6d7f4f6f17c210d5cefb13d42d4c1f61f0e/WhatsApp%20Image%202025-11-14%20at%208.10.47%20PM.jpeg)
---

**Made by Ankit with ❤️ using Jetpack Compose**
