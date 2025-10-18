package com.navigation.impl.mappers

import com.home.ui.navigation.HomeNavigationAction
import com.home.ui.navigation.HomeRoute
import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.impl.contract.NavigationActionMapper
import com.profile.ui.navigation.ProfileRoute
import kotlin.reflect.KClass

internal object HomeActionMapper : NavigationActionMapper<HomeNavigationAction> {
    override val actionType: KClass<out NavigationAction<HomeRoute>>
        get() = HomeNavigationAction::class

    override fun map(action: HomeNavigationAction): Destination =
        when (action) {
            HomeNavigationAction.ToProfile -> ProfileRoute
            HomeNavigationAction.ToCart -> TODO("not implemented yet")
            is HomeNavigationAction.ToDetails -> TODO("not implemented yet")
            HomeNavigationAction.ToSearch -> TODO("not implemented yet")
            HomeNavigationAction.ToWishlist -> TODO("not implemented yet")
        }
}