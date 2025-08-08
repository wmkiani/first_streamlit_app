package com.arabiclearning.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey
    val id: Int = 1,
    val totalWordsLearned: Int = 0,
    val totalWordsMastered: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val totalStudyTime: Long = 0, // in minutes
    val accuracyRate: Float = 0f,
    val level: Int = 1,
    val experience: Int = 0,
    val lastStudyDate: Long = 0
)