package br.com.example.todoappcompose.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.example.todoappcompose.data.models.Priority
import br.com.example.todoappcompose.ui.theme.PRIORITY_INDICATOR_SIZE


@Composable
fun PriorotyDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f, label = ""
    )

    Row(
        modifier =
        Modifier
            .fillMaxWidth(fraction = 0.94f)
            .height(60.dp)
            .clickable { expanded = true }
            .border(width = 1.dp, color = Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(4.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }

        Text(
            text = priority.name,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .weight(8f)
                .weight(1.5f)
        )

        IconButton(
            onClick = { expanded = true }, modifier = Modifier
                .alpha(0.8f)
                .rotate(degrees = angle)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Drop-Down Arrow"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.LOW)
                },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW)
                },
            )

            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.MEDIUM)
                },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM)
                },
            )

            DropdownMenuItem(
                text = {
                    PriorityItem(priority = Priority.HIGH)
                },
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH)
                },
            )
        }
    }
}

@Preview
@Composable
fun PriorityDropDownPreview() {
    PriorotyDropDown(priority = Priority.HIGH, onPrioritySelected = {})
}