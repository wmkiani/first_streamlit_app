package com.example.arabiclearner.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDate

private val Context.dailyDataStore by preferencesDataStore(name = "daily_prefs")

class DailyWordsRepository(private val appContext: Context) {

    private object Keys {
        val LAST_DATE: Preferences.Key<String> = stringPreferencesKey("last_date")
        val WORD_IDS: Preferences.Key<Set<String>> = stringSetPreferencesKey("word_ids")
    }

    suspend fun getDailyWordsToday(allWords: List<Word>, count: Int = 5): List<Word> {
        val today = LocalDate.now().toString()
        val prefs = appContext.dailyDataStore.data.first()
        val storedDate = prefs[Keys.LAST_DATE]
        val existing = prefs[Keys.WORD_IDS] ?: emptySet()

        val finalIds: Set<String> = if (storedDate == today && existing.isNotEmpty()) {
            existing
        } else {
            val shuffled = allWords.shuffled().take(count).map { it.id }.toSet()
            appContext.dailyDataStore.edit { p ->
                p[Keys.LAST_DATE] = today
                p[Keys.WORD_IDS] = shuffled
            }
            shuffled
        }

        val map = allWords.associateBy { it.id }
        return finalIds.mapNotNull { map[it] }
    }
}