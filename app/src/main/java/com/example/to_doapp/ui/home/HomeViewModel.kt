package com.example.to_doapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.Repository
import com.example.to_doapp.model.Priority
import com.example.to_doapp.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 12.12.2023
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application, private val repository: Repository
): AndroidViewModel(application){

    val toDoList = repository.localDataSource.getAllToDo().asLiveData()
    var searchToDoList: LiveData<List<ToDoModel>> = MutableLiveData()
    val searchQuery = MutableLiveData("")

    fun updateToDo(toDoModel: ToDoModel) {
        val updatedToDoModel = toDoModel.copy(isChecked =  toDoModel.isChecked?.not())
        viewModelScope.launch {
            repository.localDataSource.updateToDo(updatedToDoModel)
        }
    }

    fun searchToDo(searchQuery: String) {
        searchToDoList = repository.localDataSource.searchToDo("%$searchQuery%").asLiveData()
        this.searchQuery.value = searchQuery
    }


}