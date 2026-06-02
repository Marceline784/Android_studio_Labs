package com.example.lab6.data.reminders

import com.example.lab6.Task

internal data class Reminder(
    val id: Long,
    val title: String,
    val isCompleted: Boolean
)

internal fun Task.map(): Reminder = Reminder(
    id = id,
    title = task_desc,
    isCompleted = is_completed == 1L
)