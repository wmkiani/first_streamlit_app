package com.arabiclearning.app.data.database

import androidx.room.*
import com.arabiclearning.app.data.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE category = :category")
    fun getWordsByCategory(category: String): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE isLearned = 0 LIMIT :limit")
    fun getNewWords(limit: Int): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE isLearned = 1 AND isMastered = 0")
    fun getWordsToReview(): Flow<List<Word>>

    @Query("SELECT * FROM words WHERE isMastered = 1")
    fun getMasteredWords(): Flow<List<Word>>

    @Query("SELECT COUNT(*) FROM words WHERE isLearned = 1")
    fun getLearnedWordsCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM words WHERE isMastered = 1")
    fun getMasteredWordsCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<Word>)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("DELETE FROM words")
    suspend fun deleteAllWords()
}