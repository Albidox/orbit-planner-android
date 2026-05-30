package com.ashfaq.orbitplanner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val linkedMission: String? = null,
    val energyLabel: String? = null,
    val isCompleted: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val plannedDate: Long? = null,
    val orbitLevel: String? = null,
    val rescueState: String? = null
)
