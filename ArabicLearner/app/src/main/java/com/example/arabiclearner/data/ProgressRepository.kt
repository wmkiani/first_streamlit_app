package com.example.arabiclearner.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.progressDataStore by preferencesDataStore(name = "progress_prefs")

class ProgressRepository(private val appContext: Context) {

    private object Keys {
        val COMPLETED_LESSONS: Preferences.Key<Set<String>> = stringSetPreferencesKey("completed_lessons")
        val LAST_OPENED_LESSON_ID: Preferences.Key<String> = stringPreferencesKey("last_opened_lesson_id")
    }

    val completedLessonsFlow: Flow<Set<String>> = appContext.progressDataStore.data.map { prefs ->
        prefs[Keys.COMPLETED_LESSONS] ?: emptySet()
    }

    fun isLessonCompletedFlow(lessonId: String): Flow<Boolean> = completedLessonsFlow.map { it.contains(lessonId) }

    fun lastOpenedLessonIdFlow(): Flow<String?> = appContext.progressDataStore.data.map { prefs ->
        prefs[Keys.LAST_OPENED_LESSON_ID]
    }

    fun progressFlow(totalLessons: Int): Flow<Float> = completedLessonsFlow.map { completed ->
        if (totalLessons <= 0) 0f else completed.size.toFloat() / totalLessons.toFloat()
    }

    suspend fun markLessonCompleted(lessonId: String) {
        appContext.progressDataStore.edit { prefs ->
            val set = (prefs[Keys.COMPLETED_LESSONS] ?: emptySet()).toMutableSet()
            set.add(lessonId)
            prefs[Keys.COMPLETED_LESSONS] = set
        }
    }

    suspend fun setLastOpenedLesson(lessonId: String) {
        appContext.progressDataStore.edit { prefs ->
            prefs[Keys.LAST_OPENED_LESSON_ID] = lessonId
        }
    }
}