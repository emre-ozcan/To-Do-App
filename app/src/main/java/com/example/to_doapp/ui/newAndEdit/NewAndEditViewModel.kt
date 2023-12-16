package com.example.to_doapp.ui.newAndEdit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.to_doapp.data.Repository
import com.example.to_doapp.model.Priority
import com.example.to_doapp.model.ToDoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 13.12.2023
 */
@HiltViewModel
class NewAndEditViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
): AndroidViewModel(application) {

    val toDoModel = MutableLiveData<ToDoModel>()

    fun insertToDo(title: String, description: String, isCheck: Boolean, priority: Priority) {
        viewModelScope.launch {
            repository.localDataSource.insertToDo(
                ToDoModel(
                    title = title,
                    description = description,
                    priority = priority,
                    isChecked = isCheck
                )
            )
        }
    }

    fun getToDo(toDoId: Int) {
        viewModelScope.launch {
            val toDo = repository.localDataSource.getToDo(toDoId)
            toDoModel.value = toDo
        }
    }

    fun updateToDo(title: String, description: String, isCheck: Boolean, priority: Priority) {
        viewModelScope.launch {
            repository.localDataSource.updateToDo(ToDoModel(
                id = toDoModel.value?.id ?: 0,
                title = title,
                description = description,
                isChecked = isCheck,
                priority = priority
            ))
        }
    }

    fun deleteToDo() {
        viewModelScope.launch {
            toDoModel.value?.let {
                repository.localDataSource.deleteToDo(it)
            }
        }
    }
}