package com.example.to_doapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.test.filters.SmallTest
import com.example.to_doapp.getOrAwaitValueTest
import com.example.to_doapp.model.Priority
import com.example.to_doapp.model.ToDoModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by @Emre Özcan on 17.12.2023
 */

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class LocalDataSourceTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("TestDB")
    lateinit var database: AppDatabase

    @Inject
    @Named("TestDao")
    lateinit var dao: ToDoDao

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun searchToDo() = runTest {
        val testToDo = ToDoModel(id = 1, title = "Buy Ticket", description = "Evgeny Gringo Concert", priority = Priority.HIGH, isChecked = true)

        dao.insertToDo(testToDo)
        val list = dao.searchToDo("%Buy%").asLiveData().getOrAwaitValueTest()
        assertThat(list).contains(testToDo)
    }

    @Test
    fun searchToDoEmptyList() = runTest {
        val testToDo = ToDoModel(id = 1, title = "Buy Ticket", description = "Evgeny Gringo Concert", priority = Priority.HIGH, isChecked = true)

        dao.insertToDo(testToDo)
        val list = dao.searchToDo("%Test%").asLiveData().getOrAwaitValueTest()
        assertThat(list).doesNotContain(testToDo)
    }

    @Test
    fun insertToDo() = runTest {
        val testToDo = ToDoModel(id = 1, title = "Buy Ticket", description = "Evgeny Gringo Concert", priority = Priority.HIGH, isChecked = true)

        dao.insertToDo(testToDo)
        val list = dao.getAllToDo().asLiveData().getOrAwaitValueTest()
        assertThat(list).contains(testToDo)
    }

    @Test
    fun updateToDo() = runTest {
        val testToDo = ToDoModel(id = 1, title = "Buy Ticket", description = "Evgeny Gringo Concert", priority = Priority.HIGH, isChecked = true)
        dao.insertToDo(testToDo)

        val updatedToDo = testToDo.copy(title = "Test")
        dao.updateToDo(updatedToDo)

        val list = dao.getAllToDo().asLiveData().getOrAwaitValueTest()
        assertThat(list).contains(updatedToDo)
    }

    @Test
    fun deleteToDo() = runTest {
        val testToDo = ToDoModel(id = 1, title = "Buy Ticket", description = "Evgeny Gringo Concert", priority = Priority.HIGH, isChecked = true)
        dao.insertToDo(testToDo)

        dao.deleteToDo(testToDo)

        val list = dao.getAllToDo().asLiveData().getOrAwaitValueTest()
        assertThat(list).doesNotContain(testToDo)
    }
}