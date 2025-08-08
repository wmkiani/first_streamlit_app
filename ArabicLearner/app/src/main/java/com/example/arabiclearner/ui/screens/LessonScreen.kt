package com.example.arabiclearner.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.arabiclearner.data.ProgressRepository
import com.example.arabiclearner.data.SampleData
import com.example.arabiclearner.data.Word
import kotlinx.coroutines.launch

@Composable
fun LessonScreen(
    lessonId: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val progressRepository = remember { ProgressRepository(context.applicationContext as Application) }
    val scope = rememberCoroutineScope()

    val lesson = SampleData.learningPath.lessons.first { it.id == lessonId }
    val words: List<Word> = lesson.wordIds.mapNotNull { SampleData.words[it] }

    var index by remember { mutableIntStateOf(0) }

    LaunchedEffect(lessonId) {
        scope.launch { progressRepository.setLastOpenedLesson(lessonId) }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(text = lesson.title) },
            navigationIcon = {
                IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back") }
            }
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val current = words[index]
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = current.arabic, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
                Text(text = current.transliteration, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 8.dp))
                Text(text = "EN: ${current.english}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 16.dp))
                Text(text = "UR: ${current.urdu}", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 8.dp))
                Text(text = "${index + 1} / ${words.size}", style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 12.dp))
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { if (index > 0) index -= 1 }, enabled = index > 0) { Text("Previous") }
                if (index < words.size - 1) {
                    Button(onClick = { index += 1 }) { Text("Next") }
                } else {
                    Button(onClick = {
                        scope.launch {
                            progressRepository.markLessonCompleted(lessonId)
                            onBack()
                        }
                    }) { Text("Mark Lesson Complete") }
                }
            }
        }
    }
}