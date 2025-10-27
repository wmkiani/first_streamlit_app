package com.example.arabiclearner.data

data class Word(
    val id: String,
    val arabic: String,
    val transliteration: String,
    val english: String,
    val urdu: String
)

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val wordIds: List<String>
)

data class LearningPath(
    val lessons: List<Lesson>
)