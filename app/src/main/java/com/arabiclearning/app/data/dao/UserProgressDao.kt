package com.arabiclearning.app.data.dao

import androidx.room.*
import com.arabiclearning.app.data.model.UserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    
    @Query("SELECT * FROM user_progress LIMIT 1")
    fun getUserProgress(): Flow<UserProgress?>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProgress(userProgress: UserProgress)
    
    @Update
    suspend fun updateUserProgress(userProgress: UserProgress)
    
    @Query("UPDATE user_progress SET totalWordsLearned = totalWordsLearned + :wordsLearned")
    suspend fun incrementWordsLearned(wordsLearned: Int)
    
    @Query("UPDATE user_progress SET totalLessonsCompleted = totalLessonsCompleted + 1")
    suspend fun incrementLessonsCompleted()
    
    @Query("UPDATE user_progress SET currentStreak = :streak")
    suspend fun updateCurrentStreak(streak: Int)
    
    @Query("UPDATE user_progress SET longestStreak = :streak WHERE longestStreak < :streak")
    suspend fun updateLongestStreak(streak: Int)
    
    @Query("UPDATE user_progress SET totalStudyTime = totalStudyTime + :studyTime")
    suspend fun addStudyTime(studyTime: Long)
    
    @Query("UPDATE user_progress SET accuracyRate = :accuracy")
    suspend fun updateAccuracyRate(accuracy: Float)
    
    @Query("UPDATE user_progress SET lastStudyDate = :date")
    suspend fun updateLastStudyDate(date: Long)
    
    @Query("UPDATE user_progress SET dailyGoalAchieved = :achieved")
    suspend fun updateDailyGoalAchieved(achieved: Boolean)
    
    @Query("UPDATE user_progress SET totalXP = totalXP + :xp")
    suspend fun addXP(xp: Int)
    
    @Query("UPDATE user_progress SET level = :level")
    suspend fun updateLevel(level: Int)
    
    @Query("UPDATE user_progress SET dailyGoal = :goal")
    suspend fun updateDailyGoal(goal: Int)
}