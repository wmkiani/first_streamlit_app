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
    val isMastered: Boolean = false,
    val timesPracticed: Int = 0,
    val timesCorrect: Int = 0,
    val lastPracticed: Long = 0,
    val audioUrl: String = "",
    val imageUrl: String = ""
)