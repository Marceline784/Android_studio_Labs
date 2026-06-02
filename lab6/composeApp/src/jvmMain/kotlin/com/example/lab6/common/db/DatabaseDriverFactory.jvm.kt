package com.example.lab6.data.common.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.example.lab6.Organise
import java.io.File

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        val dbPath = "${System.getProperty("user.home")}/.lab6/organise.db"
        File(dbPath).parentFile?.mkdirs()
        val driver: SqlDriver = JdbcSqliteDriver("jdbc:sqlite:$dbPath")

        if (!File(dbPath).exists()) {
            Organise.Schema.create(driver)
        }
        return driver
    }
}