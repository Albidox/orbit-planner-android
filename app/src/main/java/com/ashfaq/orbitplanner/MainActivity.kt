package com.ashfaq.orbitplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ashfaq.orbitplanner.ui.screens.TodayScreen
import com.ashfaq.orbitplanner.ui.theme.OrbitPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrbitPlannerTheme {
                TodayScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 800)
@Composable
private fun TodayScreenActivityPreview() {
    OrbitPlannerTheme {
        TodayScreen(modifier = Modifier.fillMaxSize())
    }
}
