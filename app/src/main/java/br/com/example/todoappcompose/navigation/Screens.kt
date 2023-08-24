package br.com.example.todoappcompose.navigation

import androidx.navigation.NavHostController
import br.com.example.todoappcompose.util.Action
import br.com.example.todoappcompose.util.Constants.LIST_SCREEN
import br.com.example.todoappcompose.util.Constants.SPLSH_SCREEN

class Screens(navController: NavHostController) {

    val splash: () -> Unit = {
        navController.navigate("list/${Action.NO_ACTION.name}") {
            popUpTo(SPLSH_SCREEN) { inclusive = true }
        }
    }

    val list: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }

    val task: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) {
                inclusive = true
            }
        }
    }

}