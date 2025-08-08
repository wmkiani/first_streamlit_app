package com.example.arabiclearner.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.arabiclearner.R
import com.example.arabiclearner.ui.screens.HomeScreen
import com.example.arabiclearner.ui.screens.LearnScreen
import com.example.arabiclearner.ui.screens.LessonScreen
import com.example.arabiclearner.ui.screens.ProgressScreen
import com.example.arabiclearner.ui.theme.AppTheme
import com.example.arabiclearner.viewmodel.AppViewModel

sealed class Screen(val route: String, val icon: ImageVector) {
    data object Home : Screen("home", Icons.Filled.Home)
    data object Learn : Screen("learn", Icons.Filled.List)
    data object Progress : Screen("progress", Icons.Filled.TrendingUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArabicLearnerApp(viewModel: AppViewModel = viewModel()) {
    AppTheme {
        val navController = rememberNavController()
        val screens = listOf(Screen.Home, Screen.Learn, Screen.Progress)
        Scaffold(
            bottomBar = {
                BottomBar(navController = navController, items = screens)
            }
        ) { innerPadding ->
            NavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController, items: List<Screen>) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            val label = when (screen) {
                Screen.Home -> stringResource(R.string.home)
                Screen.Learn -> stringResource(R.string.learn)
                Screen.Progress -> stringResource(R.string.progress)
            }
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(text = label) }
            )
        }
    }
}

@Composable
private fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            HomeScreen(
                state = viewModel.uiState,
                onContinue = {
                    val next = viewModel.findNextIncompleteLessonId()
                    if (next != null) navController.navigate("lesson/$next")
                    else navController.navigate(Screen.Learn.route)
                }
            )
        }
        composable(Screen.Learn.route) {
            LearnScreen(
                state = viewModel.uiState,
                onOpenLesson = { lessonId -> navController.navigate("lesson/$lessonId") },
                onToggleComplete = { lessonId, completed -> viewModel.setLessonCompleted(lessonId, completed) }
            )
        }
        composable("lesson/{lessonId}") { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId").orEmpty()
            LessonScreen(
                lesson = viewModel.getLesson(lessonId),
                isCompleted = viewModel.isLessonCompleted(lessonId),
                onBack = { navController.popBackStack() },
                onMarkComplete = { viewModel.setLessonCompleted(lessonId, true) }
            )
        }
        composable(Screen.Progress.route) {
            ProgressScreen(state = viewModel.uiState)
        }
    }
}