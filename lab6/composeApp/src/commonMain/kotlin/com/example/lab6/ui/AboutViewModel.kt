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

class AboutViewModel(
    private val repository: AboutRepository
) : ViewModel() {

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
        repository.increaseVisitCount()
        repository.updateVisitedDate()
        load()
    }

    private fun load() {
        viewModelScope.launch {

            val info = repository.getSystemInfo()
            val count = repository.getVisitCount()
            val date = repository.getVisitedDate()?.format(format) ?: "-----"

            _state.update {
                it.copy(
                    platformInfo = info,
                    visitedCount = count,
                    visitedDate = date
                )
            }
        }
    }
}