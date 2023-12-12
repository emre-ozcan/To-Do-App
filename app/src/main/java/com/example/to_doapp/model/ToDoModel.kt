package com.example.to_doapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by @Emre Özcan on 10.12.2023
 */
@Entity(tableName = "to_do_table")
data class ToDoModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val description: String?,
    val priority: Priority?,
    val isChecked: Boolean?
) {
    fun areContentsTheSame(newItem: ToDoModel): Boolean {
        return this.title == newItem.title && this.description == newItem.description
                && this.priority == newItem.priority && this.isChecked == newItem.isChecked
    }
}