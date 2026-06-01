package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashfaq.orbitplanner.ui.components.OrbitBottomNavigation
import com.ashfaq.orbitplanner.ui.theme.OrbitBackground
import com.ashfaq.orbitplanner.ui.theme.OrbitDeepBackground
import com.ashfaq.orbitplanner.ui.theme.OrbitEnergy
import com.ashfaq.orbitplanner.ui.theme.OrbitOutline
import com.ashfaq.orbitplanner.ui.theme.OrbitPlannerTheme
import com.ashfaq.orbitplanner.ui.theme.OrbitPrimaryAccent
import com.ashfaq.orbitplanner.ui.theme.OrbitSecondaryAccent
import com.ashfaq.orbitplanner.ui.theme.OrbitSurfaceCard
import com.ashfaq.orbitplanner.ui.theme.OrbitSurfaceRaised
import com.ashfaq.orbitplanner.ui.theme.OrbitTextMuted
import com.ashfaq.orbitplanner.ui.theme.OrbitTextPrimary
import com.ashfaq.orbitplanner.ui.theme.OrbitTextSecondary

data class TodayTaskPreview(
    val id: Long,
    val title: String,
    val linkedMission: String,
    val energyLabel: String,
    val isCompleted: Boolean
)

@Composable
fun TodayScreen(
    modifier: Modifier = Modifier,
    taskPreviews: List<TodayTaskPreview> = emptyList(),
    onAddTask: (title: String, linkedMission: String?, energyLabel: String) -> Unit = { _, _, _ -> },
    onToggleTaskComplete: (taskId: Long) -> Unit = {},
    onDeleteTask: (taskId: Long) -> Unit = {},
    rescueTaskCount: Int = 0,
    onOpenRescue: () -> Unit = {},
    onBottomNavSelected: (String) -> Unit = {}
) {
    var isAddTaskDialogOpen by remember { mutableStateOf(false) }
    var taskTitle by remember { mutableStateOf("") }
    var linkedMission by remember { mutableStateOf("") }
    var showTitleError by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrbitDeepBackground)
    ) {
        TodayAmbientBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 18.dp)
        ) {
            TodayHeader()
            Spacer(modifier = Modifier.height(18.dp))
            OrbitChainRow()
            Spacer(modifier = Modifier.height(20.dp))
            MissionSummaryCard()
            Spacer(modifier = Modifier.height(24.dp))
            TaskListSection(
                taskPreviews = taskPreviews,
                onToggleTaskComplete = onToggleTaskComplete,
                onDeleteTask = onDeleteTask,
                onAddTaskClick = {
                    showTitleError = false
                    isAddTaskDialogOpen = true
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            if (rescueTaskCount > 0) {
                RescueEntryCard(
                    rescueTaskCount = rescueTaskCount,
                    onOpenRescue = onOpenRescue
                )
                Spacer(modifier = Modifier.height(14.dp))
            }
            AddTaskAction(
                onClick = {
                    showTitleError = false
                    isAddTaskDialogOpen = true
                }
            )
            Spacer(modifier = Modifier.height(18.dp))
            OrbitBottomNavigation(
                selectedLabel = "Today",
                onItemSelected = onBottomNavSelected
            )
        }

        if (isAddTaskDialogOpen) {
            AddTaskDialog(
                title = taskTitle,
                linkedMission = linkedMission,
                showTitleError = showTitleError,
                onTitleChange = {
                    taskTitle = it
                    if (it.isNotBlank()) {
                        showTitleError = false
                    }
                },
                onLinkedMissionChange = { linkedMission = it },
                onDismiss = {
                    isAddTaskDialogOpen = false
                    showTitleError = false
                },
                onSave = {
                    if (taskTitle.isBlank()) {
                        showTitleError = true
                    } else {
                        onAddTask(taskTitle, linkedMission, "Normal")
                        taskTitle = ""
                        linkedMission = ""
                        showTitleError = false
                        isAddTaskDialogOpen = false
                    }
                }
            )
        }
    }
}

@Composable
private fun BoxScope.TodayAmbientBackground() {
    Canvas(modifier = Modifier.matchParentSize()) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitPrimaryAccent.copy(alpha = 0.18f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.12f, y = -size.height * 0.04f),
                radius = size.width * 0.65f
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitSecondaryAccent.copy(alpha = 0.16f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.95f, y = size.height * 0.22f),
                radius = size.width * 0.62f
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitBackground.copy(alpha = 0.48f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.44f, y = size.height * 0.9f),
                radius = size.width * 0.56f
            )
        )
    }
}

@Composable
fun TodayHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Column {
            Text(
                text = "Today Orbit",
                color = OrbitTextPrimary,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Thu, May 28 - Good evening",
                color = OrbitTextSecondary,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 11.sp,
                    lineHeight = 14.sp
                )
            )
        }
        AddIconSurface(
            size = 42.dp,
            color = OrbitPrimaryAccent,
            plusColor = OrbitBackground,
            cornerRadius = 14.dp
        )
    }
}

