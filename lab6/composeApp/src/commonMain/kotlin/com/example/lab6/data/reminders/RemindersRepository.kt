package com.example.lab6.data.reminders

import com.example.lab6.data.common.db.LocalDataSource

internal class RemindersRepository(
    private val localDataSource: LocalDataSource
) {
    val reminders: List<Reminder>
        get() = localDataSource.getAllTasks().map { it.map() }

    fun createReminder(title: String) {
        if (title.isNotBlank()) localDataSource.insertTask(title)
    }

    fun markReminder(id: Long, isCompleted: Boolean) {
        if (isCompleted) localDataSource.markTaskCompleted(id)
        else localDataSource.markTaskPending(id)
    }


    fun deleteTaskById(id: Long) {
        localDataSource.deleteTaskById(id)
    }
}