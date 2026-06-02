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

data class WeekTaskPreview(
    val title: String,
    val meta: String,
    val energyLabel: String,
    val isCompleted: Boolean
)

@Composable
fun WeekScreen(
    modifier: Modifier = Modifier,
    weekRangeLabel: String = "May 25 - May 31, 2026",
    taskPreviews: List<WeekTaskPreview> = emptyList(),
    onBottomNavSelected: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrbitDeepBackground)
    ) {
        WeekAmbientBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 18.dp)
        ) {
            WeekHeader(weekRangeLabel = weekRangeLabel)
            Spacer(modifier = Modifier.height(18.dp))
            WeeklyMissionSummaryCard()
            Spacer(modifier = Modifier.height(20.dp))
            WeekStripCard()
            Spacer(modifier = Modifier.height(22.dp))
            WeekTaskPreviewSection(taskPreviews = taskPreviews)
            Spacer(modifier = Modifier.height(18.dp))
            MonthlyFocusConnectionCard()
            Spacer(modifier = Modifier.height(14.dp))
            WeekProgressEnergyCard()
            Spacer(modifier = Modifier.height(18.dp))
            OrbitBottomNavigation(
                selectedLabel = "Week",
                onItemSelected = onBottomNavSelected
            )
        }
    }
}

@Composable
private fun BoxScope.WeekAmbientBackground() {
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
private fun WeekHeader(
    weekRangeLabel: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Week Mission",
            color = OrbitTextPrimary,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = weekRangeLabel,
            color = OrbitTextSecondary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        )
    }
}

@Composable
private fun WeeklyMissionSummaryCard(modifier: Modifier = Modifier) {
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
                    text = "WEEKLY MISSION",
                    color = OrbitPrimaryAccent.copy(alpha = 0.9f),
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Finish portfolio case study",
                    color = OrbitTextPrimary,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Use small daily blocks to turn one project into a clear story.",
                    color = OrbitTextSecondary,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 11.sp,
                        lineHeight = 15.sp
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    WeekChip(
                        text = "Monthly: Portfolio foundations",
                        color = OrbitSecondaryAccent,
                        modifier = Modifier.weight(1.55f)
                    )
                    WeekChip(
                        text = "4/7 days",
                        color = OrbitPrimaryAccent,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.width(14.dp))
            WeekMissionProgressRing()
        }
    }
}

@Composable
private fun WeekMissionProgressRing(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(76.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = 9.dp.toPx()
            drawCircle(
                color = OrbitPrimaryAccent.copy(alpha = 0.11f),
                radius = size.minDimension / 2f
            )
            drawCircle(
                color = OrbitPrimaryAccent.copy(alpha = 0.22f),
                radius = size.minDimension / 2.65f,
                style = Stroke(width = strokeWidth)
            )
            drawArc(
                color = OrbitPrimaryAccent,
                startAngle = -90f,
                sweepAngle = 205f,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "57%",
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
private fun WeekStripCard(modifier: Modifier = Modifier) {
    val days = listOf(
        WeekDayPreview("Mon", "25", OrbitSuccess, true),
        WeekDayPreview("Tue", "26", OrbitSuccess, true),
        WeekDayPreview("Wed", "27", OrbitEnergy, false),
        WeekDayPreview("Thu", "28", OrbitPrimaryAccent, true),
        WeekDayPreview("Fri", "29", OrbitSecondaryAccent, false),
        WeekDayPreview("Sat", "30", OrbitTextMuted, false),
        WeekDayPreview("Sun", "31", OrbitTextMuted, false)
    )

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
                        text = "Week rhythm",
                        color = OrbitTextPrimary,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 19.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "Plan gently across the next 7 days",
                        color = OrbitTextSecondary,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 10.sp,
                            lineHeight = 13.sp
                        )
                    )
                }
                WeekChip(text = "Preview", color = OrbitTextMuted, filled = false)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                days.forEach { day ->
                    WeekDayNode(
                        day = day,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun WeekDayNode(
    day: WeekDayPreview,
    modifier: Modifier = Modifier
) {
    val fillColor = if (day.isActive) day.color.copy(alpha = 0.92f) else OrbitSurfaceRaised
    val textColor = if (day.isActive) OrbitBackground else OrbitTextSecondary

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = day.label,
            color = OrbitTextMuted,
            maxLines = 1,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 9.sp,
                lineHeight = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(7.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(34.dp)
                .background(fillColor, CircleShape)
        ) {
            Text(
                text = day.date,
                color = textColor,
                maxLines = 1,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 14.sp
                )
            )
        }
    }
}

@Composable
private fun WeekTaskPreviewSection(
    modifier: Modifier = Modifier,
    taskPreviews: List<WeekTaskPreview> = emptyList()
) {
    val hasRoomTasks = taskPreviews.isNotEmpty()

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Mission tasks",
            color = OrbitTextPrimary,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 17.sp,
                lineHeight = 21.sp
            )
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = if (hasRoomTasks) {
                "Saved local tasks planned for this week"
            } else {
                "Planning preview until weekly tasks are added"
            },
            color = OrbitTextMuted,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 10.sp,
                lineHeight = 13.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (hasRoomTasks) {
            taskPreviews.forEachIndexed { index, task ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
                WeekTaskPreviewCard(
                    title = task.title,
                    meta = task.meta,
                    status = if (task.isCompleted) "Done" else task.energyLabel,
                    statusColor = task.weekStatusColor()
                )
            }
        } else {
            StaticWeekTaskCards()
        }
    }
}

