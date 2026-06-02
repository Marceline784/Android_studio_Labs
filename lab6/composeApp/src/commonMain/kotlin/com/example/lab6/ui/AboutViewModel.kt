package com.example.lab6.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab6.data.AboutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.char

class AboutViewModel(private val repository: AboutRepository) : ViewModel() {

    private val format = LocalDateTime.Format {
        dayOfMonth()
        char('.')
        monthNumber()
        char('.')
        year()
        char(' ')
        hour()
        char(':')
        minute()
    }

    private val _state = MutableStateFlow(AboutState())
    val state = _state.asStateFlow()

    init {
        load()
        repository.increaseVisitCount()
        repository.updateVisitedDate()
    }

    private fun load() {
        viewModelScope.launch {
            val lastDate = repository.getVisitedDate()
            _state.update {
                it.copy(
                    platformInfo = repository.getSystemInfo(),
                    visitedCount = repository.getVisitCount(),
                    visitedDate = lastDate?.format(format) ?: "First visit"
                )
            }
        }
    }
}