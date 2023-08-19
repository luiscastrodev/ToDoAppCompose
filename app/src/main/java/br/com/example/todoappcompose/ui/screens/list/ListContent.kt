package br.com.example.todoappcompose.ui.screens.list
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import br.com.example.todoappcompose.data.models.Priority
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.ui.theme.LARGE_PADDING
import br.com.example.todoappcompose.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun ListContent(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {

}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shape = RectangleShape,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth(),
        ) {
            Row(

            ) {
                Text(
                    text = toDoTask.title, color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.weight(8f)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .width(PRIORITY_INDICATOR_SIZE)
                            .height(
                                PRIORITY_INDICATOR_SIZE
                            )
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }

            }
            Text(
                text = toDoTask.description,
                modifier = Modifier.fillMaxWidth(),
                color = Color.DarkGray,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(0, "New Car", "My new car will be soon", Priority.MEDIUM),
        navigateToTaskScreen = {})
}