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
    val level: Int, // 1-3 (Beginner, Intermediate, Advanced)
    val wordIds: List<Int>,
    val isCompleted: Boolean = false,
    val progress: Int = 0, // 0-100
    val order: Int,
    val isUnlocked: Boolean = true
)