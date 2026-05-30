package com.ashfaq.orbitplanner.data.local

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private const val DATABASE_NAME = "orbit_planner_database"

    @Volatile
    private var database: OrbitPlannerDatabase? = null

    fun getDatabase(context: Context): OrbitPlannerDatabase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }

    private fun buildDatabase(context: Context): OrbitPlannerDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            OrbitPlannerDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
