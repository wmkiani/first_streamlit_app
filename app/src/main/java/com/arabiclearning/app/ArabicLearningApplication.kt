package com.arabiclearning.app

import android.app.Application
import com.arabiclearning.app.data.database.AppDatabase
import com.arabiclearning.app.data.repository.LessonRepository
import com.arabiclearning.app.data.repository.UserProgressRepository
import com.arabiclearning.app.data.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArabicLearningApplication : Application() {
    
    private val applicationScope = CoroutineScope(Dispatchers.IO)
    
    // Database instance
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    
    // Repository instances
    val lessonRepository: LessonRepository by lazy { 
        LessonRepository(database.lessonDao()) 
    }
    
    val wordRepository: WordRepository by lazy { 
        WordRepository(database.wordDao()) 
    }
    
    val userProgressRepository: UserProgressRepository by lazy { 
        UserProgressRepository(database.userProgressDao()) 
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize database with sample data
        applicationScope.launch {
            initializeDatabase()
        }
    }
    
    private suspend fun initializeDatabase() {
        // Initialize user progress if not exists
        userProgressRepository.initializeUserProgress()
        
        // Add sample lessons if database is empty
        val existingLessons = lessonRepository.getAllLessons()
        if (existingLessons.value?.isEmpty() != false) {
            addSampleLessons()
        }
        
        // Add sample words if database is empty
        val existingWords = wordRepository.getAllWords()
        if (existingWords.value?.isEmpty() != false) {
            addSampleWords()
        }
    }
    
    private suspend fun addSampleLessons() {
        val lessons = listOf(
            com.arabiclearning.app.data.model.Lesson(
                title = "Arabic Basics",
                description = "Learn the basics of Arabic alphabet and pronunciation",
                category = "Basics",
                difficulty = 1,
                totalWords = 15,
                estimatedTime = 10,
                isLocked = false
            ),
            com.arabiclearning.app.data.model.Lesson(
                title = "Greetings",
                description = "Learn common greetings and introductions in Arabic",
                category = "Conversation",
                difficulty = 1,
                totalWords = 20,
                estimatedTime = 15,
                isLocked = false
            ),
            com.arabiclearning.app.data.model.Lesson(
                title = "Numbers",
                description = "Learn to count from 1 to 100 in Arabic",
                category = "Numbers",
                difficulty = 2,
                totalWords = 25,
                estimatedTime = 20,
                isLocked = true
            ),
            com.arabiclearning.app.data.model.Lesson(
                title = "Colors",
                description = "Learn colors and how to describe objects",
                category = "Vocabulary",
                difficulty = 2,
                totalWords = 18,
                estimatedTime = 12,
                isLocked = true
            ),
            com.arabiclearning.app.data.model.Lesson(
                title = "Family",
                description = "Learn family member terms and relationships",
                category = "Family",
                difficulty = 2,
                totalWords = 22,
                estimatedTime = 15,
                isLocked = true
            )
        )
        
        lessonRepository.insertLessons(lessons)
    }
    
    private suspend fun addSampleWords() {
        val words = listOf(
            com.arabiclearning.app.data.model.Word(
                arabic = "مرحبا",
                english = "Hello",
                urdu = "ہیلو",
                transliteration = "marhaba",
                category = "Greetings",
                difficulty = 1
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "شكرا",
                english = "Thank you",
                urdu = "شکریہ",
                transliteration = "shukran",
                category = "Greetings",
                difficulty = 1
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "ماء",
                english = "Water",
                urdu = "پانی",
                transliteration = "maa",
                category = "Basics",
                difficulty = 1
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "كتاب",
                english = "Book",
                urdu = "کتاب",
                transliteration = "kitaab",
                category = "Basics",
                difficulty = 1
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "بيت",
                english = "House",
                urdu = "گھر",
                transliteration = "bayt",
                category = "Basics",
                difficulty = 1
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "واحد",
                english = "One",
                urdu = "ایک",
                transliteration = "waahid",
                category = "Numbers",
                difficulty = 1
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "اثنان",
                english = "Two",
                urdu = "دو",
                transliteration = "ithnaan",
                category = "Numbers",
                difficulty = 1
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "أحمر",
                english = "Red",
                urdu = "سرخ",
                transliteration = "ahmar",
                category = "Colors",
                difficulty = 2
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "أزرق",
                english = "Blue",
                urdu = "نیلا",
                transliteration = "azraq",
                category = "Colors",
                difficulty = 2
            ),
            com.arabiclearning.app.data.model.Word(
                arabic = "أم",
                english = "Mother",
                urdu = "ماں",
                transliteration = "umm",
                category = "Family",
                difficulty = 2
            )
        )
        
        wordRepository.insertWords(words)
    }
}