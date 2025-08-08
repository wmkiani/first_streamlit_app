package com.arabiclearning.app.data.database

import androidx.room.*
import com.arabiclearning.app.data.model.UserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getUserProgress(): Flow<UserProgress?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProgress(userProgress: UserProgress)

    @Update
    suspend fun updateUserProgress(userProgress: UserProgress)

    @Query("UPDATE user_progress SET totalWordsLearned = totalWordsLearned + 1 WHERE id = 1")
    suspend fun incrementWordsLearned()

    @Query("UPDATE user_progress SET totalWordsMastered = totalWordsMastered + 1 WHERE id = 1")
    suspend fun incrementWordsMastered()

    @Query("UPDATE user_progress SET currentStreak = currentStreak + 1, longestStreak = CASE WHEN currentStreak + 1 > longestStreak THEN currentStreak + 1 ELSE longestStreak END WHERE id = 1")
    suspend fun incrementStreak()

    @Query("UPDATE user_progress SET currentStreak = 0 WHERE id = 1")
    suspend fun resetStreak()

    @Query("UPDATE user_progress SET totalStudyTime = totalStudyTime + :minutes WHERE id = 1")
    suspend fun addStudyTime(minutes: Long)

    @Query("UPDATE user_progress SET accuracyRate = :accuracy WHERE id = 1")
    suspend fun updateAccuracyRate(accuracy: Float)

    @Query("UPDATE user_progress SET experience = experience + :exp WHERE id = 1")
    suspend fun addExperience(exp: Int)

    @Query("UPDATE user_progress SET lastStudyDate = :timestamp WHERE id = 1")
    suspend fun updateLastStudyDate(timestamp: Long)
}