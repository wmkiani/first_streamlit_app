package com.arabiclearning.app.data.dao

import androidx.room.*
import com.arabiclearning.app.data.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    
    @Query("SELECT * FROM words ORDER BY id ASC")
    fun getAllWords(): Flow<List<Word>>
    
    @Query("SELECT * FROM words WHERE category = :category ORDER BY id ASC")
    fun getWordsByCategory(category: String): Flow<List<Word>>
    
    @Query("SELECT * FROM words WHERE isMastered = 0 ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomWords(limit: Int): List<Word>
    
    @Query("SELECT * FROM words WHERE id = :wordId")
    suspend fun getWordById(wordId: Int): Word?
    
    @Query("SELECT * FROM words WHERE isMastered = 0 ORDER BY lastPracticed ASC LIMIT :limit")
    suspend fun getWordsForReview(limit: Int): List<Word>
    
    @Query("SELECT * FROM words WHERE lastPracticed >= :startDate ORDER BY lastPracticed DESC")
    suspend fun getWordsPracticedSince(startDate: Long): List<Word>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: Word)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWords(words: List<Word>)
    
    @Update
    suspend fun updateWord(word: Word)
    
    @Delete
    suspend fun deleteWord(word: Word)
    
    @Query("SELECT COUNT(*) FROM words WHERE isMastered = 1")
    suspend fun getMasteredWordsCount(): Int
    
    @Query("SELECT COUNT(*) FROM words")
    suspend fun getTotalWordsCount(): Int
    
    @Query("SELECT AVG(timesCorrect * 100.0 / timesPracticed) FROM words WHERE timesPracticed > 0")
    suspend fun getAverageAccuracy(): Float
    
    @Query("SELECT * FROM words ORDER BY lastPracticed DESC LIMIT 5")
    suspend fun getRecentlyPracticedWords(): List<Word>
}