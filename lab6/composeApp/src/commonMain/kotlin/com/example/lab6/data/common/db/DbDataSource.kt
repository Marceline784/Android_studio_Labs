package com.example.lab6.data.common.db

import com.example.lab6.Organise
import com.example.lab6.Task

class DbDataSource(private val db: Organise) : LocalDataSource {

    override fun insertTask(description: String) {
        db.tasksQueries.insertTask(description)
    }

    override fun getAllTasks(): List<Task> {
        return db.tasksQueries.selectAllTasks().executeAsList()
    }

    override fun markTaskCompleted(id: Long) {
        db.tasksQueries.markTaskCompleted(id = id)
    }

    override fun markTaskPending(id: Long) {
        db.tasksQueries.markTaskPending(id = id)
    }

    override fun deleteTaskById(id: Long) {

        db.tasksQueries.deleteTaskById(id = id)
    }
}