package com.arabiclearning.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val totalWordsLearned: Int = 0,
    val totalLessonsCompleted: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val totalStudyTime: Long = 0, // in minutes
    val accuracyRate: Float = 0f, // 0-100
    val lastStudyDate: Long = 0,
    val dailyGoal: Int = 10, // words per day
    val dailyGoalAchieved: Boolean = false,
    val totalXP: Int = 0,
    val level: Int = 1
)