@Composable
fun OrbitChainRow(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = OrbitDeepBackground.copy(alpha = 0.78f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.82f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Daily -> Weekly -> Monthly -> Yearly",
                color = OrbitTextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 10.sp,
                    lineHeight = 13.sp
                )
            )
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(OrbitPrimaryAccent, CircleShape)
            )
        }
    }
}

@Composable
fun MissionSummaryCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.92f)),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "TODAY MISSION",
                    color = OrbitPrimaryAccent.copy(alpha = 0.86f),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        lineHeight = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Build portfolio foundations",
                    color = OrbitTextPrimary,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Small focused work that moves this month's career goal forward.",
                    color = OrbitTextSecondary,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatusChip(
                        text = "Weekly: Finish portfolio",
                        color = OrbitPrimaryAccent,
                        modifier = Modifier.weight(1.45f)
                    )
                    StatusChip(
                        text = "May focus",
                        color = OrbitSecondaryAccent,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(14.dp))
            OrbitProgressCard()
        }
    }
}

@Composable
fun OrbitProgressCard(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(76.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                color = OrbitPrimaryAccent.copy(alpha = 0.11f),
                radius = size.minDimension / 2f
            )
            val strokeWidth = 9.dp.toPx()
            drawCircle(
                color = OrbitPrimaryAccent.copy(alpha = 0.22f),
                radius = size.minDimension / 2.65f,
                style = Stroke(width = strokeWidth)
            )
            drawArc(
                color = OrbitPrimaryAccent,
                startAngle = -90f,
                sweepAngle = 245f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "68%",
                color = OrbitTextPrimary,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 14.sp
                )
            )
            Text(
                text = "steady",
                color = OrbitTextMuted,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 9.sp,
                    lineHeight = 12.sp
                )
            )
        }
    }
}

@Composable
private fun TaskListSection(
    modifier: Modifier = Modifier,
    taskPreviews: List<TodayTaskPreview> = emptyList(),
    onToggleTaskComplete: (taskId: Long) -> Unit = {},
    onDeleteTask: (taskId: Long) -> Unit = {},
    onAddTaskClick: () -> Unit = {}
) {
    val hasSavedTasks = taskPreviews.isNotEmpty()

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Today tasks",
                    color = OrbitTextPrimary,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 17.sp,
                        lineHeight = 21.sp
                    )
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (hasSavedTasks) {
                        "Saved local tasks from Room"
                    } else {
                        "No saved tasks for today yet"
                    },
                    color = OrbitTextMuted,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
            StatusChip(
                text = "+ Add task",
                color = OrbitPrimaryAccent,
                filled = false,
                modifier = Modifier.clickable(onClick = onAddTaskClick)
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        if (hasSavedTasks) {
            taskPreviews.forEachIndexed { index, task ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                TaskPreviewCard(
                    title = task.title,
                    link = task.linkedMission,
                    status = task.energyLabel,
                    statusColor = task.taskStatusColor(),
                    isCompleted = task.isCompleted,
                    onToggleComplete = {
                        onToggleTaskComplete(task.id)
                    },
                    onDelete = {
                        onDeleteTask(task.id)
                    }
                )
            }
        } else {
            TodayEmptyStateCard()
        }
    }
}

@Composable
private fun TodayEmptyStateCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.9f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.72f))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Your day is clear.",
                color = OrbitTextPrimary,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 17.sp
                )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Add one small step when you are ready.",
                color = OrbitTextSecondary,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 10.sp,
                    lineHeight = 13.sp
                )
            )
        }
    }
}

private fun TodayTaskPreview.taskStatusColor(): Color {
    val label = energyLabel.orEmpty()

    return when {
        label.contains("low", ignoreCase = true) -> OrbitEnergy
        label.contains("high", ignoreCase = true) -> OrbitSecondaryAccent
        else -> OrbitPrimaryAccent
    }
}

