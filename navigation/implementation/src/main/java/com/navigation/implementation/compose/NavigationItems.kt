package com.navigation.implementation.compose

import androidx.compose.ui.graphics.vector.ImageVector
import com.navigation.api.Destination

enum class NavigationItems(
    val route: Destination,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val enabled: Boolean,
    val iconContentDescription: String? = null,
)