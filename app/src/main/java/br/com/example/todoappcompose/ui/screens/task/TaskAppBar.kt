package br.com.example.todoappcompose.ui.screens.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import br.com.example.todoappcompose.ui.theme.Nunito
import br.com.example.todoappcompose.ui.theme.Purple80
import br.com.example.todoappcompose.util.Action

@Composable
fun TaskAppBar(
    navigaToListScreen : (Action) -> Unit
) {
    NewTaskAppBar(navigaToListScreen = navigaToListScreen)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskAppBar(
    navigaToListScreen : (Action) -> Unit
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