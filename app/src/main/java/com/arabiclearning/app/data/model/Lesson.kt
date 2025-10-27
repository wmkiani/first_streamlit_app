package com.arabiclearning.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val category: String,
    val difficulty: Int, // 1-5 scale
    val isCompleted: Boolean = false,
    val progress: Int = 0, // 0-100
    val totalWords: Int = 0,
    val learnedWords: Int = 0,
    val estimatedTime: Int = 0, // in minutes
    val imageResource: String = "",
    val isLocked: Boolean = false
)