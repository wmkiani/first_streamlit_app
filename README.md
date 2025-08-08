# Arabic Learning App

A comprehensive Android application designed to help English and Urdu speakers learn Arabic. The app features a structured learning path, progress tracking, and daily word practice.

## Features

### 🎯 Learning Path
- **Structured Lessons**: Organized learning modules covering various topics
- **Progressive Difficulty**: Lessons arranged from basic to advanced levels
- **Interactive Content**: Engaging lessons with audio, images, and exercises
- **Category-based Learning**: Topics include greetings, numbers, colors, family, food, animals, weather, travel, and business

### 📊 Progress Tracking
- **Overall Progress**: Visual progress indicators showing completion percentage
- **Daily Goals**: Set and track daily learning targets
- **Streak Tracking**: Maintain learning streaks for motivation
- **Statistics**: Detailed analytics including words mastered, lessons completed, and accuracy rates
- **Weekly Reports**: Track weekly learning activity and achievements

### 📚 Daily Words
- **Word of the Day**: Featured Arabic word with translations
- **Practice Modes**: Multiple practice options including flashcards, quiz mode, and practice mode
- **Audio Support**: Pronunciation guides for each word
- **Multi-language Support**: Translations in English and Urdu
- **Spaced Repetition**: Intelligent word review system

### 🎨 User Interface
- **Modern Design**: Material Design 3 with beautiful Arabic-themed colors
- **RTL Support**: Right-to-left text support for Arabic content
- **Responsive Layout**: Optimized for various screen sizes
- **Accessibility**: Support for accessibility features

## Technical Architecture

### 🏗️ Architecture Pattern
- **MVVM (Model-View-ViewModel)**: Clean architecture with separation of concerns
- **Repository Pattern**: Data access abstraction layer
- **Room Database**: Local data persistence with SQLite
- **Navigation Component**: Single activity with fragment-based navigation

### 📱 Technologies Used
- **Kotlin**: Primary programming language
- **Android Jetpack**: Modern Android development libraries
- **Room Database**: Local data storage
- **Navigation Component**: Fragment navigation
- **ViewBinding**: Type-safe view binding
- **Coroutines**: Asynchronous programming
- **Flow**: Reactive streams for data
- **Material Design 3**: Modern UI components

### 🗄️ Database Schema

#### Lessons Table
- `id`: Primary key
- `title`: Lesson title
- `description`: Lesson description
- `category`: Lesson category
- `difficulty`: Difficulty level (1-5)
- `isCompleted`: Completion status
- `progress`: Progress percentage (0-100)
- `totalWords`: Total words in lesson
- `learnedWords`: Words learned in lesson
- `estimatedTime`: Estimated completion time
- `imageResource`: Lesson image resource
- `isLocked`: Whether lesson is locked

#### Words Table
- `id`: Primary key
- `arabic`: Arabic text
- `english`: English translation
- `urdu`: Urdu translation
- `transliteration`: Phonetic transliteration
- `category`: Word category
- `difficulty`: Difficulty level (1-5)
- `isMastered`: Mastery status
- `timesPracticed`: Number of practice sessions
- `timesCorrect`: Number of correct answers
- `lastPracticed`: Last practice timestamp
- `audioUrl`: Audio pronunciation URL
- `imageUrl`: Associated image URL

#### User Progress Table
- `id`: Primary key
- `totalWordsLearned`: Total words mastered
- `totalLessonsCompleted`: Total lessons completed
- `currentStreak`: Current learning streak
- `longestStreak`: Longest achieved streak
- `totalStudyTime`: Total study time in minutes
- `accuracyRate`: Overall accuracy percentage
- `lastStudyDate`: Last study session date
- `dailyGoal`: Daily word learning goal
- `dailyGoalAchieved`: Daily goal achievement status
- `totalXP`: Total experience points
- `level`: Current user level

## Project Structure

