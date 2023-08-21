package br.com.example.todoappcompose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.example.todoappcompose.components.PriorotyDropDown
import br.com.example.todoappcompose.data.models.Priority
import br.com.example.todoappcompose.ui.theme.LARGE_PADDING

@ExperimentalMaterial3Api
@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = "Title") },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(5.dp))
        PriorotyDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = "Description") },
            textStyle = MaterialTheme.typography.bodyMedium,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TaskContentPreview() {
    TaskContent(
        title = "",
        onTitleChange = {} ,
        description = "",
        onDescriptionChange = {} ,
        priority = Priority.HIGH,
        onPrioritySelected = {}
    )
}