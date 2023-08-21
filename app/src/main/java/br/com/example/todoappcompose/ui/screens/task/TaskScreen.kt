package br.com.example.todoappcompose.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigaToListScreen: (Action) -> Unit
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask,
                navigaToListScreen = navigaToListScreen
            )
        },
        floatingActionButton = {
        },
        content = {

        }
    )
}