package br.com.example.todoappcompose.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.example.todoappcompose.data.models.Priority
import br.com.example.todoappcompose.data.models.ToDoTask
import br.com.example.todoappcompose.data.repositories.ToDoRepository
import br.com.example.todoappcompose.util.Constants.MAX_TITLE_LENGTH
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

    val id : MutableState<Int> = mutableStateOf(0)
    val title: MutableState<String> = mutableStateOf("")
    val description: MutableState<String> = mutableStateOf("")
    val priority: MutableState<Priority> = mutableStateOf(Priority.LOW)


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

    fun updateTaskFields(selectedTask : ToDoTask?){
        if(selectedTask !=null){
            id.value = selectedTask.id
            title.value = selectedTask.title
            description.value = selectedTask.description
            priority.value = selectedTask.priority
        }else{
            id.value = 0
            title.value = ""
            description.value = ""
            priority.value = Priority.LOW
        }
    }

    fun updateTitle(newTitle : String){
        if(newTitle.length < MAX_TITLE_LENGTH){
            title.value = newTitle
        }
    }

    fun validadeFields() : Boolean{
        return title.value.isNotEmpty() && description.value.isNotEmpty()
    }
}