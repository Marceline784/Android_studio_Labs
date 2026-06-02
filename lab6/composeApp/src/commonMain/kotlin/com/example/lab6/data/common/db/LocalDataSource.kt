package com.example.lab6.data.common.db

import com.example.lab6.Task

interface LocalDataSource {
    fun insertTask(description: String)
    fun getAllTasks(): List<Task>
    fun markTaskCompleted(id: Long)
    fun markTaskPending(id: Long)
    fun deleteTaskById(id: Long)
}