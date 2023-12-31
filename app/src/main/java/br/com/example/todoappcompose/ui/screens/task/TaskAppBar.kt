package br.com.example.todoappcompose.ui.screens.task

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import br.com.example.todoappcompose.components.DisplayAlertDialog
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.ui.theme.Nunito
import br.com.example.todoappcompose.ui.theme.Purple80
import br.com.example.todoappcompose.ui.theme.TOP_APP_BAR_HEIGHT
import br.com.example.todoappcompose.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigaToListScreen: (Action) -> Unit
) {
    if (selectedTask == null) {
        NewTaskAppBar(navigaToListScreen = navigaToListScreen)
    } else {
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigaToListScreen = navigaToListScreen
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigaToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigaToListScreen)
        },
        title = {
            Text(
                text = "Add Task",
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Purple80
        ),
        actions = {
            AddAction(onAddClicked = navigaToListScreen)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigaToListScreen: (Action) -> Unit
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigaToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Purple80
        ),
        actions = {
            ExistingTaskAppBarAction(
                selectedTask = selectedTask,
                navigaToListScreen = navigaToListScreen
            )
        }
    )
}

@Composable
fun ExistingTaskAppBarAction(
    selectedTask: ToDoTask,
    navigaToListScreen: (Action) -> Unit
) {
    var openDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = "Are you sure you want to remove?",
        message = "Task will be delete ${selectedTask.title}",
        openDialog = openDialog,
        closeDialog = {
            openDialog = false
        },
        onYesClicked = {
            navigaToListScreen(Action.DELETE)
        },
    )

    DeleteAction(onDeleteClicked = {
        openDialog = true
    })
    UpdateAction(onUpdateClicked = navigaToListScreen)
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onBackClicked(Action.NO_ACTION)
    }
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = " Back Arrow ")
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onAddClicked(Action.ADD)
    }
    ) {
        Icon(imageVector = Icons.Filled.Check, contentDescription = " Add Task ")
    }
}


@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onCloseClicked(Action.NO_ACTION)
    }
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = " Close Icon "
        )
    }
}


@Composable
fun DeleteAction(
    onDeleteClicked: () -> Unit
) {
    IconButton(onClick = {
        onDeleteClicked()
    }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = " Close Icon "
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onUpdateClicked(Action.UPDATE)
    }
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = " Close Icon "
        )
    }
}