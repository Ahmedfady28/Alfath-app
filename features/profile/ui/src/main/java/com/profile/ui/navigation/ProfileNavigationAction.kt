package com.profile.ui.navigation

import com.navigation.api.NavigationAction
import com.navigation.api.model.NavigationId
import com.profile.ui.navigation.ProfileRoute

sealed interface ProfileNavigationAction : NavigationAction<ProfileRoute> {

    override val id: NavigationId
        get() = NavigationId(ProfileNavigationAction::class)
    data object ToOrders : ProfileNavigationAction
    data object ToHome : ProfileNavigationAction
}