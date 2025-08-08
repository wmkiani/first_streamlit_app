package com.arabiclearning.app.data.database

import androidx.room.*
import com.arabiclearning.app.data.model.Lesson
import com.arabiclearning.app.data.model.UserProgress
import com.arabiclearning.app.data.model.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Database(
    entities = [Word::class, Lesson::class, UserProgress::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun lessonDao(): LessonDao
    abstract fun userProgressDao(): UserProgressDao
}

class Converters {
    @TypeConverter
    fun fromList(value: List<Int>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<Int> {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }
}