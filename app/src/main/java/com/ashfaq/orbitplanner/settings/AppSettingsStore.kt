package com.ashfaq.orbitplanner.settings

import android.content.Context

class AppSettingsStore(context: Context) {
    private val preferences = context.applicationContext.getSharedPreferences(
        SETTINGS_FILE_NAME,
        Context.MODE_PRIVATE
    )

    fun isDailyReminderEnabled(): Boolean {
        return preferences.getBoolean(KEY_DAILY_REMINDER_ENABLED, true)
    }

    fun setDailyReminderEnabled(isEnabled: Boolean) {
        preferences.edit()
            .putBoolean(KEY_DAILY_REMINDER_ENABLED, isEnabled)
            .apply()
    }

    private companion object {
        const val SETTINGS_FILE_NAME = "orbit_planner_settings"
        const val KEY_DAILY_REMINDER_ENABLED = "daily_pending_reminder_enabled"
    }
}
