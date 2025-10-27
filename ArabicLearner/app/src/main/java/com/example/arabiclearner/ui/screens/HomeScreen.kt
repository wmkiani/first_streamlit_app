package com.example.arabiclearner.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arabiclearner.data.DailyWordsRepository
import com.example.arabiclearner.data.ProgressRepository
import com.example.arabiclearner.data.SampleData
import com.example.arabiclearner.data.Word

@Composable
fun HomeScreen(
    onOpenLearningPath: () -> Unit,
    onContinueLesson: (String) -> Unit
) {
    val context = LocalContext.current
    val progressRepository = remember { ProgressRepository(context.applicationContext as Application) }
    val dailyWordsRepository = remember { DailyWordsRepository(context.applicationContext as Application) }

    val totalLessons = SampleData.learningPath.lessons.size
    val progress by progressRepository.progressFlow(totalLessons).collectAsState(initial = 0f)
    val lastOpened by progressRepository.lastOpenedLessonIdFlow().collectAsState(initial = null)

    var dailyWords by remember { mutableStateOf<List<Word>>(emptyList()) }

    LaunchedEffect(true) {
        dailyWords = dailyWordsRepository.getDailyWordsToday(SampleData.words.values.toList())
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Text(
                text = "Learn Arabic",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Progress ${(progress * 100).toInt()}%", style = MaterialTheme.typography.titleMedium)
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                )
            }
        }
        item {
            Button(onClick = onOpenLearningPath, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Learning Path")
            }
        }
        if (lastOpened != null) {
            item {
                Button(onClick = { onContinueLesson(lastOpened!!) }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Continue Lesson")
                }
            }
        }
        item {
            Text(
                text = "Daily Words",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        items(dailyWords) { word ->
            DailyWordCard(word)
        }
    }
}

@Composable
private fun DailyWordCard(word: Word) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.Start) {
            Text(text = word.arabic, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(text = word.transliteration, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 4.dp))
            Text(text = "EN: ${word.english}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 4.dp))
            Text(text = "UR: ${word.urdu}", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 2.dp))
        }
    }
}