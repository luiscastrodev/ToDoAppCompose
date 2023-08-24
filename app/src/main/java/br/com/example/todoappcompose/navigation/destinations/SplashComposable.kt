package br.com.example.todoappcompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.example.todoappcompose.ui.splash.SplashScreen
import br.com.example.todoappcompose.util.Constants

fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit,
) {
    composable(
        route = Constants.SPLSH_SCREEN,
    ) {
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}