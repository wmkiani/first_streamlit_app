package com.example.arabiclearner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.arabiclearner.R
import com.example.arabiclearner.viewmodel.UiState

@Composable
fun HomeScreen(
    state: UiState,
    onContinue: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.word_of_day),
            style = MaterialTheme.typography.titleLarge
        )
        Card(modifier = Modifier.fillMaxSize().weight(1f)) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.dailyWord.word, style = MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center)
                Spacer(Modifier.height(8.dp))
                Text(text = state.dailyWord.transliteration, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(8.dp))
                Text(text = state.dailyWord.meaning, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
            }
        }
        Text(text = "${stringResource(R.string.overall_progress)}: ${state.overallProgressPercent}%")
        LinearProgressIndicator(progress = { state.overallProgressPercent / 100f })
        Button(onClick = onContinue) { Text(stringResource(R.string.continue_learning)) }
    }
}