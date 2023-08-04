package br.com.example.todoappcompose.navigation

import androidx.navigation.NavHostController
import br.com.example.todoappcompose.util.Action
import br.com.example.todoappcompose.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) {
                inclusive = true
            }
        }
    }

    val task: (Int) -> Unit = {taskId ->
        navController.navigate("task/$taskId")
    }
}