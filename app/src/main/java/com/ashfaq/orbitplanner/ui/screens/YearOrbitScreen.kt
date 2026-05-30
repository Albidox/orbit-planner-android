package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun YearOrbitScreen(
    modifier: Modifier = Modifier,
    onBottomNavSelected: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrbitDeepBackground)
    ) {
        YearOrbitAmbientBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 18.dp)
        ) {
            YearOrbitHeader()
            Spacer(modifier = Modifier.height(18.dp))
            YearlyGoalSummaryCard()
            Spacer(modifier = Modifier.height(20.dp))
            YearOrbitVisualCard()
            Spacer(modifier = Modifier.height(18.dp))
            SelectedMonthFocusCard()
            Spacer(modifier = Modifier.height(14.dp))
            YearProgressInsightCard()
            Spacer(modifier = Modifier.height(18.dp))
            OrbitBottomNavigation(
                selectedLabel = "Year",
                onItemSelected = onBottomNavSelected
            )
        }
    }
}

@Composable
private fun BoxScope.YearOrbitAmbientBackground() {
    Canvas(modifier = Modifier.matchParentSize()) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitPrimaryAccent.copy(alpha = 0.17f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.12f, y = -size.height * 0.04f),
                radius = size.width * 0.66f
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitSecondaryAccent.copy(alpha = 0.17f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.95f, y = size.height * 0.22f),
                radius = size.width * 0.62f
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitBackground.copy(alpha = 0.5f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.44f, y = size.height * 0.9f),
                radius = size.width * 0.56f
            )
        )
    }
}

@Composable
private fun YearOrbitHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Year Orbit",
            color = OrbitTextPrimary,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "2026 Goal: Become job-ready Android developer",
            color = OrbitTextSecondary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        )
    }
}

@Composable
private fun YearlyGoalSummaryCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.95f)),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 17.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "YEARLY GOAL",
                    color = OrbitSecondaryAccent,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Build a calm career system",
                    color = OrbitTextPrimary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 19.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            YearProgressChip()
        }
    }
}

@Composable
private fun YearProgressChip(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.height(28.dp),
        shape = RoundedCornerShape(14.dp),
        color = OrbitSurfaceRaised.copy(alpha = 0.86f),
        border = BorderStroke(1.dp, OrbitPrimaryAccent.copy(alpha = 0.84f))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(horizontal = 11.dp)
        ) {
            Text(
                text = "42% year",
                color = OrbitPrimaryAccent,
                maxLines = 1,
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
private fun YearOrbitVisualCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        color = Color(0xFF0C1728).copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.92f)),
        shadowElevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            YearOrbitRing()
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "4 quarters + 12 month detail nodes",
                color = OrbitTextSecondary,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 10.sp,
                    lineHeight = 13.sp
                )
            )
        }
    }
}

@Composable
fun YearOrbitRing(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(244.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        OrbitPrimaryAccent.copy(alpha = 0.18f),
                        Color.Transparent
                    ),
                    radius = size.minDimension * 0.48f
                )
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        OrbitSecondaryAccent.copy(alpha = 0.14f),
                        Color.Transparent
                    ),
                    radius = size.minDimension * 0.34f
                )
            )
            drawCircle(
                color = OrbitOutline.copy(alpha = 0.82f),
                radius = size.minDimension * 0.31f,
                style = Stroke(width = 1.dp.toPx())
            )
            drawCircle(
                color = OrbitOutline.copy(alpha = 0.68f),
                radius = size.minDimension * 0.18f,
                style = Stroke(width = 1.dp.toPx())
            )
        }

        QuarterArcPreview(
            color = OrbitPrimaryAccent,
            startAngle = -88f,
            modifier = Modifier.matchParentSize()
        )
        QuarterArcPreview(
            color = OrbitSecondaryAccent,
            startAngle = 2f,
            modifier = Modifier.matchParentSize()
        )
        QuarterArcPreview(
            color = OrbitSuccess,
            startAngle = 92f,
            modifier = Modifier.matchParentSize()
        )
        QuarterArcPreview(
            color = OrbitEnergy,
            startAngle = 182f,
            modifier = Modifier.matchParentSize()
        )

        QuarterLabel(text = "Q1", color = OrbitPrimaryAccent, x = 139.dp, y = 31.dp)
        QuarterLabel(text = "Q2", color = OrbitSecondaryAccent, x = 209.dp, y = 114.dp)
        QuarterLabel(text = "Q3", color = OrbitSuccess, x = 139.dp, y = 202.dp)
        QuarterLabel(text = "Q4", color = OrbitEnergy, x = 26.dp, y = 114.dp)

        val monthLabels = listOf("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D")
        monthLabels.forEachIndexed { index, label ->
            MonthNode(
                label = label,
                angleDegrees = -90f + index * 30f,
                selected = index == 4
            )
        }

        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .size(96.dp),
            shape = CircleShape,
            color = OrbitSurfaceCard.copy(alpha = 0.96f),
            border = BorderStroke(1.dp, OrbitPrimaryAccent.copy(alpha = 0.45f))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "2026",
                    color = OrbitTextPrimary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 22.sp
                    )
                )
                Text(
                    text = "Life Orbit",
                    color = OrbitTextSecondary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 12.sp
                    )
                )
            }
        }
    }
}

