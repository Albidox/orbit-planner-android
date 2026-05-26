package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ashfaq.orbitplanner.ui.components.OrbitCard
import com.ashfaq.orbitplanner.ui.theme.OrbitCyan

@Composable
fun TodayPlaceholderScreen(modifier: Modifier = Modifier) {
    OrbitCard(
        eyebrow = "Today",
        title = "A calm place for daily focus",
        body = "Your tasks for the day will appear here after we build the task features.",
        accentColor = OrbitCyan,
        modifier = modifier
    )
}