@Composable
private fun StaticWeekTaskCards() {
        WeekTaskPreviewCard(
            title = "Draft project story",
            meta = "Today - link to monthly portfolio focus",
            status = "Normal",
            statusColor = OrbitPrimaryAccent
        )
        Spacer(modifier = Modifier.height(12.dp))
        WeekTaskPreviewCard(
            title = "Collect screenshots",
            meta = "Friday - prepare case-study visuals",
            status = "High",
            statusColor = OrbitEnergy
        )
        Spacer(modifier = Modifier.height(12.dp))
        WeekTaskPreviewCard(
            title = "Review Kotlin notes",
            meta = "Weekend - support Android goal",
            status = "Low",
            statusColor = OrbitSuccess
        )
}

private fun WeekTaskPreview.weekStatusColor(): Color {
    if (isCompleted) return OrbitTextMuted

    return when {
        energyLabel.contains("low", ignoreCase = true) -> OrbitSuccess
        energyLabel.contains("high", ignoreCase = true) -> OrbitEnergy
        else -> OrbitPrimaryAccent
    }
}

@Composable
private fun WeekTaskPreviewCard(
    title: String,
    meta: String,
    status: String,
    statusColor: Color,
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
            WeekStatusDot(color = statusColor)
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
            Spacer(modifier = Modifier.width(10.dp))
            WeekChip(text = status, color = statusColor)
        }
    }
}

@Composable
private fun WeekStatusDot(color: Color) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .background(color.copy(alpha = 0.18f), CircleShape)
    ) {
        Box(
            modifier = Modifier
                .size(9.dp)
                .background(color, CircleShape)
        )
    }
}

@Composable
private fun MonthlyFocusConnectionCard(modifier: Modifier = Modifier) {
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
            WeekMiniOrbitIcon()
            Spacer(modifier = Modifier.width(14.dp))
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
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = "Portfolio foundations",
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
                    text = "This week turns May's focus into visible proof.",
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
private fun WeekMiniOrbitIcon() {
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
private fun WeekProgressEnergyCard(modifier: Modifier = Modifier) {
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
                    text = "Plan high-energy work early, keep weekend light.",
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
            WeekChip(text = "Gentle pace", color = OrbitSuccess)
        }
    }
}

@Composable
private fun WeekChip(
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

private data class WeekDayPreview(
    val label: String,
    val date: String,
    val color: Color,
    val isActive: Boolean
)

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun WeekScreenPreview() {
    OrbitPlannerTheme {
        WeekScreen()
    }
}