```
app/
├── src/main/
│   ├── java/com/arabiclearning/app/
│   │   ├── data/
│   │   │   ├── dao/           # Data Access Objects
│   │   │   ├── database/      # Room database setup
│   │   │   ├── model/         # Data models
│   │   │   └── repository/    # Repository classes
│   │   ├── ui/
│   │   │   ├── home/          # Home screen
│   │   │   ├── lessons/       # Lessons screen
│   │   │   ├── progress/      # Progress screen
│   │   │   ├── dailywords/    # Daily words screen
│   │   │   └── lesson/        # Individual lesson screen
│   │   └── MainActivity.kt    # Main activity
│   └── res/
│       ├── layout/            # UI layouts
│       ├── values/            # Resources (strings, colors, themes)
│       ├── drawable/          # Icons and images
│       └── navigation/        # Navigation graphs
```

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24 (API level 24) or higher
- Kotlin 1.9.10 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/arabic-learning-app.git
   cd arabic-learning-app
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Open the project folder
   - Wait for Gradle sync to complete

3. **Build and Run**
   - Connect an Android device or start an emulator
   - Click the "Run" button or press Shift+F10
   - Select your target device and install the app

### Configuration

The app comes with sample data pre-loaded. To customize:

1. **Add Custom Lessons**: Modify the lesson data in the repository
2. **Add Custom Words**: Add new words to the word repository
3. **Customize UI**: Modify colors, themes, and layouts in the `res/values` folder
4. **Add Audio**: Include audio files for pronunciation in the `res/raw` folder

## Features in Detail

### Learning Path
The learning path is designed to take users from complete beginners to intermediate Arabic speakers:

1. **Arabic Basics**: Alphabet, pronunciation, and basic sounds
2. **Greetings**: Common greetings and introductions
3. **Numbers**: Counting from 1-100 and basic arithmetic
4. **Colors**: Color vocabulary and descriptions
5. **Family**: Family member terms and relationships
6. **Food & Drinks**: Food vocabulary and ordering
7. **Animals**: Animal names and descriptions
8. **Weather**: Weather terms and conditions
9. **Travel**: Travel-related vocabulary
10. **Business**: Business and professional terms

### Progress Tracking
The app tracks multiple metrics to provide comprehensive progress insights:

- **Daily Progress**: Words learned per day vs. daily goal
- **Weekly Activity**: Study time, lessons completed, and words mastered
- **Overall Statistics**: Total progress across all categories
- **Accuracy Tracking**: Performance on practice exercises
- **Streak Maintenance**: Daily learning streak tracking

### Daily Words Feature
Each day, users receive:

- **Word of the Day**: A featured Arabic word with full translations
- **Practice Set**: 10 new words to learn and practice
- **Review Words**: Previously learned words for reinforcement
- **Audio Pronunciation**: Native pronunciation for each word
- **Multiple Practice Modes**: Flashcards, quizzes, and interactive exercises

## Contributing

We welcome contributions to improve the Arabic Learning App! Here's how you can help:

### Development Setup
1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make your changes and test thoroughly
4. Commit your changes: `git commit -m 'Add amazing feature'`
5. Push to the branch: `git push origin feature/amazing-feature`
6. Open a Pull Request

### Areas for Contribution
- **Content**: Add more Arabic words, lessons, or categories
- **Features**: Implement new learning features or practice modes
- **UI/UX**: Improve the user interface and experience
- **Localization**: Add support for additional languages
- **Testing**: Add unit tests and UI tests
- **Documentation**: Improve code documentation and user guides

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- **Arabic Language Experts**: For content validation and pronunciation guidance
- **Material Design Team**: For the beautiful design system
- **Android Developer Community**: For open-source libraries and tools
- **Beta Testers**: For valuable feedback and bug reports

## Support

If you encounter any issues or have questions:

1. **Check the Issues**: Look for existing issues in the GitHub repository
2. **Create an Issue**: Report bugs or request features
3. **Contact Support**: Email support@arabiclearningapp.com

## Roadmap

### Version 1.1 (Planned)
- [ ] Offline mode support
- [ ] Social features (leaderboards, friends)
- [ ] Advanced analytics and insights
- [ ] Custom lesson creation

### Version 1.2 (Planned)
- [ ] Voice recognition for pronunciation practice
- [ ] AR features for immersive learning
- [ ] Integration with external Arabic learning resources
- [ ] Advanced spaced repetition algorithm

### Version 2.0 (Future)
- [ ] AI-powered personalized learning paths
- [ ] Real-time conversation practice
- [ ] Cultural context and history lessons
- [ ] Advanced grammar and writing exercises

---

**Happy Learning! 🌟**

*Learn Arabic, Connect with Culture*