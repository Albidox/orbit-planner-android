package com.ashfaq.orbitplanner.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE plannedDate = :plannedDate ORDER BY createdAt DESC")
    fun getTasksForDate(plannedDate: Long): Flow<List<TaskEntity>>

    @Query(
        "SELECT * FROM tasks " +
            "WHERE plannedDate BETWEEN :startDate AND :endDate " +
            "ORDER BY plannedDate ASC, createdAt DESC"
    )
    fun getTasksBetween(startDate: Long, endDate: Long): Flow<List<TaskEntity>>

    @Query(
        "SELECT * FROM tasks " +
            "WHERE isCompleted = 0 " +
            "AND plannedDate IS NOT NULL " +
            "AND plannedDate < :beforeDate " +
            "ORDER BY plannedDate ASC, createdAt DESC"
    )
    fun getRescueCandidateTasks(beforeDate: Long): Flow<List<TaskEntity>>

    @Query(
        "SELECT COUNT(*) FROM tasks " +
            "WHERE isCompleted = 0 " +
            "AND plannedDate IS NOT NULL " +
            "AND plannedDate <= :todayStart"
    )
    suspend fun getPendingTaskCountForReminder(todayStart: Long): Int

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskById(taskId: Long): TaskEntity?

    @Query("UPDATE tasks SET plannedDate = :newPlannedDate WHERE id = :taskId")
    suspend fun updateTaskPlannedDate(taskId: Long, newPlannedDate: Long)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Long)
}
