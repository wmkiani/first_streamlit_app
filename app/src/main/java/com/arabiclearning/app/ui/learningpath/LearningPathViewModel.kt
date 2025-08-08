package com.arabiclearning.app.ui.learningpath

import androidx.lifecycle.ViewModel
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.data.repository.ArabicLearningRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LearningPathViewModel @Inject constructor(
    private val repository: ArabicLearningRepository
) : ViewModel() {

    val beginnerLessons: Flow<List<Lesson>> = repository.getLessonsByLevel(1)
    val intermediateLessons: Flow<List<Lesson>> = repository.getLessonsByLevel(2)
    val advancedLessons: Flow<List<Lesson>> = repository.getLessonsByLevel(3)
}