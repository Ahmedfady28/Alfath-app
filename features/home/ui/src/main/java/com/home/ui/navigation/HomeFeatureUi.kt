package com.home.ui.navigation

import androidx.compose.runtime.Composable
import com.core.ui.GraphFeatureUi
import com.core.ui.ScreenFeatureUi
import com.home.ui.layout.HomeScreen
import com.navigation.api.Destination

object HomeFeatureUi : ScreenFeatureUi {
    override val route: Destination
        get() = HomeRoute
    override val graphFeature: GraphFeatureUi<Destination>?
        get() = null
    override val metadata: Map<String, Any>?
        get() = null
    override val content: @Composable ((Destination) -> Unit)
        get() = { HomeScreen() }
}