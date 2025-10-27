package com.arabiclearning.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arabiclearning.app.data.dao.LessonDao
import com.arabiclearning.app.data.dao.UserProgressDao
import com.arabiclearning.app.data.dao.WordDao
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.data.model.UserProgress
import com.arabiclearning.app.data.model.Word

@Database(
    entities = [Lesson::class, Word::class, UserProgress::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun lessonDao(): LessonDao
    abstract fun wordDao(): WordDao
    abstract fun userProgressDao(): UserProgressDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "arabic_learning_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}