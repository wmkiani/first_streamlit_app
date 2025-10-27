package com.arabiclearning.app.data.repository

import com.arabiclearning.app.data.dao.LessonDao
import com.arabiclearning.app.data.model.Lesson
import kotlinx.coroutines.flow.Flow

class LessonRepository(
    private val lessonDao: LessonDao
) {
    
    fun getAllLessons(): Flow<List<Lesson>> = lessonDao.getAllLessons()
    
    fun getLessonsByCategory(category: String): Flow<List<Lesson>> = 
        lessonDao.getLessonsByCategory(category)
    
    suspend fun getNextLesson(): Lesson? = lessonDao.getNextLesson()
    
    suspend fun getLessonById(lessonId: Int): Lesson? = lessonDao.getLessonById(lessonId)
    
    suspend fun insertLesson(lesson: Lesson) = lessonDao.insertLesson(lesson)
    
    suspend fun insertLessons(lessons: List<Lesson>) = lessonDao.insertLessons(lessons)
    
    suspend fun updateLesson(lesson: Lesson) = lessonDao.updateLesson(lesson)
    
    suspend fun deleteLesson(lesson: Lesson) = lessonDao.deleteLesson(lesson)
    
    suspend fun getCompletedLessonsCount(): Int = lessonDao.getCompletedLessonsCount()
    
    suspend fun getTotalLessonsCount(): Int = lessonDao.getTotalLessonsCount()
    
    suspend fun getAverageProgress(): Float = lessonDao.getAverageProgress()
    
    suspend fun completeLesson(lessonId: Int) {
        val lesson = getLessonById(lessonId)
        lesson?.let {
            val updatedLesson = it.copy(
                isCompleted = true,
                progress = 100
            )
            updateLesson(updatedLesson)
        }
    }
    
    suspend fun updateLessonProgress(lessonId: Int, progress: Int) {
        val lesson = getLessonById(lessonId)
        lesson?.let {
            val updatedLesson = it.copy(progress = progress)
            updateLesson(updatedLesson)
        }
    }
}