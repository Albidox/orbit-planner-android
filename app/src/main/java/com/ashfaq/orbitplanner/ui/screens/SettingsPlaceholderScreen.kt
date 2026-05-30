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

@Composable
fun SettingsPlaceholderScreen(
    modifier: Modifier = Modifier,
    onBottomNavSelected: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(OrbitDeepBackground)
    ) {
        SettingsAmbientBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 22.dp, vertical = 18.dp)
        ) {
            SettingsHeader()
            Spacer(modifier = Modifier.height(18.dp))
            SettingsIntroCard()
            Spacer(modifier = Modifier.height(18.dp))
            SettingsPreviewCard(
                marker = "01",
                title = "Reminder preferences",
                body = "Morning planning, evening rescue, and weekly review will live here later.",
                accentColor = OrbitPrimaryAccent
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsPreviewCard(
                marker = "02",
                title = "Calm orbit theme",
                body = "Dark premium colors stay consistent across Today, Week, Month, and Year.",
                accentColor = OrbitSecondaryAccent
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsPreviewCard(
                marker = "03",
                title = "Local planner setup",
                body = "Future offline storage settings will remain simple and beginner-friendly.",
                accentColor = OrbitSuccess
            )
            Spacer(modifier = Modifier.height(14.dp))
            SettingsStaticNote()
            Spacer(modifier = Modifier.height(18.dp))
            OrbitBottomNavigation(
                selectedLabel = "Settings",
                onItemSelected = onBottomNavSelected
            )
        }
    }
}

@Composable
private fun BoxScope.SettingsAmbientBackground() {
    Canvas(modifier = Modifier.matchParentSize()) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitPrimaryAccent.copy(alpha = 0.14f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.12f, y = -size.height * 0.04f),
                radius = size.width * 0.65f
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitSecondaryAccent.copy(alpha = 0.14f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.95f, y = size.height * 0.2f),
                radius = size.width * 0.62f
            )
        )
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    OrbitBackground.copy(alpha = 0.45f),
                    Color.Transparent
                ),
                center = Offset(x = size.width * 0.44f, y = size.height * 0.9f),
                radius = size.width * 0.56f
            )
        )
    }
}

@Composable
private fun SettingsHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Settings",
            color = OrbitTextPrimary,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Static development placeholder",
            color = OrbitTextSecondary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        )
    }
}

@Composable
private fun SettingsIntroCard(modifier: Modifier = Modifier) {
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
            SettingsOrbitDot()
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "TEMPORARY SETTINGS",
                    color = OrbitEnergy,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Quiet controls will arrive later.",
                    color = OrbitTextPrimary,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 19.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "For now, this tab only proves the static bottom navigation flow.",
                    color = OrbitTextSecondary,
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
private fun SettingsOrbitDot(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(42.dp)
            .background(OrbitEnergy.copy(alpha = 0.18f), CircleShape)
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .background(OrbitEnergy.copy(alpha = 0.24f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(7.dp)
                .background(OrbitEnergy, CircleShape)
        )
    }
}

@Composable
private fun SettingsPreviewCard(
    marker: String,
    title: String,
    body: String,
    accentColor: Color,
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
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = accentColor.copy(alpha = 0.16f),
                border = BorderStroke(1.dp, accentColor.copy(alpha = 0.7f))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(28.dp)
                        .width(38.dp)
                ) {
                    Text(
                        text = marker,
                        color = accentColor,
                        maxLines = 1,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 12.sp
                        )
                    )
                }
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
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 17.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = body,
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
private fun SettingsStaticNote(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFF101B2C).copy(alpha = 0.98f),
        border = BorderStroke(1.dp, OrbitOutline.copy(alpha = 0.82f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Static only",
                    color = OrbitPrimaryAccent,
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
                    text = "No settings are saved or applied in this phase.",
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
            Surface(
                shape = RoundedCornerShape(13.dp),
                color = OrbitSurfaceRaised.copy(alpha = 0.82f),
                border = BorderStroke(1.dp, OrbitTextMuted.copy(alpha = 0.82f))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(26.dp)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = "Preview",
                        color = OrbitTextMuted,
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

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun SettingsPlaceholderScreenPreview() {
    OrbitPlannerTheme {
        SettingsPlaceholderScreen()
    }
}
