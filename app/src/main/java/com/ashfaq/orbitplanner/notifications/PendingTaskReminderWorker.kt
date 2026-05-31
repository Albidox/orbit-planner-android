package com.ashfaq.orbitplanner.notifications

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class PendingTaskReminderWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        // TODO Phase 8D: query Room for incomplete today or overdue tasks.
        // TODO Phase 8D: send one gentle notification only when pending tasks exist.
        return Result.success()
    }
}
