package com.ashfaq.orbitplanner.notifications

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object PendingTaskReminderScheduler {
    private const val DAILY_PENDING_TASK_WORK_NAME = "daily_pending_task_reminder"
    private const val REMINDER_HOUR_OF_DAY = 18

    fun scheduleDailyReminder(context: Context) {
        val reminderRequest = PeriodicWorkRequestBuilder<PendingTaskReminderWorker>(
            1,
            TimeUnit.DAYS
        )
            .setInitialDelay(millisUntilNextReminderWindow(), TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            DAILY_PENDING_TASK_WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            reminderRequest
        )
    }

    private fun millisUntilNextReminderWindow(): Long {
        val now = Calendar.getInstance()
        val reminderTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, REMINDER_HOUR_OF_DAY)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            if (!after(now)) {
                add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        return reminderTime.timeInMillis - now.timeInMillis
    }
}
