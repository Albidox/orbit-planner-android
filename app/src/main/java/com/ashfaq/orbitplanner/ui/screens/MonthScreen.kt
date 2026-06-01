package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
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
import com.ashfaq.orbitplanner.ui.theme.OrbitSuccess
import com.ashfaq.orbitplanner.ui.theme.OrbitSurfaceCard
import com.ashfaq.orbitplanner.ui.theme.OrbitSurfaceRaised
import com.ashfaq.orbitplanner.ui.theme.OrbitTextMuted
import com.ashfaq.orbitplanner.ui.theme.OrbitTextPrimary
import com.ashfaq.orbitplanner.ui.theme.OrbitTextSecondary

data class MonthTaskPreview(
    val title: String,
    val meta: String,
    val energyLabel: String,
    val isCompleted: Boolean
)

@Composable
fun MonthScreen(
    modifier: Modifier = Modifier,
    monthLabel: String = "May 2026",
    taskPreviews: List<MonthTaskPreview> = emptyList(),
    onBottomNavSelected: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrbitDeepBackground)
    ) {
        MonthAmbientBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 18.dp)
        ) {
            MonthHeader(monthLabel = monthLabel)
            Spacer(modifier = Modifier.height(18.dp))
            MonthlyFocusSummaryCard(taskPreviews = taskPreviews)
            Spacer(modifier = Modifier.height(20.dp))
            MonthFocusGridCard()
            Spacer(modifier = Modifier.height(22.dp))
            WeeklyMissionPreviewSection(taskPreviews = taskPreviews)
            Spacer(modifier = Modifier.height(18.dp))
            YearOrbitConnectionCard()
            Spacer(modifier = Modifier.height(14.dp))
            MonthProgressEnergyCard(taskPreviews = taskPreviews)
            Spacer(modifier = Modifier.height(18.dp))
            OrbitBottomNavigation(
                selectedLabel = "Month",
                onItemSelected = onBottomNavSelected
            )
        }
    }
}

@Composable
private fun BoxScope.MonthAmbientBackground() {
    Canvas(modifier = Modifier.matchParentSize()) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitPrimaryAccent.copy(alpha = 0.16f),
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
private fun MonthHeader(
    monthLabel: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Month Focus",
            color = OrbitTextPrimary,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = monthLabel,
            color = OrbitTextSecondary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        )
    }
}

@Composable
private fun MonthlyFocusSummaryCard(
    modifier: Modifier = Modifier,
    taskPreviews: List<MonthTaskPreview> = emptyList()
) {
    val hasRoomTasks = taskPreviews.isNotEmpty()
    val totalTasks = taskPreviews.size
    val completedTasks = taskPreviews.count { it.isCompleted }
    val remainingTasks = totalTasks - completedTasks
    val completionPercent = monthCompletionPercent(taskPreviews)

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
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "MONTHLY FOCUS",
                    color = OrbitSecondaryAccent,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (hasRoomTasks) {
                        "Local month task flow"
                    } else {
                        "Portfolio foundations"
                    },
                    color = OrbitTextPrimary,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (hasRoomTasks) {
                        "$completedTasks of $totalTasks tasks complete. Keep the month calm and visible."
                    } else {
                        "Turn one Android project into a clear proof-of-work story."
                    },
                    color = OrbitTextSecondary,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    MonthChip(
                        text = if (hasRoomTasks) {
                            "$remainingTasks open"
                        } else {
                            "Year: Calm career system"
                        },
                        color = OrbitSecondaryAccent,
                        modifier = Modifier.weight(1.5f)
                    )
                    MonthChip(
                        text = if (hasRoomTasks) {
                            "$completionPercent% done"
                        } else {
                            "42% month"
                        },
                        color = OrbitPrimaryAccent,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(14.dp))
            MonthFocusProgressRing(
                progressPercent = if (hasRoomTasks) completionPercent else 42,
                label = if (hasRoomTasks) "saved" else "focus"
            )
        }
    }
}

