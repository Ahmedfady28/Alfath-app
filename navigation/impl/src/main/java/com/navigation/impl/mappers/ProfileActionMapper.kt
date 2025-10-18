package com.navigation.impl.mappers

import com.home.ui.navigation.HomeRoute
import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.impl.contract.NavigationActionMapper
import com.profile.ui.navigation.ProfileNavigationAction
import kotlin.reflect.KClass

internal object ProfileActionMapper : NavigationActionMapper<ProfileNavigationAction> {
    override val actionType: KClass<out NavigationAction<*>>
        get() = ProfileNavigationAction::class

    override fun map(action: ProfileNavigationAction): Destination =
        when (action) {
            ProfileNavigationAction.ToOrders -> TODO()
            ProfileNavigationAction.ToHome -> HomeRoute
        }
}