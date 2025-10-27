package com.example.arabiclearner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.arabiclearner.ui.theme.ArabicLearnerTheme
import com.example.arabiclearner.ui.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArabicLearnerApp()
        }
    }
}

@Composable
fun ArabicLearnerApp() {
    ArabicLearnerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AppNavHost(modifier = Modifier)
        }
    }
}