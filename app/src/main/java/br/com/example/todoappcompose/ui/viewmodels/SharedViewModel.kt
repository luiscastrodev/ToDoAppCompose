package br.com.example.todoappcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.data.repositories.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

    private val _allTaks = MutableStateFlow<List<ToDoTask>>(emptyList())
    val allTaks: StateFlow<List<ToDoTask>> = _allTaks

    fun getallTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { tasks ->
                _allTaks.value = tasks
            }
        }
    }
}