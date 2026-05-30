package com.ashfaq.orbitplanner.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashfaq.orbitplanner.data.local.TaskEntity
import com.ashfaq.orbitplanner.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {
    val allTasks: Flow<List<TaskEntity>> = taskRepository.getAllTasks()

    private var hasStartedSampleSeed = false

    fun getTasksForDate(plannedDate: Long): Flow<List<TaskEntity>> {
        return taskRepository.getTasksForDate(plannedDate)
    }

    fun getTasksBetween(startDate: Long, endDate: Long): Flow<List<TaskEntity>> {
        return taskRepository.getTasksBetween(startDate, endDate)
    }

    fun addTaskFromInput(
        title: String,
        linkedMission: String?,
        energyLabel: String = "Normal"
    ) {
        val cleanTitle = title.trim()
        if (cleanTitle.isBlank()) return

        val now = System.currentTimeMillis()
        val task = TaskEntity(
            title = cleanTitle,
            linkedMission = linkedMission?.trim()?.takeIf { it.isNotBlank() },
            energyLabel = energyLabel.trim().ifBlank { "Normal" },
            createdAt = now,
            plannedDate = now,
            orbitLevel = "Daily"
        )

        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

    fun seedSampleTasksForTestingIfEmpty() {
        if (hasStartedSampleSeed) return
        hasStartedSampleSeed = true

        viewModelScope.launch {
            if (taskRepository.getTaskCount() > 0) return@launch

            val now = System.currentTimeMillis()
            val sampleTasks = listOf(
                TaskEntity(
                    title = "Draft project summary",
                    linkedMission = "Mission: Portfolio foundations",
                    energyLabel = "Normal",
                    createdAt = now
                ),
                TaskEntity(
                    title = "Review Kotlin notes",
                    linkedMission = "Goal: Android career growth",
                    energyLabel = "Low",
                    createdAt = now - 1_000
                ),
                TaskEntity(
                    title = "Plan tomorrow's mission",
                    linkedMission = "Weekly: Finish portfolio",
                    energyLabel = "Normal",
                    createdAt = now - 2_000
                )
            )

            sampleTasks.forEach { task ->
                taskRepository.insertTask(task)
            }
        }
    }

    fun toggleTaskCompleted(taskId: Long) {
        viewModelScope.launch {
            val task = taskRepository.getTaskById(taskId) ?: return@launch
            taskRepository.updateTask(
                task.copy(isCompleted = !task.isCompleted)
            )
        }
    }

    fun deleteTaskById(taskId: Long) {
        viewModelScope.launch {
            taskRepository.deleteTaskById(taskId)
        }
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
