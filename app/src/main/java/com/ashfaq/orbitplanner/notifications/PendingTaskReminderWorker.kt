package com.ashfaq.orbitplanner.notifications

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ashfaq.orbitplanner.MainActivity
import com.ashfaq.orbitplanner.R
import com.ashfaq.orbitplanner.data.local.DatabaseProvider
import com.ashfaq.orbitplanner.data.repository.TaskRepository
import java.util.Calendar

class PendingTaskReminderWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val repository = TaskRepository(
            DatabaseProvider.getDatabase(applicationContext).taskDao()
        )
        val pendingTaskCount = repository.getPendingTaskCountForReminder(
            todayStart = currentDayStartMillis()
        )

        if (pendingTaskCount <= 0) return Result.success()
        if (!NotificationHelper.hasPostNotificationsPermission(applicationContext)) {
            return Result.success()
        }

        NotificationHelper.createPendingTaskReminderChannel(applicationContext)
        showPendingTaskNotification()

        return Result.success()
    }

    private fun currentDayStartMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.timeInMillis
    }

    @SuppressLint("MissingPermission")
    private fun showPendingTaskNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntentFlags = PendingIntent.FLAG_UPDATE_CURRENT or
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            PENDING_TASK_NOTIFICATION_ID,
            intent,
            pendingIntentFlags
        )

        val notification = NotificationCompat.Builder(
            applicationContext,
            NotificationHelper.PENDING_TASK_CHANNEL_ID
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Orbit Planner")
            .setContentText("You have pending tasks waiting in your orbit.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("You have pending tasks waiting in your orbit.")
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        NotificationManagerCompat.from(applicationContext).notify(
            PENDING_TASK_NOTIFICATION_ID,
            notification
        )
    }

    private companion object {
        const val PENDING_TASK_NOTIFICATION_ID = 8001
    }
}