@Composable
private fun MonthFocusProgressRing(
    modifier: Modifier = Modifier,
    progressPercent: Int = 42,
    label: String = "focus"
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(76.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 9.dp.toPx()
            drawCircle(
                color = OrbitSecondaryAccent.copy(alpha = 0.12f),
                radius = size.minDimension / 2f
            )
            drawCircle(
                color = OrbitSecondaryAccent.copy(alpha = 0.22f),
                radius = size.minDimension / 2.65f,
                style = Stroke(width = strokeWidth)
            )
            drawArc(
                color = OrbitSecondaryAccent,
                startAngle = -90f,
                sweepAngle = 360f * progressPercent / 100f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$progressPercent%",
                color = OrbitTextPrimary,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 14.sp
                )
            )
            Text(
                text = label,
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
private fun MonthFocusGridCard(modifier: Modifier = Modifier) {
    val days = buildMayPreviewDays()
    val weekLabels = listOf("M", "T", "W", "T", "F", "S", "S")

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = Color(0xFF0C1728).copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.9f)),
        shadowElevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Focus grid",
                        color = OrbitTextPrimary,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 19.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Small weekly missions across May",
                        color = OrbitTextSecondary,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 10.sp,
                            lineHeight = 13.sp
                        )
                    )
                }
                MonthChip(text = "Preview", color = OrbitTextMuted, filled = false)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                weekLabels.forEach { label ->
                    Text(
                        text = label,
                        color = OrbitTextMuted,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 9.sp,
                            lineHeight = 12.sp
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            days.chunked(7).forEach { week ->
                Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                    week.forEach { day ->
                        MonthDayCell(
                            day = day,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(7.dp))
            }
        }
    }
}

@Composable
private fun MonthDayCell(
    day: MonthDayPreview,
    modifier: Modifier = Modifier
) {
    val fillColor = when {
        day.isSelected -> OrbitPrimaryAccent.copy(alpha = 0.95f)
        day.isMissionDay -> day.color.copy(alpha = 0.28f)
        day.isMuted -> Color.Transparent
        else -> OrbitSurfaceRaised.copy(alpha = 0.74f)
    }
    val textColor = when {
        day.isSelected -> OrbitBackground
        day.isMuted -> OrbitTextMuted.copy(alpha = 0.45f)
        day.isMissionDay -> day.color
        else -> OrbitTextSecondary
    }
    val borderColor = when {
        day.isSelected -> OrbitPrimaryAccent
        day.isMissionDay -> day.color.copy(alpha = 0.68f)
        else -> OrbitOutline.copy(alpha = 0.5f)
    }

    Surface(
        modifier = modifier.size(34.dp),
        shape = RoundedCornerShape(12.dp),
        color = fillColor,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = day.label,
                color = textColor,
                maxLines = 1,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 13.sp
                )
            )
        }
    }
}

@Composable
private fun WeeklyMissionPreviewSection(
    modifier: Modifier = Modifier,
    taskPreviews: List<MonthTaskPreview> = emptyList()
) {
    val hasRoomTasks = taskPreviews.isNotEmpty()

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = if (hasRoomTasks) {
                "Monthly tasks"
            } else {
                "Weekly missions"
            },
            color = OrbitTextPrimary,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 17.sp,
                lineHeight = 21.sp
            )
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = if (hasRoomTasks) {
                "Saved local tasks planned for this month"
            } else {
                "Planning preview until monthly tasks are added"
            },
            color = OrbitTextMuted,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 10.sp,
                lineHeight = 13.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (hasRoomTasks) {
            taskPreviews.take(4).forEachIndexed { index, task ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                WeeklyMissionPreviewCard(
                    marker = "T${index + 1}",
                    title = task.title,
                    meta = if (task.isCompleted) {
                        "${task.meta} - complete"
                    } else {
                        task.meta
                    },
                    color = task.monthStatusColor()
                )
            }
        } else {
            StaticWeeklyMissionPreviewCards()
        }
    }
}

@Composable
private fun StaticWeeklyMissionPreviewCards() {
        WeeklyMissionPreviewCard(
            marker = "W1",
            title = "Collect project proof",
            meta = "Screenshots, notes, outcomes",
            color = OrbitSuccess
        )
        Spacer(modifier = Modifier.height(12.dp))
        WeeklyMissionPreviewCard(
            marker = "W2",
            title = "Draft case-study story",
            meta = "Problem, process, result",
            color = OrbitPrimaryAccent
        )
        Spacer(modifier = Modifier.height(12.dp))
        WeeklyMissionPreviewCard(
            marker = "W3",
            title = "Polish portfolio section",
            meta = "Layout, copy, final review",
            color = OrbitEnergy
        )
}

