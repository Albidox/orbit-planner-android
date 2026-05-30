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
import com.ashfaq.orbitplanner.ui.theme.OrbitQuietDanger
import com.ashfaq.orbitplanner.ui.theme.OrbitSecondaryAccent
import com.ashfaq.orbitplanner.ui.theme.OrbitSurfaceCard
import com.ashfaq.orbitplanner.ui.theme.OrbitSurfaceRaised
import com.ashfaq.orbitplanner.ui.theme.OrbitTextPrimary
import com.ashfaq.orbitplanner.ui.theme.OrbitTextSecondary

data class RescueTaskPreview(
    val id: Long,
    val title: String,
    val linkedMission: String,
    val energyLabel: String,
    val plannedDateLabel: String
)

@Composable
fun RescueModePlaceholderScreen(
    modifier: Modifier = Modifier,
    rescueTasks: List<RescueTaskPreview> = emptyList()
) {
    RescueModeScreen(
        modifier = modifier,
        rescueTasks = rescueTasks
    )
}

@Composable
fun RescueModeScreen(
    modifier: Modifier = Modifier,
    rescueTasks: List<RescueTaskPreview> = emptyList()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrbitDeepBackground)
    ) {
        RescueAmbientBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 18.dp)
        ) {
            RescueHeader()
            Spacer(modifier = Modifier.height(18.dp))
            RescueToneCard()
            Spacer(modifier = Modifier.height(22.dp))
            if (rescueTasks.isEmpty()) {
                RescueEmptyStatePreview()
            } else {
                RescueTaskList(rescueTasks = rescueTasks)
            }
            Spacer(modifier = Modifier.height(18.dp))
            OrbitBottomNavigation(selectedLabel = "Today")
        }
    }
}

@Composable
private fun BoxScope.RescueAmbientBackground() {
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
                    OrbitSecondaryAccent.copy(alpha = 0.18f),
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
private fun RescueHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "RESCUE MODE",
            color = OrbitSecondaryAccent,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 13.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Let's rescue these tasks.",
            color = OrbitTextPrimary,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Nothing is lost. Choose what happens next.",
            color = OrbitTextSecondary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        )
    }
}

@Composable
private fun RescueToneCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.96f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.86f)),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RescuePulseIcon()
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Gentle recovery queue",
                    color = OrbitTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Move unfinished work without pressure.",
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
private fun RescueTaskList(
    rescueTasks: List<RescueTaskPreview>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        rescueTasks.forEachIndexed { index, task ->
            if (index > 0) {
                Spacer(modifier = Modifier.height(18.dp))
            }
            RescueTaskCard(
                marker = (index + 1).toString(),
                title = task.title,
                meta = "${task.plannedDateLabel} - ${task.linkedMission} - Energy: ${task.energyLabel}"
            )
        }
    }
}

@Composable
fun RescueTaskCard(
    marker: String,
    title: String,
    meta: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.86f)),
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Row(verticalAlignment = Alignment.Top) {
                RescueNumberMarker(marker = marker)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = OrbitTextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            lineHeight = 18.sp
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
            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                RescueActionChip(
                    text = "Do today",
                    color = OrbitPrimaryAccent,
                    filled = true,
                    modifier = Modifier.weight(1f)
                )
                RescueActionChip(
                    text = "Move tomorrow",
                    color = OrbitTextSecondary,
                    modifier = Modifier.weight(1f)
                )
                RescueActionChip(
                    text = "Move weekend",
                    color = OrbitTextSecondary,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                RescueActionChip(
                    text = "Convert to goal",
                    color = OrbitSecondaryAccent,
                    modifier = Modifier.weight(1.15f)
                )
                RescueActionChip(
                    text = "Delete/cancel",
                    color = OrbitQuietDanger,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun RescueActionChip(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    filled: Boolean = false
) {
    Surface(
        modifier = modifier.height(30.dp),
        shape = RoundedCornerShape(15.dp),
        color = if (filled) color.copy(alpha = 0.95f) else OrbitSurfaceRaised.copy(alpha = 0.7f),
        border = BorderStroke(1.dp, color.copy(alpha = 0.82f))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                text = text,
                color = if (filled) OrbitBackground else color,
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

@Composable
fun RescueEmptyStatePreview(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.9f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.72f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "All clear after rescue.",
                    color = OrbitTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "When the queue is empty, this space can stay quiet.",
                    color = OrbitTextSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            RescueActionChip(
                text = "No Rescue tab",
                color = OrbitSecondaryAccent,
                modifier = Modifier.width(108.dp)
            )
        }
    }
}

@Composable
private fun RescuePulseIcon() {
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
private fun RescueNumberMarker(marker: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(28.dp)
            .background(OrbitEnergy.copy(alpha = 0.2f), CircleShape)
    ) {
        Text(
            text = marker,
            color = OrbitEnergy,
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 15.sp
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun RescueModeScreenPreview() {
    OrbitPlannerTheme {
        RescueModeScreen()
    }
}
