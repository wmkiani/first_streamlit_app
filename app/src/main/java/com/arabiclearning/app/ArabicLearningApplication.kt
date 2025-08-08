package com.arabiclearning.app

import android.app.Application
import androidx.room.Room
import com.arabiclearning.app.data.database.AppDatabase

class ArabicLearningApplication : Application() {
    
    lateinit var database: AppDatabase
        private set
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Room database
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "arabic_learning_database"
        ).build()
        
        // Initialize default data if needed
        initializeDefaultData()
    }
    
    private fun initializeDefaultData() {
        // This would be called to populate the database with initial words and lessons
        // For now, it's a placeholder for future implementation
    }
}