package com.ashfaq.orbitplanner.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

object NotificationHelper {
    const val PENDING_TASK_CHANNEL_ID = "pending_task_reminders"

    private const val PENDING_TASK_CHANNEL_NAME = "Pending task reminders"
    private const val PENDING_TASK_CHANNEL_DESCRIPTION =
        "Gentle reminders for unfinished Orbit Planner tasks."

    fun createPendingTaskReminderChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            PENDING_TASK_CHANNEL_ID,
            PENDING_TASK_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = PENDING_TASK_CHANNEL_DESCRIPTION
        }

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    fun hasPostNotificationsPermission(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return true

        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun shouldRequestPostNotificationsPermission(context: Context): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            !hasPostNotificationsPermission(context)
    }
}
