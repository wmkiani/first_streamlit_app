package com.example.arabiclearner.viewmodel

import androidx.lifecycle.ViewModel
import com.example.arabiclearner.data.InMemoryContentRepository
import com.example.arabiclearner.data.LearningPath
import com.example.arabiclearner.data.Lesson
import com.example.arabiclearner.data.Word
import com.example.arabiclearner.data.meaningFor
import java.time.LocalDate
import java.util.Locale

class AppViewModel : ViewModel() {

    private val allPaths: List<LearningPath> = InMemoryContentRepository.learningPaths
    private val allWords: List<Word> = InMemoryContentRepository.getAllWords()

    private val completed = linkedSetOf<String>()

    val uiState: UiState
        get() {
            val overallPercent = computeOverallProgressPercent()
            val daily = pickDailyWord()
            val locale = Locale.getDefault().language
            return UiState(
                learningPaths = allPaths,
                completedLessons = completed.toSet(),
                overallProgressPercent = overallPercent,
                dailyWord = DailyWord(
                    word = daily.word,
                    transliteration = daily.transliteration,
                    meaning = daily.meaningFor(locale)
                )
            )
        }

    fun setLessonCompleted(lessonId: String, isCompleted: Boolean) {
        if (isCompleted) completed.add(lessonId) else completed.remove(lessonId)
    }

    fun isLessonCompleted(lessonId: String): Boolean = completed.contains(lessonId)

    fun getLesson(lessonId: String): Lesson? = allPaths.flatMap { it.lessons }.find { it.id == lessonId }

    fun findNextIncompleteLessonId(): String? =
        allPaths.flatMap { it.lessons }.firstOrNull { !completed.contains(it.id) }?.id

    private fun computeOverallProgressPercent(): Int {
        val total = allPaths.sumOf { it.lessons.size }
        if (total == 0) return 0
        val done = completed.size.coerceAtMost(total)
        return (done * 100) / total
    }

    private fun pickDailyWord(): Word {
        if (allWords.isEmpty()) throw IllegalStateException("No words available")
        val day = LocalDate.now().dayOfYear
        return allWords[day % allWords.size]
    }
}

data class UiState(
    val learningPaths: List<LearningPath>,
    val completedLessons: Set<String>,
    val overallProgressPercent: Int,
    val dailyWord: DailyWord,
)

data class DailyWord(
    val word: String,
    val transliteration: String,
    val meaning: String,
)