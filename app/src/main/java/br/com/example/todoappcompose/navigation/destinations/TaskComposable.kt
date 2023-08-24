package br.com.example.todoappcompose.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.example.todoappcompose.ui.screens.task.TaskScreen
import br.com.example.todoappcompose.ui.viewmodels.SharedViewModel
import br.com.example.todoappcompose.util.Action
import br.com.example.todoappcompose.util.Constants
import br.com.example.todoappcompose.util.Constants.TASK_ARGUMENT_KEY


fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        
        LaunchedEffect(key1 = taskId){
            sharedViewModel.getSelectedTask(taskId)
        }
        
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask){
            Log.d("LaunchedEffect","NavGraphBuilder")
            if(selectedTask != null || taskId == -1){
                sharedViewModel.updateTaskFields(selectedTask)
            }
        }
        TaskScreen(
            navigaToListScreen = navigateToListScreen,
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel
        )
    }
}