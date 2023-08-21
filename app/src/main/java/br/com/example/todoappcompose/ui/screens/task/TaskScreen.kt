package br.com.example.todoappcompose.ui.screens.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.example.todoappcompose.data.models.Priority
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.ui.theme.TOP_APP_BAR_HEIGHT
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
        }
    ){
        TaskContent(
            title = "",
            onTitleChange = {} ,
            description = "",
            onDescriptionChange = {} ,
            priority = Priority.HIGH,
            onPrioritySelected = {}
        )
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen(selectedTask = ToDoTask(0,"","",Priority.HIGH), navigaToListScreen = {})
}