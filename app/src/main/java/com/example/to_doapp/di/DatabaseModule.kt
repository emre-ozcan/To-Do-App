package com.example.to_doapp.di

import android.content.Context
import androidx.room.Room
import com.example.to_doapp.data.AppDatabase
import com.example.to_doapp.utilities.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by @Emre Özcan on 11.12.2023
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideToDoDao(database: AppDatabase) = database.toDoDao()
}