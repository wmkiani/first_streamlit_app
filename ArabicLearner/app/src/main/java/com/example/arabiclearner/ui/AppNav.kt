package com.example.arabiclearner.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.arabiclearner.ui.screens.HomeScreen
import com.example.arabiclearner.ui.screens.LearningPathScreen
import com.example.arabiclearner.ui.screens.LessonScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object LearningPath : Screen("learning_path")
    data object Lesson : Screen("lesson/{lessonId}") {
        fun route(lessonId: String) = "lesson/$lessonId"
    }
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onOpenLearningPath = { navController.navigate(Screen.LearningPath.route) },
                onContinueLesson = { lessonId -> navController.navigate(Screen.Lesson.route(lessonId)) }
            )
        }
        composable(Screen.LearningPath.route) {
            LearningPathScreen(
                onBack = { navController.popBackStack() },
                onOpenLesson = { lessonId -> navController.navigate(Screen.Lesson.route(lessonId)) }
            )
        }
        composable(
            route = Screen.Lesson.route,
            arguments = listOf(navArgument("lessonId") { type = NavType.StringType })
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: return@composable
            LessonScreen(
                lessonId = lessonId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}