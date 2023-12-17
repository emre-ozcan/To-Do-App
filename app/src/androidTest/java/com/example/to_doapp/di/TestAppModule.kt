package com.example.to_doapp.di

import android.content.Context
import androidx.room.Room
import com.example.to_doapp.data.AppDatabase
import com.example.to_doapp.data.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/**
 * Created by @Emre Özcan on 17.12.2023
 */
@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Provides
    @Named("TestDB")
    fun injectInMemoryRoom(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries().build()
    }

    @Provides
    @Named("TestDao")
    fun injectTestDao(@Named("TestDB") appDatabase: AppDatabase): ToDoDao {
        return appDatabase.toDoDao()
    }
}