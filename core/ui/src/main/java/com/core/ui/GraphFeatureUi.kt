package com.core.ui

import com.navigation.api.Destination

data class GraphFeatureUi<out D : Destination>(
    val route: D,
    val startScreen: D,
    val priority: Int,
)