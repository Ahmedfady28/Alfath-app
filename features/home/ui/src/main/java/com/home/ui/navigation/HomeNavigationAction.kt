package com.home.ui.navigation

import com.navigation.api.NavigationAction
import com.navigation.api.model.NavigationId

sealed interface HomeNavigationAction : NavigationAction<HomeRoute> {
    override val id: NavigationId
        get() = NavigationId(HomeNavigationAction::class)

    data object ToWishlist : HomeNavigationAction
    data object ToCart : HomeNavigationAction
    data object ToSearch : HomeNavigationAction
    data object ToProfile : HomeNavigationAction
    data class ToDetails(val bookId: String) : HomeNavigationAction
}