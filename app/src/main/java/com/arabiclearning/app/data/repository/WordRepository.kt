package com.arabiclearning.app.data.repository

import com.arabiclearning.app.data.dao.WordDao
import com.arabiclearning.app.data.model.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(
    private val wordDao: WordDao
) {
    
    fun getAllWords(): Flow<List<Word>> = wordDao.getAllWords()
    
    fun getWordsByCategory(category: String): Flow<List<Word>> = 
        wordDao.getWordsByCategory(category)
    
    suspend fun getRandomWords(limit: Int): List<Word> = wordDao.getRandomWords(limit)
    
    suspend fun getWordById(wordId: Int): Word? = wordDao.getWordById(wordId)
    
    suspend fun getWordsForReview(limit: Int): List<Word> = wordDao.getWordsForReview(limit)
    
    suspend fun getWordsPracticedSince(startDate: Long): List<Word> = 
        wordDao.getWordsPracticedSince(startDate)
    
    suspend fun insertWord(word: Word) = wordDao.insertWord(word)
    
    suspend fun insertWords(words: List<Word>) = wordDao.insertWords(words)
    
    suspend fun updateWord(word: Word) = wordDao.updateWord(word)
    
    suspend fun deleteWord(word: Word) = wordDao.deleteWord(word)
    
    suspend fun getMasteredWordsCount(): Int = wordDao.getMasteredWordsCount()
    
    suspend fun getTotalWordsCount(): Int = wordDao.getTotalWordsCount()
    
    suspend fun getAverageAccuracy(): Float = wordDao.getAverageAccuracy()
    
    suspend fun getRecentlyPracticedWords(): List<Word> = wordDao.getRecentlyPracticedWords()
    
    suspend fun practiceWord(wordId: Int, isCorrect: Boolean) {
        val word = getWordById(wordId)
        word?.let {
            val updatedWord = it.copy(
                timesPracticed = it.timesPracticed + 1,
                timesCorrect = if (isCorrect) it.timesCorrect + 1 else it.timesCorrect,
                lastPracticed = System.currentTimeMillis(),
                isMastered = if (isCorrect && it.timesCorrect + 1 >= 5) true else it.isMastered
            )
            updateWord(updatedWord)
        }
    }
    
    suspend fun getDailyWords(limit: Int = 10): List<Word> {
        return getRandomWords(limit)
    }
    
    suspend fun getWordsByDifficulty(difficulty: Int): List<Word> {
        return getAllWords().collect { words ->
            words.filter { it.difficulty == difficulty }
        }
    }
}