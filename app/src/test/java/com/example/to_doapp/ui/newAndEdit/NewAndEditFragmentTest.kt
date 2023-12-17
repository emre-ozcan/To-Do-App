package com.example.to_doapp.ui.newAndEdit

import com.example.to_doapp.model.Priority
import com.example.to_doapp.utilities.controlIsFieldsSuitableForSave
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Created by @Emre Özcan on 17.12.2023
 */
class NewAndEditFragmentTest {
    @Test
    fun `control todo model when title empty returns false`() {
        val value = controlIsFieldsSuitableForSave("", "Test", Priority.HIGH, true)

        assertThat(value).isFalse()
    }

    @Test
    fun `control todo model when description empty returns false`() {
        val value = controlIsFieldsSuitableForSave("Test", "", Priority.HIGH, true)

        assertThat(value).isFalse()
    }

    @Test
    fun `control todo model when priority null returns false`() {
        val value = controlIsFieldsSuitableForSave("Test", "Test",null, true)

        assertThat(value).isFalse()
    }

    @Test
    fun `control todo model when isChecked null returns false`() {
        val value = controlIsFieldsSuitableForSave("Test", "Test", Priority.HIGH, null)

        assertThat(value).isFalse()
    }
}