@Composable
fun QuarterArcPreview(
    color: Color,
    startAngle: Float,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val strokeWidth = 16.dp.toPx()
        val diameter = size.minDimension * 0.74f
        val topLeft = Offset(
            x = (size.width - diameter) / 2f,
            y = (size.height - diameter) / 2f
        )

        drawArc(
            color = color.copy(alpha = 0.93f),
            startAngle = startAngle,
            sweepAngle = 74f,
            useCenter = false,
            topLeft = topLeft,
            size = Size(diameter, diameter),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
        )
    }
}

@Composable
fun MonthNode(
    label: String,
    angleDegrees: Float,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    val nodeSize = if (selected) 16.dp else 13.dp
    val nodeOffset = orbitNodeOffset(
        angleDegrees = angleDegrees,
        radius = 112.dp,
        nodeSize = nodeSize
    )
    val nodeColor = if (selected) OrbitPrimaryAccent else OrbitSurfaceRaised
    val textColor = if (selected) OrbitBackground else OrbitTextSecondary

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .offset(x = nodeOffset.x, y = nodeOffset.y)
            .size(nodeSize)
            .background(nodeColor, CircleShape)
            .border(
                width = 1.dp,
                color = if (selected) OrbitPrimaryAccent else OrbitTextMuted.copy(alpha = 0.56f),
                shape = CircleShape
            )
    ) {
        Text(
            text = label,
            color = textColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium.copy(
                fontSize = 8.5.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 10.sp
            )
        )
    }
}

@Composable
private fun QuarterLabel(
    text: String,
    color: Color,
    x: Dp,
    y: Dp,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelMedium.copy(
            fontSize = 9.sp,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 12.sp
        ),
        modifier = modifier
            .offset(x = x, y = y)
            .width(28.dp)
    )
}

@Composable
fun SelectedMonthFocusCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = OrbitSurfaceCard.copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.95f)),
        shadowElevation = 7.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "MAY FOCUS",
                    color = OrbitPrimaryAccent,
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
                    text = "Weekly Mission: Finish case-study draft",
                    color = OrbitTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = OrbitPrimaryAccent,
                border = BorderStroke(1.dp, OrbitPrimaryAccent)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(28.dp)
                        .padding(horizontal = 11.dp)
                ) {
                    Text(
                        text = "Open Today",
                        color = OrbitBackground,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun YearProgressInsightCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFF101B2C).copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.82f))
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp)
        ) {
            Text(
                text = "Progress + rescue insight",
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
                text = "Rescued tasks still count toward the bigger orbit.",
                color = OrbitTextSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 9.sp,
                    lineHeight = 12.sp
                )
            )
        }
    }
}

private data class NodeOffset(
    val x: Dp,
    val y: Dp
)

private fun orbitNodeOffset(
    angleDegrees: Float,
    radius: Dp,
    nodeSize: Dp
): NodeOffset {
    val center = 122.dp
    val radians = angleDegrees * PI / 180.0
    val x = center.value + (cos(radians) * radius.value).toFloat() - nodeSize.value / 2f
    val y = center.value + (sin(radians) * radius.value).toFloat() - nodeSize.value / 2f

    return NodeOffset(x = x.dp, y = y.dp)
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun YearOrbitScreenPreview() {
    OrbitPlannerTheme {
        YearOrbitScreen()
    }
}
