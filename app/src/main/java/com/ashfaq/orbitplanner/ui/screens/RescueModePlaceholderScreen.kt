package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ashfaq.orbitplanner.ui.components.OrbitCard
import com.ashfaq.orbitplanner.ui.theme.OrbitViolet

@Composable
fun RescueModePlaceholderScreen(modifier: Modifier = Modifier) {
    OrbitCard(
        eyebrow = "Rescue Mode",
        title = "You have unfinished tasks. Let's rescue them.",
        body = "This gentle area will help move pending work without shame when real task data exists.",
        accentColor = OrbitViolet,
        modifier = modifier
    )
}
