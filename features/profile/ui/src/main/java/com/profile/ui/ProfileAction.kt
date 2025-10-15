package com.profile.ui

import com.navigation.api.NavigationAction
import com.navigation.api.model.NavigationId

sealed interface ProfileAction : NavigationAction<ProfileRoute> {

    override val id: NavigationId
        get() = NavigationId(ProfileAction::class)
    data object ToOrders : ProfileAction
    data object ToHome : ProfileAction
}