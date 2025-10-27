package com.arabiclearning.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val arabic: String,
    val english: String,
    val urdu: String,
    val transliteration: String,
    val category: String,
    val difficulty: Int, // 1-5 scale
    val isLearned: Boolean = false,
    val isMastered: Boolean = false,
    val timesReviewed: Int = 0,
    val lastReviewed: Long = 0
)