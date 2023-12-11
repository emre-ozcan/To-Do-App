package com.example.to_doapp.data

import com.example.to_doapp.model.ToDoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 11.12.2023
 */
class LocalDataSource @Inject constructor(
    private val toDoDao: ToDoDao
) {

    fun getAllToDo(): Flow<List<ToDoModel>> {
        return toDoDao.getAllToDo()
    }

    fun searchToDo(searchQuery: String): Flow<List<ToDoModel>> {
        return toDoDao.searchToDo(searchQuery)
    }

    suspend fun insertToDo(toDoModel: ToDoModel) {
        toDoDao.insertToDo(toDoModel)
    }

    suspend fun getToDo(toDoId: Int): ToDoModel {
        return toDoDao.getToDo(toDoId)
    }

    suspend fun updateToDo(toDoModel: ToDoModel) {
        toDoDao.updateToDo(toDoModel)
    }

    suspend fun deleteToDo(toDoModel: ToDoModel) {
        toDoDao.deleteToDo(toDoModel)
    }
}