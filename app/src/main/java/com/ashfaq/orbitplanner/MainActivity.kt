package com.ashfaq.orbitplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.ashfaq.orbitplanner.data.local.DatabaseProvider
import com.ashfaq.orbitplanner.data.local.TaskEntity
import com.ashfaq.orbitplanner.data.repository.TaskRepository
import com.ashfaq.orbitplanner.ui.screens.MonthScreen
import com.ashfaq.orbitplanner.ui.screens.SettingsPlaceholderScreen
import com.ashfaq.orbitplanner.ui.screens.TaskViewModel
import com.ashfaq.orbitplanner.ui.screens.TaskViewModelFactory
import com.ashfaq.orbitplanner.ui.screens.TodayTaskPreview
import com.ashfaq.orbitplanner.ui.screens.TodayScreen
import com.ashfaq.orbitplanner.ui.screens.WeekScreen
import com.ashfaq.orbitplanner.ui.screens.YearOrbitScreen
import com.ashfaq.orbitplanner.ui.theme.OrbitPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = DatabaseProvider.getDatabase(applicationContext)
        val taskRepository = TaskRepository(database.taskDao())
        val taskViewModel = ViewModelProvider(
            this,
            TaskViewModelFactory(taskRepository)
        )[TaskViewModel::class.java]

        setContent {
            val savedTasks by taskViewModel.allTasks.collectAsState(initial = emptyList())
            val todayTaskPreviews = savedTasks.map { task ->
                task.toTodayTaskPreview()
            }

            OrbitPlannerTheme {
                OrbitPlannerStaticApp(
                    modifier = Modifier.fillMaxSize(),
                    todayTasks = todayTaskPreviews
                )
            }
        }
    }
}

@Composable
private fun OrbitPlannerStaticApp(
    modifier: Modifier = Modifier,
    todayTasks: List<TodayTaskPreview> = emptyList()
) {
    // Temporary Phase 3G tab state. Real Navigation Compose will replace this later.
    var selectedTab by remember { mutableStateOf(TAB_TODAY) }

    val onTabSelected: (String) -> Unit = { tabLabel ->
        selectedTab = tabLabel
    }

    when (selectedTab) {
        TAB_WEEK -> WeekScreen(
            modifier = modifier,
            onBottomNavSelected = onTabSelected
        )

        TAB_MONTH -> MonthScreen(
            modifier = modifier,
            onBottomNavSelected = onTabSelected
        )

        TAB_YEAR -> YearOrbitScreen(
            modifier = modifier,
            onBottomNavSelected = onTabSelected
        )

        TAB_SETTINGS -> SettingsPlaceholderScreen(
            modifier = modifier,
            onBottomNavSelected = onTabSelected
        )

        else -> TodayScreen(
            modifier = modifier,
            taskPreviews = todayTasks,
            onBottomNavSelected = onTabSelected
        )
    }
}

private const val TAB_TODAY = "Today"
private const val TAB_WEEK = "Week"
private const val TAB_MONTH = "Month"
private const val TAB_YEAR = "Year"
private const val TAB_SETTINGS = "Settings"

private fun TaskEntity.toTodayTaskPreview(): TodayTaskPreview {
    val missionLabel = linkedMission?.takeIf { it.isNotBlank() }
        ?: orbitLevel?.takeIf { it.isNotBlank() }
        ?: "Local planner task"

    return TodayTaskPreview(
        title = title,
        linkedMission = missionLabel,
        energyLabel = energyLabel?.takeIf { it.isNotBlank() } ?: "Normal"
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun TodayScreenActivityPreview() {
    OrbitPlannerTheme {
        OrbitPlannerStaticApp(modifier = Modifier.fillMaxSize())
    }
}
