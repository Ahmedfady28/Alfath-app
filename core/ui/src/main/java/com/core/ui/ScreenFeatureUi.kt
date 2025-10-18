package com.core.ui

import androidx.compose.runtime.Composable
import com.navigation.api.Destination

interface ScreenFeatureUi {
    val route: Destination
    val graphFeature: GraphFeatureUi<Destination>?
    val metadata: Map<String, Any>?
    val content: @Composable (Destination) -> Unit
}