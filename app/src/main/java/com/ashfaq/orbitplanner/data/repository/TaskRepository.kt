package com.ashfaq.orbitplanner.data.repository

import com.ashfaq.orbitplanner.data.local.TaskDao
import com.ashfaq.orbitplanner.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskDao: TaskDao
) {
    fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskDao.getAllTasks()
    }

    fun getTasksForDate(plannedDate: Long): Flow<List<TaskEntity>> {
        return taskDao.getTasksForDate(plannedDate)
    }

    fun getTasksBetween(startDate: Long, endDate: Long): Flow<List<TaskEntity>> {
        return taskDao.getTasksBetween(startDate, endDate)
    }

    fun getRescueCandidateTasks(beforeDate: Long): Flow<List<TaskEntity>> {
        return taskDao.getRescueCandidateTasks(beforeDate)
    }

    suspend fun getTaskById(taskId: Long): TaskEntity? {
        return taskDao.getTaskById(taskId)
    }

    suspend fun getPendingTaskCountForReminder(todayStart: Long): Int {
        return taskDao.getPendingTaskCountForReminder(todayStart)
    }

    suspend fun insertTask(task: TaskEntity): Long {
        return taskDao.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskDao.updateTask(task)
    }

    suspend fun updateTaskPlannedDate(taskId: Long, newPlannedDate: Long) {
        taskDao.updateTaskPlannedDate(taskId, newPlannedDate)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteTaskById(taskId: Long) {
        taskDao.deleteTaskById(taskId)
    }
}
