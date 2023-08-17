package br.com.example.todoappcompose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import br.com.example.todoappcompose.R
import br.com.example.todoappcompose.components.PriorityItem
import br.com.example.todoappcompose.data.models.Priority
import br.com.example.todoappcompose.ui.theme.LARGE_PADDING
import br.com.example.todoappcompose.ui.theme.Nunito
import br.com.example.todoappcompose.ui.theme.Purple80
import br.com.example.todoappcompose.ui.theme.TOP_APP_BAR_HEIGHT
import br.com.example.todoappcompose.ui.viewmodels.SharedViewModel
import br.com.example.todoappcompose.util.SearchAppBarState
import br.com.example.todoappcompose.util.TralingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {

    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(onSearchClicked = {
                sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
            },
                onSortClicked = { },
                onDeleteClicked = { }
            )
        }

        else -> {
            SearchAppBar(text = searchTextState, onTextChange = { newText ->
                sharedViewModel.searchTextState.value = newText
            },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Purple80
        ),
        actions = {
            ListAppBarActions(
                onSearchClicked,
                onSortClicked,
                onDeleteClicked
            )
        }
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteClicked = onDeleteClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_action),
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.filter_list),
            contentDescription = stringResource(R.string.sort_action)
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {

            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.LOW)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                },
            )
            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.MEDIUM)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.MEDIUM)
                },
            )
            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.HIGH)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
            )
            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.NONE)
                },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                },
            )
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.delete),
            contentDescription = stringResource(R.string.sort_action)
        )

        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.delete_all),
                        modifier = Modifier.padding(LARGE_PADDING)
                    )
                },
                onClick = {
                    expanded = false
                    onDeleteClicked
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (text: String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (text: String) -> Unit,
) {

    var tralingIconState by remember { mutableStateOf(TralingIconState.READY_TO_DELETE) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
    ) {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Purple80,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent

            ),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search", color = Color.White, modifier = Modifier.alpha(0.5f))
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                background = Purple80,
                fontFamily = Nunito,
                fontWeight = FontWeight.Bold,
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(0.5f), onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color.Black
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    when(tralingIconState){
                        TralingIconState.READY_TO_DELETE -> {
                            onTextChange("")
                            tralingIconState = TralingIconState.READY_TO_CLOSE
                        }
                        TralingIconState.READY_TO_CLOSE -> {
                            if(text.isNotEmpty()){
                                onTextChange("")
                            }else{
                                onCloseClicked()
                                tralingIconState = TralingIconState.READY_TO_DELETE
                            }
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }),
        )
    }
}

@Composable
@Preview
fun DefaultAppBarPreview() {
    DefaultListAppBar({}, {}, {})
}

@Composable
@Preview
fun SearchAppBarPreview() {
    SearchAppBar(text = "Serach", onTextChange = {}, onCloseClicked = {}) { }
}