package com.ovais.composex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovais.composex.playground.home.PlaygroundHomeScreen
import com.ovais.composex.playground.screens.ButtonScreen
import com.ovais.composex.playground.screens.ContainerComponentsScreen
import com.ovais.composex.playground.screens.FeedbackComponentsScreen
import com.ovais.composex.playground.screens.FormComponentsScreen
import com.ovais.composex.playground.screens.SelectionComponentsScreen
import com.ovais.composex.playground.screens.SliderScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Buttons : Screen("buttons")
    object FormComponents : Screen("form_components")
    object SelectionComponents : Screen("selection_components")
    object FeedbackComponents : Screen("feedback_components")
    object ContainerComponents : Screen("container_components")
    object Slider : Screen("slider")
}

@Composable
fun PlaygroundNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            PlaygroundHomeScreen(
                onNavigateToButtons = { navController.navigate(Screen.Buttons.route) },
                onNavigateToForm = { navController.navigate(Screen.FormComponents.route) },
                onNavigateToSelection = { navController.navigate(Screen.SelectionComponents.route) },
                onNavigateToFeedback = { navController.navigate(Screen.FeedbackComponents.route) },
                onNavigateToContainer = { navController.navigate(Screen.ContainerComponents.route) },
                onNavigateToSlider = { navController.navigate(Screen.Slider.route) }
            )
        }

        composable(Screen.Buttons.route) {
            ButtonScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.FormComponents.route) {
            FormComponentsScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.SelectionComponents.route) {
            SelectionComponentsScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.FeedbackComponents.route) {
            FeedbackComponentsScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.ContainerComponents.route) {
            ContainerComponentsScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.Slider.route) {
            SliderScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}

