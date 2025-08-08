package com.arabiclearning.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.data.model.UserProgress
import com.arabiclearning.app.data.repository.ArabicLearningRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    val userProgress: Flow<UserProgress?> = repository.getUserProgress()
    val nextLesson: Flow<Lesson?> = repository.getNextLesson()

    init {
        // Initialize user progress if it doesn't exist
        viewModelScope.launch {
            userProgress.collect { progress ->
                if (progress == null) {
                    repository.insertUserProgress(UserProgress())
                }
            }
        }
    }
}