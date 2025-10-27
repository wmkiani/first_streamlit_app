package com.arabiclearning.app.data.dao

import androidx.room.*
import com.arabiclearning.app.data.model.Lesson
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    
    @Query("SELECT * FROM lessons ORDER BY id ASC")
    fun getAllLessons(): Flow<List<Lesson>>
    
    @Query("SELECT * FROM lessons WHERE category = :category ORDER BY id ASC")
    fun getLessonsByCategory(category: String): Flow<List<Lesson>>
    
    @Query("SELECT * FROM lessons WHERE isCompleted = 0 ORDER BY id ASC LIMIT 1")
    suspend fun getNextLesson(): Lesson?
    
    @Query("SELECT * FROM lessons WHERE id = :lessonId")
    suspend fun getLessonById(lessonId: Int): Lesson?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<Lesson>)
    
    @Update
    suspend fun updateLesson(lesson: Lesson)
    
    @Delete
    suspend fun deleteLesson(lesson: Lesson)
    
    @Query("SELECT COUNT(*) FROM lessons WHERE isCompleted = 1")
    suspend fun getCompletedLessonsCount(): Int
    
    @Query("SELECT COUNT(*) FROM lessons")
    suspend fun getTotalLessonsCount(): Int
    
    @Query("SELECT AVG(progress) FROM lessons")
    suspend fun getAverageProgress(): Float
}