package br.com.example.todoappcompose.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.example.todoappcompose.data.models.Priority
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.ui.viewmodels.SharedViewModel
import br.com.example.todoappcompose.util.Action

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navigaToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel,
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask,
                navigaToListScreen = navigaToListScreen
            )
        }
    ) {
        Surface(
            modifier = Modifier.padding(top = 56.dp)
        ) {

            TaskContent(
                title = title,
                onTitleChange = {
                    sharedViewModel.title.value = it
                },
                description = description,
                onDescriptionChange = {
                    sharedViewModel.description.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    }
}

