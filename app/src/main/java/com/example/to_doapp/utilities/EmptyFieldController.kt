package com.example.to_doapp.utilities

import com.example.to_doapp.model.Priority

/**
 * Created by @Emre Özcan on 17.12.2023
 */
fun controlIsFieldsSuitableForSave(
    title: String?, description: String?, priority: Priority?, isChecked: Boolean?
): Boolean {
    if (title?.trim().isNullOrEmpty() || description?.trim().isNullOrEmpty() || priority == null || isChecked == null) {
        return false
    }
    return true
}