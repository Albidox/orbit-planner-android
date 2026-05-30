package com.ashfaq.orbitplanner.ui.screens

import androidx.lifecycle.ViewModel
import com.ashfaq.orbitplanner.data.local.TaskEntity
import com.ashfaq.orbitplanner.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {
    val allTasks: Flow<List<TaskEntity>> = taskRepository.getAllTasks()

    fun getTasksForDate(plannedDate: Long): Flow<List<TaskEntity>> {
        return taskRepository.getTasksForDate(plannedDate)
    }

    suspend fun addTask(
        title: String,
        linkedMission: String? = null,
        energyLabel: String? = null,
        plannedDate: Long? = null,
        orbitLevel: String? = null,
        rescueState: String? = null
    ): Long {
        val task = TaskEntity(
            title = title,
            linkedMission = linkedMission,
            energyLabel = energyLabel,
            plannedDate = plannedDate,
            orbitLevel = orbitLevel,
            rescueState = rescueState
        )

        return taskRepository.insertTask(task)
    }

    suspend fun updateTask(task: TaskEntity) {
        taskRepository.updateTask(task)
    }

    suspend fun deleteTask(task: TaskEntity) {
        taskRepository.deleteTask(task)
    }
}
