package com.example.lab6.ui.reminders

import androidx.lifecycle.ViewModel
import com.example.lab6.data.reminders.Reminder
import com.example.lab6.data.reminders.RemindersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ReminderViewModel(private val repository: RemindersRepository) : ViewModel() {
    private val _reminders = MutableStateFlow<List<Reminder>>(emptyList())
    val reminders = _reminders.asStateFlow()

    init { load() }

    fun load() {
        _reminders.value = repository.reminders
    }

    fun createReminder(title: String) {
        if (title.isBlank()) return
        repository.createReminder(title)
        load()
    }

    fun markReminder(id: Long, isCompleted: Boolean) {
        repository.markReminder(id, isCompleted)
        load()
    }

    fun deleteReminder(id: Long) {
        repository.deleteTaskById(id)
        load()
    }
}