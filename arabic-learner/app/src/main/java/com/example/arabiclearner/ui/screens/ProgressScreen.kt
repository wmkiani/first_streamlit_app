package com.example.arabiclearner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.arabiclearner.R
import com.example.arabiclearner.viewmodel.UiState

@Composable
fun ProgressScreen(state: UiState) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "${stringResource(R.string.overall_progress)}: ${state.overallProgressPercent}%", style = MaterialTheme.typography.titleLarge)
        LinearProgressIndicator(progress = { state.overallProgressPercent / 100f })

        state.learningPaths.forEach { path ->
            val total = path.lessons.size
            val completed = path.lessons.count { state.completedLessons.contains(it.id) }
            val percent = if (total == 0) 0 else (completed * 100 / total)
            Text(text = "${path.title}: $percent%", style = MaterialTheme.typography.titleMedium)
            LinearProgressIndicator(progress = { percent / 100f })
        }
    }
}