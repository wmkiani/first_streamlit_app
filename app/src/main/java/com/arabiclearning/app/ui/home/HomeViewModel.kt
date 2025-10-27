package com.arabiclearning.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.data.model.UserProgress
import com.arabiclearning.app.data.repository.LessonRepository
import com.arabiclearning.app.data.repository.UserProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userProgressRepository: UserProgressRepository,
    private val lessonRepository: LessonRepository
) : ViewModel() {

    private val _userProgress = MutableStateFlow<UserProgress?>(null)
    val userProgress: StateFlow<UserProgress?> = _userProgress

    private val _nextLesson = MutableStateFlow<Lesson?>(null)
    val nextLesson: StateFlow<Lesson?> = _nextLesson

    init {
        loadUserProgress()
        loadNextLesson()
    }

    fun loadUserProgress() {
        viewModelScope.launch {
            userProgressRepository.getUserProgress().collect { progress ->
                _userProgress.value = progress
            }
        }
    }

    fun loadNextLesson() {
        viewModelScope.launch {
            val lesson = lessonRepository.getNextLesson()
            _nextLesson.value = lesson
        }
    }

    fun updateDailyGoal(goal: Int) {
        viewModelScope.launch {
            userProgressRepository.updateDailyGoal(goal)
        }
    }

    fun resetDailyProgress() {
        viewModelScope.launch {
            userProgressRepository.updateDailyGoalAchieved(false)
        }
    }
}