package com.example.to_doapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.to_doapp.model.ToDoModel

/**
 * Created by @Emre Özcan on 10.12.2023
 */
@Database(entities = [ToDoModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}