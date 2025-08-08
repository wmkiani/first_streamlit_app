package com.example.arabiclearner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.arabiclearner.R
import com.example.arabiclearner.data.Lesson
import com.example.arabiclearner.data.meaningFor
import java.util.Locale

@Composable
fun LessonScreen(
    lesson: Lesson?,
    isCompleted: Boolean,
    onBack: () -> Unit,
    onMarkComplete: () -> Unit
) {
    if (lesson == null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
            Text(text = stringResource(R.string.lesson_not_found))
        }
        return
    }

    val locale = Locale.getDefault().language
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = lesson.title, style = MaterialTheme.typography.titleLarge)
        Text(text = lesson.description, style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(8.dp))
        Text(text = stringResource(R.string.words), style = MaterialTheme.typography.titleMedium)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(lesson.words) { w ->
                Text(text = "${w.word} (${w.transliteration}) — ${w.meaningFor(locale)}")
            }
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onMarkComplete, enabled = !isCompleted) { Text(if (isCompleted) stringResource(R.string.completed) else stringResource(R.string.mark_complete)) }
    }
}