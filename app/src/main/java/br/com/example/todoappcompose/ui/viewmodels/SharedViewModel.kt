package br.com.example.todoappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.data.repositories.ToDoRepository
import br.com.example.todoappcompose.util.RequestState
import br.com.example.todoappcompose.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
) : ViewModel() {

     val searchAppBarState : MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)

     val searchTextState : MutableState<String> = mutableStateOf("")

    private val _allTaks = MutableStateFlow<RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTaks: StateFlow<RequestState<List<ToDoTask>>> = _allTaks

    fun getallTasks() {
        _allTaks.value = RequestState.Loading

        try {

            viewModelScope.launch {
                repository.getAllTasks().collect { tasks ->
                    _allTaks.value = RequestState.Success(tasks)
                }
            }
        }catch (e: Exception){
            _allTaks.value = RequestState.Error(e)
        }
    }

    private val _selectedTask : MutableStateFlow<ToDoTask?> = MutableStateFlow(null)
    val selectedTask : StateFlow<ToDoTask?> = _selectedTask

    fun getSelectedTask(taskId : Int){
        viewModelScope.launch {
            repository.getSelectedTask(taskId).collect{
                task ->

                _selectedTask.value = task
            }
        }
    }
}