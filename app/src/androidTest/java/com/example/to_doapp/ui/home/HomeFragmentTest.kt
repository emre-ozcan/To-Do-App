package com.example.to_doapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import com.example.to_doapp.R
import com.example.to_doapp.data.AppDatabase
import com.example.to_doapp.data.ToDoDao
import com.example.to_doapp.launchFragmentInHiltContainer
import com.example.to_doapp.model.Priority
import com.example.to_doapp.model.ToDoModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.mockito.Mockito.mock
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by @Emre Özcan on 17.12.2023
 */
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromHomeToNewAndEditFragmentNewToDo()  {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<HomeFragment> {
            Navigation.setViewNavController(requireView(), navController)

        }

        Espresso.onView(ViewMatchers.withId(R.id.fragment_home_fab)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(R.id.action_homeFragment_to_newAndEditFragment)
    }
}