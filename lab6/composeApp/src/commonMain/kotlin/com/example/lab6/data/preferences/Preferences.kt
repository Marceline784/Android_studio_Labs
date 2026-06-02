package com.example.lab6.data.preferences

interface Preferences {

    var aboutVisitedCount: Int

    var aboutVisitedDate: String?

    fun cleanStorage()
}