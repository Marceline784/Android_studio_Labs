package com.example.lab6.data.preferences

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class AppPreferences(
    private val settings: Settings
) : Preferences {

    override var aboutVisitedCount: Int
        get() = settings.get(PreferenceKey.ABOUT_VISITED_COUNT.key, 0)
        set(value) {
            settings[PreferenceKey.ABOUT_VISITED_COUNT.key] = value
        }

    override var aboutVisitedDate: String?
        get() = settings.get(PreferenceKey.ABOUT_VISITED_DATE.key, "")
            .takeIf { it.isNotEmpty() }

        set(value) {
            if (value == null) return
            settings[PreferenceKey.ABOUT_VISITED_DATE.key] = value
        }

    override fun cleanStorage() {
        settings.clear()
    }
}