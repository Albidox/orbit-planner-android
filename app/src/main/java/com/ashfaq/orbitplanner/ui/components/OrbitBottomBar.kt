package com.ashfaq.orbitplanner.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashfaq.orbitplanner.ui.theme.OrbitCyan
import com.ashfaq.orbitplanner.ui.theme.OrbitOutline
import com.ashfaq.orbitplanner.ui.theme.OrbitSpaceNavy
import com.ashfaq.orbitplanner.ui.theme.OrbitTextMuted

@Composable
fun OrbitBottomBar(
    selectedLabel: String = "Today",
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit = {}
) {
    OrbitBottomNavigation(
        selectedLabel = selectedLabel,
        modifier = modifier,
        onItemSelected = onItemSelected
    )
}

@Composable
fun OrbitBottomNavigation(
    selectedLabel: String = "Today",
    modifier: Modifier = Modifier,
    onItemSelected: (String) -> Unit = {}
) {
    val labels = listOf("Today", "Week", "Month", "Year", "Settings")

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = OrbitSpaceNavy.copy(alpha = 0.94f),
        border = BorderStroke(1.dp, OrbitOutline),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            labels.forEach { label ->
                val isSelected = label == selectedLabel
                val itemColor = if (isSelected) OrbitCyan else OrbitTextMuted

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onItemSelected(label) }
                ) {
                    NavIconDot(
                        label = label,
                        color = itemColor,
                        selected = isSelected
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = label,
                        color = itemColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 9.5.sp)
                    )
                }
            }
        }
    }
}

@Composable
private fun NavIconDot(
    label: String,
    color: Color,
    selected: Boolean
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(22.dp)
            .border(
                width = 1.dp,
                color = color.copy(alpha = if (selected) 1f else 0.55f),
                shape = CircleShape
            )
    ) {
        when (label) {
            "Today" -> Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(color, CircleShape)
            )

            "Year" -> {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .border(1.dp, color.copy(alpha = 0.75f), CircleShape)
                )
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(color, CircleShape)
                )
            }

            "Settings" -> Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(color.copy(alpha = 0.75f), CircleShape)
            )

            else -> Spacer(
                modifier = Modifier
                    .size(if (selected) 9.dp else 7.dp)
                    .background(
                        color = color.copy(alpha = if (selected) 0.95f else 0.5f),
                        shape = CircleShape
                    )
            )
        }
    }
}
