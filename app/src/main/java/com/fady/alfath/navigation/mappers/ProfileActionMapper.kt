package com.fady.alfath.navigation.mappers

import com.home.ui.navigation.HomeRoute
import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.impl.contract.NavigationActionMapper
import com.profile.ui.ProfileAction
import kotlin.reflect.KClass

object ProfileActionMapper : NavigationActionMapper<ProfileAction> {
    override val actionType: KClass<out NavigationAction<*>>
        get() = ProfileAction::class

    override fun map(action: ProfileAction): Destination =
        when (action) {
            ProfileAction.ToOrders -> TODO()
            ProfileAction.ToHome -> HomeRoute
        }
}