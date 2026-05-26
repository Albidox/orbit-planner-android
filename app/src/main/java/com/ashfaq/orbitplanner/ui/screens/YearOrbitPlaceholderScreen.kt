package com.ashfaq.orbitplanner.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ashfaq.orbitplanner.ui.components.OrbitCard
import com.ashfaq.orbitplanner.ui.theme.OrbitMint

@Composable
fun YearOrbitPlaceholderScreen(modifier: Modifier = Modifier) {
    OrbitCard(
        eyebrow = "Year Orbit",
        title = "Your bigger path will appear here",
        body = "Yearly goals will later connect with monthly focus, weekly missions, and daily tasks.",
        accentColor = OrbitMint,
        modifier = modifier
    )
}
