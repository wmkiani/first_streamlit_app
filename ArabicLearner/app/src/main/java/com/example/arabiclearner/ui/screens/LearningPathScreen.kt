package com.example.arabiclearner.ui.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arabiclearner.data.ProgressRepository
import com.example.arabiclearner.data.SampleData

@Composable
fun LearningPathScreen(
    onBack: () -> Unit,
    onOpenLesson: (String) -> Unit
) {
    val context = LocalContext.current
    val progressRepository = remember { ProgressRepository(context.applicationContext as Application) }
    val completedSet by progressRepository.completedLessonsFlow.collectAsState(initial = emptySet())

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = "Learning Path") },
            navigationIcon = {
                IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
            }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(SampleData.learningPath.lessons) { lesson ->
                val completed = completedSet.contains(lesson.id)
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onOpenLesson(lesson.id) },
                    colors = CardDefaults.cardColors()
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Text(text = lesson.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                        Text(text = lesson.description, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 4.dp))
                        val status = if (completed) "Completed" else "Not completed"
                        Text(text = status, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 8.dp))
                    }
                }
            }
        }
    }
}