@Composable
fun TaskPreviewCard(
    title: String,
    link: String,
    status: String,
    statusColor: Color,
    modifier: Modifier = Modifier,
    isCompleted: Boolean = false,
    onToggleComplete: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null
) {
    val displayedStatus = if (isCompleted) "Done" else status
    val displayedStatusColor = if (isCompleted) OrbitTextMuted else statusColor

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.94f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.78f)),
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onToggleComplete == null) {
                TaskStatusDot(color = statusColor)
            } else {
                Checkbox(
                    checked = isCompleted,
                    onCheckedChange = { onToggleComplete() },
                    modifier = Modifier.semantics {
                        contentDescription = if (isCompleted) {
                            "Mark $title incomplete"
                        } else {
                            "Mark $title complete"
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = if (isCompleted) OrbitTextMuted else OrbitTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.sp,
                        lineHeight = 17.sp,
                        textDecoration = if (isCompleted) {
                            TextDecoration.LineThrough
                        } else {
                            TextDecoration.None
                        }
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = link,
                    color = if (isCompleted) OrbitTextMuted else OrbitTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(horizontalAlignment = Alignment.End) {
                StatusChip(
                    text = displayedStatus,
                    color = displayedStatusColor
                )
                if (onDelete != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    TextButton(
                        onClick = onDelete,
                        modifier = Modifier.semantics {
                            contentDescription = "Delete task $title"
                        }
                    ) {
                        Text(
                            text = "Delete",
                            color = OrbitEnergy,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontSize = 9.sp,
                                lineHeight = 12.sp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TaskStatusDot(color: Color) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(22.dp)
            .background(color.copy(alpha = 0.18f), CircleShape)
    ) {
        Canvas(modifier = Modifier.size(11.dp)) {
            val strokeWidth = 2.dp.toPx()
            drawLine(
                color = color,
                start = Offset(size.width * 0.18f, size.height * 0.54f),
                end = Offset(size.width * 0.42f, size.height * 0.76f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
            drawLine(
                color = color,
                start = Offset(size.width * 0.42f, size.height * 0.76f),
                end = Offset(size.width * 0.84f, size.height * 0.26f),
                strokeWidth = strokeWidth,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun RescueEntryCard(
    modifier: Modifier = Modifier,
    rescueTaskCount: Int = 1,
    onOpenRescue: () -> Unit = {}
) {
    val taskLabel = if (rescueTaskCount == 1) {
        "1 unfinished task"
    } else {
        "$rescueTaskCount unfinished tasks"
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = "Open Rescue Mode",
                role = Role.Button,
                onClick = onOpenRescue
            ),
        shape = RoundedCornerShape(22.dp),
        color = Color(0xFF151E32).copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitSecondaryAccent.copy(alpha = 0.54f)),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RescuePulse()
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Rescue Mode available",
                    color = OrbitTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.sp,
                        lineHeight = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "$taskLabel can be moved gently.",
                    color = OrbitTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
            Text(
                text = "Open",
                color = OrbitSecondaryAccent,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 9.sp,
                    lineHeight = 12.sp
                )
            )
        }
    }
}

@Composable
private fun RescuePulse() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(34.dp)
            .background(OrbitSecondaryAccent.copy(alpha = 0.26f), CircleShape)
    ) {
        Canvas(modifier = Modifier.size(17.dp)) {
            drawArc(
                color = OrbitSecondaryAccent,
                startAngle = 35f,
                sweepAngle = 285f,
                useCenter = false,
                style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            )
            drawCircle(
                color = OrbitSecondaryAccent,
                radius = 2.dp.toPx(),
                center = Offset(size.width * 0.66f, size.height * 0.16f)
            )
        }
    }
}

@Composable
fun AddTaskAction(modifier: Modifier = Modifier) {
    AddTaskAction(
        onClick = {},
        modifier = modifier
    )
}

@Composable
fun AddTaskAction(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable(
                onClickLabel = "Add task",
                role = Role.Button,
                onClick = onClick
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Add a small task to start today's orbit.",
            color = OrbitTextMuted,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 10.sp,
                lineHeight = 13.sp
            ),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(14.dp))
        AddIconSurface(
            size = 52.dp,
            color = OrbitPrimaryAccent,
            plusColor = OrbitBackground,
            cornerRadius = 26.dp
        )
    }
}

@Composable
private fun AddTaskDialog(
    title: String,
    linkedMission: String,
    showTitleError: Boolean,
    onTitleChange: (String) -> Unit,
    onLinkedMissionChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSave: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = OrbitSurfaceCard,
        titleContentColor = OrbitTextPrimary,
        textContentColor = OrbitTextSecondary,
        title = {
            Text(text = "Add task")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = onTitleChange,
                    label = { Text(text = "Task title") },
                    isError = showTitleError,
                    singleLine = true
                )
                if (showTitleError) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Task title is required.",
                        color = OrbitEnergy,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = linkedMission,
                    onValueChange = onLinkedMissionChange,
                    label = { Text(text = "Linked mission optional") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Energy: Normal",
                    color = OrbitTextMuted,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        confirmButton = {
            Button(onClick = onSave) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}

@Composable
fun StatusChip(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    filled: Boolean = true
) {
    Surface(
        modifier = modifier.height(26.dp),
        shape = RoundedCornerShape(13.dp),
        color = if (filled) OrbitSurfaceRaised.copy(alpha = 0.8f) else Color.Transparent,
        border = BorderStroke(1.dp, color.copy(alpha = 0.9f))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = text,
                color = color,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontSize = 9.sp,
                    lineHeight = 12.sp
                )
            )
        }
    }
}

@Composable
private fun AddIconSurface(
    size: androidx.compose.ui.unit.Dp,
    color: Color,
    plusColor: Color,
    cornerRadius: androidx.compose.ui.unit.Dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(cornerRadius))
            .background(color)
    ) {
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(2.dp)
                .background(plusColor, RoundedCornerShape(1.dp))
        )
        Box(
            modifier = Modifier
                .height(2.dp)
                .width(16.dp)
                .background(plusColor, RoundedCornerShape(1.dp))
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun TodayScreenPreview() {
    OrbitPlannerTheme {
        TodayScreen()
    }
}
