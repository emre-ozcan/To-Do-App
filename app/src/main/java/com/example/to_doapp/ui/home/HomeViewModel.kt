package com.example.to_doapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    fun insertToDo() {
        viewModelScope.launch {
            repository.localDataSource.insertToDo(ToDoModel(title = "Deneme", description = "Description", priority = Priority.HIGH , isChecked = true))
        }
    }
}