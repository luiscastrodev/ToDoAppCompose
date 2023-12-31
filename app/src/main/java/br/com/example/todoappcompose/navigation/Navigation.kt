package br.com.example.todoappcompose.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.example.todoappcompose.navigation.destinations.listComposable
import br.com.example.todoappcompose.navigation.destinations.splashComposable
import br.com.example.todoappcompose.navigation.destinations.taskComposable
import br.com.example.todoappcompose.ui.screens.list.ListScreen
import br.com.example.todoappcompose.ui.viewmodels.SharedViewModel
import br.com.example.todoappcompose.util.Constants
import com.google.accompanist.navigation.animation.AnimatedNavHost


@Composable
fun SetupNavigation(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController) {
        Screens(navHostController)
    }
    NavHost(
        navController = navHostController,
        startDestination = Constants.SPLSH_SCREEN
    ) {

        splashComposable(
            navigateToListScreen = screen.splash
        )
        listComposable(
            navigateToTaskScreen = screen.list,
            sharedViewModel
        )
        taskComposable(
            navigateToListScreen = screen.task,
            sharedViewModel = sharedViewModel
        )
    }
}
