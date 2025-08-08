package com.arabiclearning.app.data.repository

import com.arabiclearning.app.data.database.*
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.data.model.UserProgress
import com.arabiclearning.app.data.model.Word
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArabicLearningRepository @Inject constructor(
    private val wordDao: WordDao,
    private val lessonDao: LessonDao,
    private val userProgressDao: UserProgressDao
) {
    // Word operations
    fun getAllWords(): Flow<List<Word>> = wordDao.getAllWords()
    
    fun getWordsByCategory(category: String): Flow<List<Word>> = 
        wordDao.getWordsByCategory(category)
    
    fun getNewWords(limit: Int): Flow<List<Word>> = wordDao.getNewWords(limit)
    
    fun getWordsToReview(): Flow<List<Word>> = wordDao.getWordsToReview()
    
    fun getMasteredWords(): Flow<List<Word>> = wordDao.getMasteredWords()
    
    fun getLearnedWordsCount(): Flow<Int> = wordDao.getLearnedWordsCount()
    
    fun getMasteredWordsCount(): Flow<Int> = wordDao.getMasteredWordsCount()
    
    suspend fun insertWord(word: Word) = wordDao.insertWord(word)
    
    suspend fun insertWords(words: List<Word>) = wordDao.insertWords(words)
    
    suspend fun updateWord(word: Word) = wordDao.updateWord(word)
    
    suspend fun markWordAsLearned(wordId: Int) {
        // Implementation would update the word's isLearned status
    }
    
    suspend fun markWordAsMastered(wordId: Int) {
        // Implementation would update the word's isMastered status
    }
    
    // Lesson operations
    fun getAllLessons(): Flow<List<Lesson>> = lessonDao.getAllLessons()
    
    fun getLessonsByLevel(level: Int): Flow<List<Lesson>> = lessonDao.getLessonsByLevel(level)
    
    fun getLessonsByCategory(category: String): Flow<List<Lesson>> = 
        lessonDao.getLessonsByCategory(category)
    
    fun getNextLesson(): Flow<Lesson?> = lessonDao.getNextLesson()
    
    fun getCompletedLessonsCount(): Flow<Int> = lessonDao.getCompletedLessonsCount()
    
    fun getTotalLessonsCount(): Flow<Int> = lessonDao.getTotalLessonsCount()
    
    suspend fun insertLesson(lesson: Lesson) = lessonDao.insertLesson(lesson)
    
    suspend fun insertLessons(lessons: List<Lesson>) = lessonDao.insertLessons(lessons)
    
    suspend fun updateLesson(lesson: Lesson) = lessonDao.updateLesson(lesson)
    
    suspend fun markLessonAsCompleted(lessonId: Int) {
        // Implementation would update the lesson's completion status
    }
    
    // User progress operations
    fun getUserProgress(): Flow<UserProgress?> = userProgressDao.getUserProgress()
    
    suspend fun insertUserProgress(userProgress: UserProgress) = 
        userProgressDao.insertUserProgress(userProgress)
    
    suspend fun updateUserProgress(userProgress: UserProgress) = 
        userProgressDao.updateUserProgress(userProgress)
    
    suspend fun incrementWordsLearned() = userProgressDao.incrementWordsLearned()
    
    suspend fun incrementWordsMastered() = userProgressDao.incrementWordsMastered()
    
    suspend fun incrementStreak() = userProgressDao.incrementStreak()
    
    suspend fun resetStreak() = userProgressDao.resetStreak()
    
    suspend fun addStudyTime(minutes: Long) = userProgressDao.addStudyTime(minutes)
    
    suspend fun updateAccuracyRate(accuracy: Float) = userProgressDao.updateAccuracyRate(accuracy)
    
    suspend fun addExperience(exp: Int) = userProgressDao.addExperience(exp)
    
    suspend fun updateLastStudyDate(timestamp: Long) = userProgressDao.updateLastStudyDate(timestamp)
}