private fun MonthTaskPreview.monthStatusColor(): Color {
    if (isCompleted) return OrbitTextMuted

    return when {
        energyLabel.contains("low", ignoreCase = true) -> OrbitSuccess
        energyLabel.contains("high", ignoreCase = true) -> OrbitEnergy
        else -> OrbitPrimaryAccent
    }
}

private fun monthCompletionPercent(taskPreviews: List<MonthTaskPreview>): Int {
    if (taskPreviews.isEmpty()) return 0

    val completedTasks = taskPreviews.count { it.isCompleted }
    return completedTasks * 100 / taskPreviews.size
}

@Composable
private fun WeeklyMissionPreviewCard(
    marker: String,
    title: String,
    meta: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.94f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.78f)),
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .background(color.copy(alpha = 0.18f), CircleShape)
            ) {
                Text(
                    text = marker,
                    color = color,
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 13.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    color = OrbitTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.sp,
                        lineHeight = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = meta,
                    color = OrbitTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun YearOrbitConnectionCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitSecondaryAccent.copy(alpha = 0.42f)),
        shadowElevation = 7.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MonthOrbitIcon()
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "YEAR ORBIT CONNECTION",
                    color = OrbitSecondaryAccent,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = "2026: Become job-ready Android developer",
                    color = OrbitTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 18.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "May connects weekly proof to the bigger career orbit.",
                    color = OrbitTextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun MonthOrbitIcon() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(42.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                color = OrbitSecondaryAccent.copy(alpha = 0.18f),
                radius = size.minDimension / 2f
            )
            drawCircle(
                color = OrbitSecondaryAccent,
                radius = size.minDimension / 2.8f,
                style = Stroke(width = 2.dp.toPx())
            )
            drawCircle(
                color = OrbitPrimaryAccent,
                radius = 3.dp.toPx(),
                center = Offset(size.width * 0.72f, size.height * 0.28f)
            )
        }
    }
}

@Composable
private fun MonthProgressEnergyCard(
    modifier: Modifier = Modifier,
    taskPreviews: List<MonthTaskPreview> = emptyList()
) {
    val hasRoomTasks = taskPreviews.isNotEmpty()
    val totalTasks = taskPreviews.size
    val completedTasks = taskPreviews.count { it.isCompleted }
    val remainingTasks = totalTasks - completedTasks

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFF101B2C).copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.82f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Progress + energy",
                    color = OrbitSuccess,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = if (hasRoomTasks) {
                        "$completedTasks complete, $remainingTasks still open this month."
                    } else {
                        "Keep high-energy work near mission days, not every day."
                    },
                    color = OrbitTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 9.sp,
                        lineHeight = 12.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            MonthChip(
                text = if (hasRoomTasks) "$totalTasks tasks" else "Balanced",
                color = OrbitSuccess
            )
        }
    }
}

@Composable
private fun MonthChip(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    filled: Boolean = true
) {
    Surface(
        modifier = modifier.height(26.dp),
        shape = RoundedCornerShape(13.dp),
        color = if (filled) OrbitSurfaceRaised.copy(alpha = 0.82f) else Color.Transparent,
        border = BorderStroke(1.dp, color.copy(alpha = 0.86f))
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
                    fontWeight = FontWeight.Medium,
                    lineHeight = 12.sp
                )
            )
        }
    }
}

private fun buildMayPreviewDays(): List<MonthDayPreview> {
    val leadingDays = listOf("27", "28", "29", "30")
    val mayDays = (1..31).map { it.toString() }
    val labels = leadingDays + mayDays

    return labels.mapIndexed { index, label ->
        val mayDay = index >= leadingDays.size
        val dayNumber = label.toInt()
        val isMissionDay = mayDay && dayNumber in listOf(5, 8, 14, 21, 28)

        MonthDayPreview(
            label = label,
            color = when (dayNumber) {
                5, 8 -> OrbitSuccess
                14, 21 -> OrbitPrimaryAccent
                28 -> OrbitEnergy
                else -> OrbitTextMuted
            },
            isMissionDay = isMissionDay,
            isSelected = mayDay && dayNumber == 28,
            isMuted = !mayDay
        )
    }
}

private data class MonthDayPreview(
    val label: String,
    val color: Color,
    val isMissionDay: Boolean,
    val isSelected: Boolean,
    val isMuted: Boolean
)

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun MonthScreenPreview() {
    OrbitPlannerTheme {
        MonthScreen()
    }
}
