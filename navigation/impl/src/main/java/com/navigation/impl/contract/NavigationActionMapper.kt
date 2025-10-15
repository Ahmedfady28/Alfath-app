package com.navigation.impl.contract

import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import kotlin.reflect.KClass

/**
 * Maps a [com.navigation.api.NavigationAction] to its target [com.navigation.api.Destination].
 *
 * Use this in app module to map navigation actions to destinations.
 */
interface NavigationActionMapper<out A : NavigationAction<*>> {
    val actionType: KClass<out NavigationAction<*>>
    fun map(action: @UnsafeVariance A): Destination
}