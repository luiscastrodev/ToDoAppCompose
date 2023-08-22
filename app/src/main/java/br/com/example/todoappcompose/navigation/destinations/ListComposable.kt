package br.com.example.todoappcompose.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.example.todoappcompose.ui.screens.list.ListScreen
import br.com.example.todoappcompose.ui.viewmodels.SharedViewModel
import br.com.example.todoappcompose.util.Constants
import br.com.example.todoappcompose.util.Constants.LIST_ARGUMENT_KEY
import br.com.example.todoappcompose.util.toAction


fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = Constants.LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 =  action){
            sharedViewModel.action.value = action
            Log.d("LaunchedEffect","action")
        }
        ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel)
    }
}