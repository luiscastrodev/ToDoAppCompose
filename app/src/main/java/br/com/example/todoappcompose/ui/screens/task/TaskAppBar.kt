package br.com.example.todoappcompose.ui.screens.task

import android.widget.Toast
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.ui.theme.Nunito
import br.com.example.todoappcompose.ui.theme.Purple80
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
            DeleteAction(onDeleteClicked = navigaToListScreen)
            UpdateAction(onUpdateClicked = navigaToListScreen)
        }
    )
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
    onDeleteClicked: (Action) -> Unit
) {
    IconButton(onClick = {
        onDeleteClicked(Action.DELETE)
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