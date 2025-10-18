package com.profile.ui.navigation

import androidx.compose.runtime.Composable
import com.core.ui.GraphFeatureUi
import com.core.ui.ScreenFeatureUi
import com.navigation.api.Destination
import com.profile.ui.layout.ProfileScreen

object ProfileFeatureUi : ScreenFeatureUi {
    override val route: Destination
        get() = ProfileRoute
    override val graphFeature: GraphFeatureUi<ProfileRoute>?
        get() = null
    override val metadata: Map<String, Any>?
        get() = null
    override val content: @Composable ((Destination) -> Unit)
        get() = {
            ProfileScreen()
        }
}