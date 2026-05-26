package com.ashfaq.orbitplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ashfaq.orbitplanner.ui.components.OrbitBottomBar
import com.ashfaq.orbitplanner.ui.screens.MonthPlaceholderScreen
import com.ashfaq.orbitplanner.ui.screens.RescueModePlaceholderScreen
import com.ashfaq.orbitplanner.ui.screens.TodayPlaceholderScreen
import com.ashfaq.orbitplanner.ui.screens.WeekPlaceholderScreen
import com.ashfaq.orbitplanner.ui.screens.YearOrbitPlaceholderScreen
import com.ashfaq.orbitplanner.ui.theme.OrbitDeepNavy
import com.ashfaq.orbitplanner.ui.theme.OrbitPlannerTheme
import com.ashfaq.orbitplanner.ui.theme.OrbitTextSecondary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrbitPlannerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = OrbitDeepNavy,
                    bottomBar = {
                        OrbitBottomBar()
                    }
                ) { innerPadding ->
                    OrbitHomePlaceholder(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun OrbitHomePlaceholder(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(OrbitDeepNavy)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Text(
            text = "Orbit Planner",
            style = androidx.compose.material3.MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Plan today. Rescue tomorrow. Build your year.",
            color = OrbitTextSecondary,
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(24.dp))

        TodayPlaceholderScreen(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(14.dp))
        WeekPlaceholderScreen(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(14.dp))
        MonthPlaceholderScreen(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(14.dp))
        YearOrbitPlaceholderScreen(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(14.dp))
        RescueModePlaceholderScreen(modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun OrbitHomePlaceholderPreview() {
    OrbitPlannerTheme {
        OrbitHomePlaceholder()
    }
}
