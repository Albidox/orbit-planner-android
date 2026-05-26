package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ashfaq.orbitplanner.ui.components.OrbitCard
import com.ashfaq.orbitplanner.ui.theme.OrbitSunrise

@Composable
fun MonthPlaceholderScreen(modifier: Modifier = Modifier) {
    OrbitCard(
        eyebrow = "Month",
        title = "Monthly focus without the pressure",
        body = "Monthly focus areas will help the app feel bigger than a normal to-do list.",
        accentColor = OrbitSunrise,
        modifier = modifier
    )
}
