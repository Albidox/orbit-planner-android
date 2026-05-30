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
import com.ashfaq.orbitplanner.ui.screens.WeekTaskPreview
import com.ashfaq.orbitplanner.ui.screens.WeekScreen
import com.ashfaq.orbitplanner.ui.screens.YearOrbitScreen
import com.ashfaq.orbitplanner.ui.theme.OrbitPlannerTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
        // Temporary Phase 5E testing seed. Remove this when real Add Task UI exists.
        taskViewModel.seedSampleTasksForTestingIfEmpty()

        setContent {
            val currentWeekRange = remember { currentWeekRangeMillis() }
            val savedTasks by taskViewModel.allTasks.collectAsState(initial = emptyList())
            val savedWeekTasks by remember(currentWeekRange) {
                taskViewModel.getTasksBetween(
                    startDate = currentWeekRange.startMillis,
                    endDate = currentWeekRange.endMillis
                )
            }.collectAsState(initial = emptyList())
            val todayTaskPreviews = savedTasks.map { task ->
                task.toTodayTaskPreview()
            }
            val weekTaskPreviews = savedWeekTasks.map { task ->
                task.toWeekTaskPreview()
            }

            OrbitPlannerTheme {
                OrbitPlannerStaticApp(
                    modifier = Modifier.fillMaxSize(),
                    todayTasks = todayTaskPreviews,
                    weekTasks = weekTaskPreviews,
                    weekRangeLabel = currentWeekRange.label,
                    onAddTask = { title, linkedMission, energyLabel ->
                        taskViewModel.addTaskFromInput(
                            title = title,
                            linkedMission = linkedMission,
                            energyLabel = energyLabel
                        )
                    },
                    onToggleTaskComplete = { taskId ->
                        taskViewModel.toggleTaskCompleted(taskId)
                    },
                    onDeleteTask = { taskId ->
                        taskViewModel.deleteTaskById(taskId)
                    }
                )
            }
        }
    }
}

@Composable
private fun OrbitPlannerStaticApp(
    modifier: Modifier = Modifier,
    todayTasks: List<TodayTaskPreview> = emptyList(),
    weekTasks: List<WeekTaskPreview> = emptyList(),
    weekRangeLabel: String = "May 25 - May 31, 2026",
    onAddTask: (title: String, linkedMission: String?, energyLabel: String) -> Unit = { _, _, _ -> },
    onToggleTaskComplete: (taskId: Long) -> Unit = {},
    onDeleteTask: (taskId: Long) -> Unit = {}
) {
    // Temporary Phase 3G tab state. Real Navigation Compose will replace this later.
    var selectedTab by remember { mutableStateOf(TAB_TODAY) }

    val onTabSelected: (String) -> Unit = { tabLabel ->
        selectedTab = tabLabel
    }

    when (selectedTab) {
        TAB_WEEK -> WeekScreen(
            modifier = modifier,
            weekRangeLabel = weekRangeLabel,
            taskPreviews = weekTasks,
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
            onAddTask = onAddTask,
            onToggleTaskComplete = onToggleTaskComplete,
            onDeleteTask = onDeleteTask,
            onBottomNavSelected = onTabSelected
        )
    }
}

private const val TAB_TODAY = "Today"
private const val TAB_WEEK = "Week"
private const val TAB_MONTH = "Month"
private const val TAB_YEAR = "Year"
private const val TAB_SETTINGS = "Settings"

private data class WeekRange(
    val startMillis: Long,
    val endMillis: Long,
    val label: String
)

private fun TaskEntity.toTodayTaskPreview(): TodayTaskPreview {
    val missionLabel = linkedMission?.takeIf { it.isNotBlank() }
        ?: orbitLevel?.takeIf { it.isNotBlank() }
        ?: "Local planner task"

    return TodayTaskPreview(
        id = id,
        title = title,
        linkedMission = missionLabel,
        energyLabel = energyLabel?.takeIf { it.isNotBlank() } ?: "Normal",
        isCompleted = isCompleted
    )
}

private fun TaskEntity.toWeekTaskPreview(): WeekTaskPreview {
    val taskTime = plannedDate ?: createdAt
    val dayLabel = SimpleDateFormat("EEE", Locale.getDefault()).format(Date(taskTime))
    val missionLabel = linkedMission?.takeIf { it.isNotBlank() }
        ?: orbitLevel?.takeIf { it.isNotBlank() }
        ?: "Local planner task"

    return WeekTaskPreview(
        title = title,
        meta = "$dayLabel - $missionLabel",
        energyLabel = energyLabel?.takeIf { it.isNotBlank() } ?: "Normal",
        isCompleted = isCompleted
    )
}

private fun currentWeekRangeMillis(): WeekRange {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    val daysFromMonday = (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7
    calendar.add(Calendar.DAY_OF_MONTH, -daysFromMonday)
    val startMillis = calendar.timeInMillis
    val startDate = calendar.time

    calendar.add(Calendar.DAY_OF_MONTH, 6)
    calendar.set(Calendar.HOUR_OF_DAY, 23)
    calendar.set(Calendar.MINUTE, 59)
    calendar.set(Calendar.SECOND, 59)
    calendar.set(Calendar.MILLISECOND, 999)
    val endMillis = calendar.timeInMillis
    val endDate = calendar.time

    val formatter = SimpleDateFormat("MMM d", Locale.getDefault())
    val yearFormatter = SimpleDateFormat("yyyy", Locale.getDefault())
    val label = "${formatter.format(startDate)} - ${formatter.format(endDate)}, ${yearFormatter.format(endDate)}"

    return WeekRange(
        startMillis = startMillis,
        endMillis = endMillis,
        label = label
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun TodayScreenActivityPreview() {
    OrbitPlannerTheme {
        OrbitPlannerStaticApp(modifier = Modifier.fillMaxSize())
    }
}
