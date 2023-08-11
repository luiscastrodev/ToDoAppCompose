package br.com.example.todoappcompose.data.models

import androidx.compose.ui.graphics.Color
import br.com.example.todoappcompose.ui.theme.HighPriorityColor
import br.com.example.todoappcompose.ui.theme.LowPriorityColor
import br.com.example.todoappcompose.ui.theme.MediumPriorityColor
import br.com.example.todoappcompose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}