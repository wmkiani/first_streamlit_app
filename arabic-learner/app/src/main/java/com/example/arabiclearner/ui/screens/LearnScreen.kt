package com.example.arabiclearner.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.arabiclearner.R
import com.example.arabiclearner.viewmodel.UiState

@Composable
fun LearnScreen(
    state: UiState,
    onOpenLesson: (String) -> Unit,
    onToggleComplete: (String, Boolean) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        state.learningPaths.forEach { path ->
            item(key = path.id) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = path.title, style = MaterialTheme.typography.titleLarge)
                    Text(text = path.description, style = MaterialTheme.typography.bodyMedium)
                }
            }
            items(path.lessons, key = { it.id }) { lesson ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenLesson(lesson.id) }
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = lesson.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = "${lesson.words.size} ${stringResource(R.string.words)}", style = MaterialTheme.typography.bodySmall)
                    }
                    Checkbox(
                        checked = state.completedLessons.contains(lesson.id),
                        onCheckedChange = { onToggleComplete(lesson.id, it) }
                    )
                }
                Divider()
            }
        }
    }
}