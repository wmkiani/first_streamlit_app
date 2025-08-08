# Arabic Learning App

A comprehensive Android application designed to help English and Urdu speakers learn Arabic. The app features a structured learning path, progress tracking, and daily word practice.

## Features

### 🏠 Home Screen
- Welcome message in Arabic, English, and Urdu
- Daily streak tracking
- Total words learned counter
- Continue learning section with progress
- Quick action buttons for practice and daily words

### 📚 Learning Path
- Structured lessons organized by difficulty levels:
  - **Beginner**: Basic greetings, numbers, colors
  - **Intermediate**: Family, food, animals
  - **Advanced**: Weather, travel, complex phrases
- Progress tracking for each lesson
- Visual indicators for completed, in-progress, and locked lessons

### 📊 Progress Tracking
- Overall progress percentage with circular progress indicator
- Weekly and monthly progress charts
- Statistics including:
  - Words learned and mastered
  - Accuracy rate
  - Study time tracking
  - Current and longest streaks

### 📝 Daily Words
- **Word of the Day**: Featured Arabic word with translations
- **New Words**: Fresh vocabulary to learn
- **Review Words**: Previously learned words for reinforcement
- **Recent Words**: Quick access to recently studied vocabulary

### 🎯 Key Features
- **Multilingual Support**: English, Urdu, and Arabic interfaces
- **Progress Persistence**: Local database storage using Room
- **Modern UI**: Material Design 3 components
- **Responsive Design**: Works on various screen sizes
- **RTL Support**: Proper Arabic text direction support

## Technical Architecture

### Architecture Pattern
- **MVVM (Model-View-ViewModel)** with Repository pattern
- **Single Activity Architecture** with Navigation Component
- **Reactive Programming** using Kotlin Flow

### Key Components

#### Data Layer
- **Room Database**: Local data persistence
- **Repository Pattern**: Centralized data management
- **Data Models**: Word, Lesson, UserProgress entities

#### UI Layer
- **Fragments**: Modular UI components
- **ViewModels**: Business logic and state management
- **ViewBinding**: Type-safe view access
- **Navigation Component**: Fragment navigation

#### Dependencies
- **Room**: Database ORM
- **Navigation Component**: Fragment navigation
- **Material Design**: UI components
- **Lifecycle Components**: ViewModel and LiveData
- **Coroutines**: Asynchronous programming
- **Gson**: JSON serialization

## Project Structure

```
app/
├── src/main/
│   ├── java/com/arabiclearning/app/
│   │   ├── data/
│   │   │   ├── database/          # Room database and DAOs
│   │   │   ├── model/             # Data entities
│   │   │   └── repository/        # Repository classes
│   │   ├── ui/
│   │   │   ├── home/              # Home screen
│   │   │   ├── learningpath/      # Learning path screen
│   │   │   ├── progress/          # Progress tracking screen
│   │   │   ├── dailywords/        # Daily words screen
│   │   │   └── profile/           # User profile screen
│   │   └── MainActivity.kt        # Main activity
│   └── res/
│       ├── layout/                # UI layouts
│       ├── values/                # Strings, colors, themes
│       ├── drawable/              # Icons and graphics
│       ├── navigation/            # Navigation graphs
│       └── menu/                  # Menu resources
```

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+ (API level 24)
- Kotlin 1.9.10+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/arabic-learning-app.git
   cd arabic-learning-app
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the cloned directory and select it

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click the "Run" button (green play icon) in Android Studio
   - The app will install and launch on your device

### Building the Project

```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease
```

## Database Schema

### Words Table
- `id`: Primary key
- `arabic`: Arabic word
- `english`: English translation
- `urdu`: Urdu translation
- `transliteration`: Romanized pronunciation
- `category`: Word category (greetings, numbers, etc.)
- `difficulty`: Difficulty level (1-5)
- `isLearned`: Learning status
- `isMastered`: Mastery status
- `timesReviewed`: Review count
- `lastReviewed`: Last review timestamp

### Lessons Table
- `id`: Primary key
- `title`: Lesson title
- `description`: Lesson description
- `category`: Lesson category
- `level`: Difficulty level (1-3)
- `wordIds`: List of word IDs in lesson
- `isCompleted`: Completion status
- `progress`: Progress percentage (0-100)
- `order`: Lesson order
- `isUnlocked`: Availability status

### UserProgress Table
- `id`: Primary key (always 1 for single user)
- `totalWordsLearned`: Total learned words
- `totalWordsMastered`: Total mastered words
- `currentStreak`: Current daily streak
- `longestStreak`: Longest achieved streak
- `totalStudyTime`: Total study time in minutes
- `accuracyRate`: Overall accuracy percentage
- `level`: Current user level
- `experience`: Experience points
- `lastStudyDate`: Last study session date

## Customization

### Adding New Words
1. Create a new `Word` object
2. Insert into database using `WordDao`
3. Words will automatically appear in appropriate lessons

### Adding New Lessons
1. Create a new `Lesson` object
2. Specify word IDs and difficulty level
3. Insert into database using `LessonDao`

### Modifying UI
- Colors: Edit `colors.xml`
- Strings: Edit `strings.xml`
- Themes: Edit `themes.xml`
- Layouts: Modify XML files in `layout/` directory

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Material Design for UI components
- Room Database for local storage
- Navigation Component for fragment management
- Arabic language resources and translations

## Support

For support and questions:
- Create an issue in the GitHub repository
- Contact the development team
- Check the documentation in the `/docs` folder

---

**Note**: This is a learning application designed to help users learn Arabic. The app includes basic vocabulary and phrases suitable for beginners. For advanced learning, users are encouraged to supplement with additional resources and practice with native speakers.