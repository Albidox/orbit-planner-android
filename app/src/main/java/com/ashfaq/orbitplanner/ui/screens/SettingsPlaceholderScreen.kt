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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
    notificationStatusTitle: String = "Permission needed",
    notificationStatusBody: String = "Orbit Planner can send one gentle daily reminder when pending tasks exist.",
    notificationStatusNeedsAttention: Boolean = true,
    dailyReminderEnabled: Boolean = true,
    onDailyReminderEnabledChange: (Boolean) -> Unit = {},
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
            SettingsIntroCard(
                notificationStatusTitle = notificationStatusTitle,
                notificationStatusBody = notificationStatusBody,
                notificationStatusNeedsAttention = notificationStatusNeedsAttention
            )
            Spacer(modifier = Modifier.height(18.dp))
            SettingsReminderToggleCard(
                isEnabled = dailyReminderEnabled,
                notificationStatusNeedsAttention = notificationStatusNeedsAttention,
                onEnabledChange = onDailyReminderEnabledChange
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsPreviewCard(
                marker = "03",
                title = "Orbit Planner",
                body = "Version: Local MVP preview.",
                accentColor = OrbitSecondaryAccent
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsPreviewCard(
                marker = "04",
                title = "Local data",
                body = "Your tasks are stored locally on this device.",
                accentColor = OrbitSuccess
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsPreviewCard(
                marker = "05",
                title = "No cloud account",
                body = "This local MVP has no login or cloud sync.",
                accentColor = OrbitEnergy
            )
            Spacer(modifier = Modifier.height(14.dp))
            SettingsReminderControlNote()
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
            text = "Notifications, app info, and local data",
            color = OrbitTextSecondary,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp
            )
        )
    }
}

@Composable
private fun SettingsIntroCard(
    notificationStatusTitle: String,
    notificationStatusBody: String,
    notificationStatusNeedsAttention: Boolean,
    modifier: Modifier = Modifier
) {
    val statusColor = if (notificationStatusNeedsAttention) OrbitEnergy else OrbitSuccess

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
            SettingsOrbitDot(accentColor = statusColor)
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "NOTIFICATION STATUS",
                    color = statusColor,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 12.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = notificationStatusTitle,
                    color = OrbitTextPrimary,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 19.sp
                    )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = notificationStatusBody,
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
private fun SettingsOrbitDot(
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(42.dp)
            .background(accentColor.copy(alpha = 0.18f), CircleShape)
    ) {
        Box(
            modifier = Modifier
                .size(18.dp)
                .background(accentColor.copy(alpha = 0.24f), CircleShape)
        )
        Box(
            modifier = Modifier
                .size(7.dp)
                .background(accentColor, CircleShape)
        )
    }
}

@Composable
private fun SettingsReminderToggleCard(
    isEnabled: Boolean,
    notificationStatusNeedsAttention: Boolean,
    onEnabledChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val body = when {
        !isEnabled -> "Daily pending-task reminder is off."
        notificationStatusNeedsAttention -> "Reminder is on, but notification permission needs attention before it can run."
        else -> "Daily pending-task reminder is on."
    }

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
                color = OrbitPrimaryAccent.copy(alpha = 0.16f),
                border = BorderStroke(1.dp, OrbitPrimaryAccent.copy(alpha = 0.7f))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(28.dp)
                        .width(38.dp)
                ) {
                    Text(
                        text = "02",
                        color = OrbitPrimaryAccent,
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
                    text = "Daily reminder",
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
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp,
                        lineHeight = 13.sp
                    )
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Switch(
                checked = isEnabled,
                onCheckedChange = onEnabledChange,
                modifier = Modifier.semantics {
                    contentDescription = if (isEnabled) {
                        "Daily reminder is on"
                    } else {
                        "Daily reminder is off"
                    }
                }
            )
        }
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
private fun SettingsReminderControlNote(modifier: Modifier = Modifier) {
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
                    text = "Reminder control",
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
                    text = "This switch only controls the daily pending-task reminder.",
                    color = OrbitTextSecondary,
                    maxLines = 2,
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
                        text = "Saved",
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
