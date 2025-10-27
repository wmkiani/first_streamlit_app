package com.arabiclearning.app.data.repository

import com.arabiclearning.app.data.dao.UserProgressDao
import com.arabiclearning.app.data.model.UserProgress
import kotlinx.coroutines.flow.Flow

class UserProgressRepository(
    private val userProgressDao: UserProgressDao
) {
    
    fun getUserProgress(): Flow<UserProgress?> = userProgressDao.getUserProgress()
    
    suspend fun insertUserProgress(userProgress: UserProgress) = 
        userProgressDao.insertUserProgress(userProgress)
    
    suspend fun updateUserProgress(userProgress: UserProgress) = 
        userProgressDao.updateUserProgress(userProgress)
    
    suspend fun incrementWordsLearned(wordsLearned: Int) = 
        userProgressDao.incrementWordsLearned(wordsLearned)
    
    suspend fun incrementLessonsCompleted() = userProgressDao.incrementLessonsCompleted()
    
    suspend fun updateCurrentStreak(streak: Int) = userProgressDao.updateCurrentStreak(streak)
    
    suspend fun updateLongestStreak(streak: Int) = userProgressDao.updateLongestStreak(streak)
    
    suspend fun addStudyTime(studyTime: Long) = userProgressDao.addStudyTime(studyTime)
    
    suspend fun updateAccuracyRate(accuracy: Float) = userProgressDao.updateAccuracyRate(accuracy)
    
    suspend fun updateLastStudyDate(date: Long) = userProgressDao.updateLastStudyDate(date)
    
    suspend fun updateDailyGoalAchieved(achieved: Boolean) = 
        userProgressDao.updateDailyGoalAchieved(achieved)
    
    suspend fun addXP(xp: Int) = userProgressDao.addXP(xp)
    
    suspend fun updateLevel(level: Int) = userProgressDao.updateLevel(level)
    
    suspend fun updateDailyGoal(goal: Int) = userProgressDao.updateDailyGoal(goal)
    
    suspend fun initializeUserProgress() {
        val existingProgress = getUserProgress()
        if (existingProgress == null) {
            val initialProgress = UserProgress(
                totalWordsLearned = 0,
                totalLessonsCompleted = 0,
                currentStreak = 0,
                longestStreak = 0,
                totalStudyTime = 0,
                accuracyRate = 0f,
                lastStudyDate = System.currentTimeMillis(),
                dailyGoal = 10,
                dailyGoalAchieved = false,
                totalXP = 0,
                level = 1
            )
            insertUserProgress(initialProgress)
        }
    }
    
    suspend fun updateStreak() {
        val currentTime = System.currentTimeMillis()
        val oneDayInMillis = 24 * 60 * 60 * 1000L
        
        getUserProgress().collect { progress ->
            progress?.let {
                val lastStudy = it.lastStudyDate
                val daysSinceLastStudy = (currentTime - lastStudy) / oneDayInMillis
                
                when {
                    daysSinceLastStudy == 0L -> {
                        // Same day, increment streak if not already done today
                        if (!it.dailyGoalAchieved) {
                            updateCurrentStreak(it.currentStreak + 1)
                            updateDailyGoalAchieved(true)
                        }
                    }
                    daysSinceLastStudy == 1L -> {
                        // Next day, continue streak
                        updateCurrentStreak(it.currentStreak + 1)
                        updateDailyGoalAchieved(true)
                    }
                    daysSinceLastStudy > 1L -> {
                        // Streak broken
                        updateCurrentStreak(1)
                        updateDailyGoalAchieved(true)
                    }
                }
                
                updateLastStudyDate(currentTime)
            }
        }
    }
    
    suspend fun calculateLevel(xp: Int): Int {
        return when {
            xp < 100 -> 1
            xp < 300 -> 2
            xp < 600 -> 3
            xp < 1000 -> 4
            xp < 1500 -> 5
            xp < 2100 -> 6
            xp < 2800 -> 7
            xp < 3600 -> 8
            xp < 4500 -> 9
            else -> 10
        }
    }
}