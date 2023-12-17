package com.example.to_doapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.to_doapp.di.ToDoApplication
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Created by @Emre Özcan on 17.12.2023
 */
class HiltTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}