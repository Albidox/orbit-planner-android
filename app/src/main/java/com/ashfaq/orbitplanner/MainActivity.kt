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
import com.ashfaq.orbitplanner.ui.screens.MonthTaskPreview
import com.ashfaq.orbitplanner.ui.screens.MonthScreen
import com.ashfaq.orbitplanner.ui.screens.RescueModePlaceholderScreen
import com.ashfaq.orbitplanner.ui.screens.RescueTaskPreview
import com.ashfaq.orbitplanner.ui.screens.SettingsPlaceholderScreen
import com.ashfaq.orbitplanner.ui.screens.TaskViewModel
import com.ashfaq.orbitplanner.ui.screens.TaskViewModelFactory
import com.ashfaq.orbitplanner.ui.screens.TodayTaskPreview
import com.ashfaq.orbitplanner.ui.screens.TodayScreen
import com.ashfaq.orbitplanner.ui.screens.WeekTaskPreview
import com.ashfaq.orbitplanner.ui.screens.WeekScreen
import com.ashfaq.orbitplanner.ui.screens.YearOrbitSummary
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

        setContent {
            val currentWeekRange = remember { currentWeekRangeMillis() }
            val currentMonthRange = remember { currentMonthRangeMillis() }
            val currentYearRange = remember { currentYearRangeMillis() }
            val todayStartMillis = remember { currentDayStartMillis() }
            val tomorrowStartMillis = remember { currentTomorrowStartMillis() }
            val weekendStartMillis = remember { nextSaturdayStartMillis() }
            val savedTodayTasks by remember(todayStartMillis) {
                taskViewModel.getTasksForDate(plannedDate = todayStartMillis)
            }.collectAsState(initial = emptyList())
            val savedWeekTasks by remember(currentWeekRange) {
                taskViewModel.getTasksBetween(
                    startDate = currentWeekRange.startMillis,
                    endDate = currentWeekRange.endMillis
                )
            }.collectAsState(initial = emptyList())
            val savedMonthTasks by remember(currentMonthRange) {
                taskViewModel.getTasksBetween(
                    startDate = currentMonthRange.startMillis,
                    endDate = currentMonthRange.endMillis
                )
            }.collectAsState(initial = emptyList())
            val savedYearTasks by remember(currentYearRange) {
                taskViewModel.getTasksBetween(
                    startDate = currentYearRange.startMillis,
                    endDate = currentYearRange.endMillis
                )
            }.collectAsState(initial = emptyList())
            val savedRescueTasks by remember(todayStartMillis) {
                taskViewModel.getRescueCandidateTasks(beforeDate = todayStartMillis)
            }.collectAsState(initial = emptyList())
            val todayTaskPreviews = savedTodayTasks.map { task ->
                task.toTodayTaskPreview()
            }
            val weekTaskPreviews = savedWeekTasks.map { task ->
                task.toWeekTaskPreview()
            }
            val monthTaskPreviews = savedMonthTasks.map { task ->
                task.toMonthTaskPreview()
            }
            val yearOrbitSummary = savedYearTasks.toYearOrbitSummary(
                yearLabel = currentYearRange.label
            )
            val rescueTaskPreviews = savedRescueTasks.map { task ->
                task.toRescueTaskPreview()
            }

            OrbitPlannerTheme {
                OrbitPlannerStaticApp(
                    modifier = Modifier.fillMaxSize(),
                    todayTasks = todayTaskPreviews,
                    weekTasks = weekTaskPreviews,
                    weekRangeLabel = currentWeekRange.label,
                    monthTasks = monthTaskPreviews,
                    monthLabel = currentMonthRange.label,
                    yearOrbitSummary = yearOrbitSummary,
                    rescueTasks = rescueTaskPreviews,
                    onAddTask = { title, linkedMission, energyLabel ->
                        taskViewModel.addTaskFromInput(
                            title = title,
                            linkedMission = linkedMission,
                            energyLabel = energyLabel,
                            plannedDate = todayStartMillis
                        )
                    },
                    onToggleTaskComplete = { taskId ->
                        taskViewModel.toggleTaskCompleted(taskId)
                    },
                    onDeleteTask = { taskId ->
                        taskViewModel.deleteTaskById(taskId)
                    },
                    onRescueDoToday = { taskId ->
                        taskViewModel.moveTaskToDate(
                            taskId = taskId,
                            newPlannedDate = todayStartMillis
                        )
                    },
                    onRescueMoveTomorrow = { taskId ->
                        taskViewModel.moveTaskToDate(
                            taskId = taskId,
                            newPlannedDate = tomorrowStartMillis
                        )
                    },
                    onRescueMoveWeekend = { taskId ->
                        taskViewModel.moveTaskToDate(
                            taskId = taskId,
                            newPlannedDate = weekendStartMillis
                        )
                    },
                    onRescueDeleteTask = { taskId ->
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
    monthTasks: List<MonthTaskPreview> = emptyList(),
    monthLabel: String = "May 2026",
    yearOrbitSummary: YearOrbitSummary? = null,
    rescueTasks: List<RescueTaskPreview> = emptyList(),
    onAddTask: (title: String, linkedMission: String?, energyLabel: String) -> Unit = { _, _, _ -> },
    onToggleTaskComplete: (taskId: Long) -> Unit = {},
    onDeleteTask: (taskId: Long) -> Unit = {},
    onRescueDoToday: (taskId: Long) -> Unit = {},
    onRescueMoveTomorrow: (taskId: Long) -> Unit = {},
    onRescueMoveWeekend: (taskId: Long) -> Unit = {},
    onRescueDeleteTask: (taskId: Long) -> Unit = {}
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
            monthLabel = monthLabel,
            taskPreviews = monthTasks,
            onBottomNavSelected = onTabSelected
        )

        TAB_YEAR -> YearOrbitScreen(
            modifier = modifier,
            yearSummary = yearOrbitSummary,
            onBottomNavSelected = onTabSelected
        )

        TAB_SETTINGS -> SettingsPlaceholderScreen(
            modifier = modifier,
            onBottomNavSelected = onTabSelected
        )

        TAB_RESCUE -> RescueModePlaceholderScreen(
            modifier = modifier,
            rescueTasks = rescueTasks,
            onDoToday = onRescueDoToday,
            onMoveTomorrow = onRescueMoveTomorrow,
            onMoveWeekend = onRescueMoveWeekend,
            onDeleteTask = onRescueDeleteTask
        )

        else -> TodayScreen(
            modifier = modifier,
            taskPreviews = todayTasks,
            onAddTask = onAddTask,
            onToggleTaskComplete = onToggleTaskComplete,
            onDeleteTask = onDeleteTask,
            rescueTaskCount = rescueTasks.size,
            onOpenRescue = { selectedTab = TAB_RESCUE },
            onBottomNavSelected = onTabSelected
        )
    }
}

private const val TAB_TODAY = "Today"
private const val TAB_WEEK = "Week"
private const val TAB_MONTH = "Month"
private const val TAB_YEAR = "Year"
private const val TAB_SETTINGS = "Settings"
private const val TAB_RESCUE = "Rescue"

private data class WeekRange(
    val startMillis: Long,
    val endMillis: Long,
    val label: String
)

private data class MonthRange(
    val startMillis: Long,
    val endMillis: Long,
    val label: String
)

private data class YearRange(
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

private fun TaskEntity.toMonthTaskPreview(): MonthTaskPreview {
    val taskTime = plannedDate ?: createdAt
    val dayLabel = SimpleDateFormat("MMM d", Locale.getDefault()).format(Date(taskTime))
    val missionLabel = linkedMission?.takeIf { it.isNotBlank() }
        ?: orbitLevel?.takeIf { it.isNotBlank() }
        ?: "Local planner task"

    return MonthTaskPreview(
        title = title,
        meta = "$dayLabel - $missionLabel",
        energyLabel = energyLabel?.takeIf { it.isNotBlank() } ?: "Normal",
        isCompleted = isCompleted
    )
}

private fun TaskEntity.toRescueTaskPreview(): RescueTaskPreview {
    val plannedTime = plannedDate ?: createdAt
    val plannedDateLabel = "Planned ${SimpleDateFormat("MMM d", Locale.getDefault()).format(Date(plannedTime))}"
    val missionLabel = linkedMission?.takeIf { it.isNotBlank() }
        ?: orbitLevel?.takeIf { it.isNotBlank() }
        ?: "Local planner task"

    return RescueTaskPreview(
        id = id,
        title = title,
        linkedMission = missionLabel,
        energyLabel = energyLabel?.takeIf { it.isNotBlank() } ?: "Normal",
        plannedDateLabel = plannedDateLabel
    )
}

private fun List<TaskEntity>.toYearOrbitSummary(yearLabel: String): YearOrbitSummary? {
    if (isEmpty()) return null

    val monthLabels = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )
    val monthlyTaskCounts = MutableList(12) { 0 }

    forEach { task ->
        val taskTime = task.plannedDate ?: task.createdAt
        val taskCalendar = Calendar.getInstance()
        taskCalendar.timeInMillis = taskTime
        val monthIndex = taskCalendar.get(Calendar.MONTH)
        monthlyTaskCounts[monthIndex] = monthlyTaskCounts[monthIndex] + 1
    }

    val totalTasks = size
    val completedTasks = count { it.isCompleted }
    val remainingTasks = totalTasks - completedTasks
    val completionPercent = completedTasks * 100 / totalTasks
    val quarterTaskCounts = listOf(
        monthlyTaskCounts.take(3).sum(),
        monthlyTaskCounts.drop(3).take(3).sum(),
        monthlyTaskCounts.drop(6).take(3).sum(),
        monthlyTaskCounts.drop(9).take(3).sum()
    )
    val activeMonthIndex = monthlyTaskCounts
        .withIndex()
        .maxByOrNull { it.value }
        ?.index
        ?: 0

    return YearOrbitSummary(
        yearLabel = yearLabel,
        totalTasks = totalTasks,
        completedTasks = completedTasks,
        remainingTasks = remainingTasks,
        completionPercent = completionPercent,
        monthlyTaskCounts = monthlyTaskCounts,
        quarterTaskCounts = quarterTaskCounts,
        activeMonthLabel = monthLabels[activeMonthIndex],
        activeMonthTaskCount = monthlyTaskCounts[activeMonthIndex]
    )
}

private fun currentDayStartMillis(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}

private fun currentTomorrowStartMillis(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    calendar.add(Calendar.DAY_OF_MONTH, 1)

    return calendar.timeInMillis
}

private fun nextSaturdayStartMillis(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysUntilSaturday = (Calendar.SATURDAY - dayOfWeek + 7) % 7
    val daysToAdd = if (daysUntilSaturday == 0) 7 else daysUntilSaturday
    calendar.add(Calendar.DAY_OF_MONTH, daysToAdd)

    return calendar.timeInMillis
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

private fun currentMonthRangeMillis(): MonthRange {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    val startMillis = calendar.timeInMillis
    val monthDate = calendar.time

    calendar.add(Calendar.MONTH, 1)
    calendar.add(Calendar.MILLISECOND, -1)
    val endMillis = calendar.timeInMillis

    val label = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(monthDate)

    return MonthRange(
        startMillis = startMillis,
        endMillis = endMillis,
        label = label
    )
}

private fun currentYearRangeMillis(): YearRange {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    calendar.set(Calendar.MONTH, Calendar.JANUARY)
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    val startMillis = calendar.timeInMillis

    calendar.add(Calendar.YEAR, 1)
    calendar.add(Calendar.MILLISECOND, -1)
    val endMillis = calendar.timeInMillis

    return YearRange(
        startMillis = startMillis,
        endMillis = endMillis,
        label = year.toString()
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun TodayScreenActivityPreview() {
    OrbitPlannerTheme {
        OrbitPlannerStaticApp(modifier = Modifier.fillMaxSize())
    }
}
