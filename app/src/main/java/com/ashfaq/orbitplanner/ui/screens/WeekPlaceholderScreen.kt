package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ashfaq.orbitplanner.ui.components.OrbitCard
import com.ashfaq.orbitplanner.ui.theme.OrbitViolet

@Composable
fun WeekPlaceholderScreen(modifier: Modifier = Modifier) {
    OrbitCard(
        eyebrow = "Week",
        title = "Weekly missions will live here",
        body = "This area will connect daily effort with a simple weekly direction.",
        accentColor = OrbitViolet,
        modifier = modifier
    )
}
