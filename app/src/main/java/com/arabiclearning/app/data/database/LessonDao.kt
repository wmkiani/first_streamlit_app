package com.arabiclearning.app.data.database

import androidx.room.*
import com.arabiclearning.app.data.model.Lesson
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {
    @Query("SELECT * FROM lessons ORDER BY level, order")
    fun getAllLessons(): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE level = :level ORDER BY order")
    fun getLessonsByLevel(level: Int): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE category = :category ORDER BY order")
    fun getLessonsByCategory(category: String): Flow<List<Lesson>>

    @Query("SELECT * FROM lessons WHERE isCompleted = 0 AND isUnlocked = 1 ORDER BY level, order LIMIT 1")
    fun getNextLesson(): Flow<Lesson?>

    @Query("SELECT COUNT(*) FROM lessons WHERE isCompleted = 1")
    fun getCompletedLessonsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM lessons")
    fun getTotalLessonsCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: Lesson)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<Lesson>)

    @Update
    suspend fun updateLesson(lesson: Lesson)

    @Delete
    suspend fun deleteLesson(lesson: Lesson)

    @Query("DELETE FROM lessons")
    suspend fun deleteAllLessons()
}