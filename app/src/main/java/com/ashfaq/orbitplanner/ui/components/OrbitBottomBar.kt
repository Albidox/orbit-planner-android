package com.ashfaq.orbitplanner.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ashfaq.orbitplanner.ui.theme.OrbitCyan
import com.ashfaq.orbitplanner.ui.theme.OrbitSpaceNavy
import com.ashfaq.orbitplanner.ui.theme.OrbitTextMuted
import com.ashfaq.orbitplanner.ui.theme.OrbitTextPrimary

@Composable
fun OrbitBottomBar(
    selectedLabel: String = "Today"
) {
    val labels = listOf("Today", "Week", "Month", "Year", "Rescue")

    Surface(
        color = OrbitSpaceNavy,
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            labels.forEach { label ->
                val isSelected = label == selectedLabel

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(3.dp)
                            .width(if (isSelected) 28.dp else 12.dp)
                            .background(
                                color = if (isSelected) OrbitCyan else OrbitTextMuted.copy(alpha = 0.35f),
                                shape = RoundedCornerShape(50)
                            )
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = label,
                        color = if (isSelected) OrbitTextPrimary else OrbitTextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}
