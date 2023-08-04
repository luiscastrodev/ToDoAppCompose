package br.com.example.todoappcompose.data.repositories

import br.com.example.todoappcompose.data.ToDoDao
import br.com.example.todoappcompose.data.models.ToDoTask
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao
) {
    fun getAllTasks(): Flow<List<ToDoTask>> = toDoDao.getAllTasks()

    fun sortByLowPriority(): Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()

    fun sortByHighPriority(): Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask> = toDoDao.getSelectedTask(taskId)

    suspend fun addTask(toDoTask: ToDoTask) = toDoDao.addTask(toDoTask)

    suspend fun updateTask(toDoTask: ToDoTask) = toDoDao.updateTask(toDoTask)

    suspend fun deleteTask(toDoTask: ToDoTask) = toDoDao.deleteTask(toDoTask)

    suspend fun deleteallTask() = toDoDao.deleteAllTasks()

    /**
     * Search with LIKE
     */
    fun searchDataBase(searchQuery: String): Flow<List<ToDoTask>> =
        toDoDao.searchDatabase(searchQuery)
}