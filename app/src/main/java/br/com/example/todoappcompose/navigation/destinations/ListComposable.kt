package br.com.example.todoappcompose.navigation.destinations

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.example.todoappcompose.util.Action
import br.com.example.todoappcompose.util.Constants
import br.com.example.todoappcompose.util.Constants.LIST_ARGUMENT_KEY


fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {

    }
}