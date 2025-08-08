package com.example.arabiclearner.data

data class Word(
    val id: String,
    val word: String,
    val transliteration: String,
    val meaningEn: String,
    val meaningUr: String,
)

fun Word.meaningFor(locale: String): String {
    return when {
        locale.startsWith("ur") -> meaningUr
        else -> meaningEn
    }
}

data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val words: List<Word>
)

data class LearningPath(
    val id: String,
    val title: String,
    val description: String,
    val lessons: List<Lesson>
)