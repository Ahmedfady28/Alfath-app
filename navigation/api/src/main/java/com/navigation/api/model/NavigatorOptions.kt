package com.navigation.api.model

import com.navigation.api.NavigationAction

/**
 * Represents the configuration options for a navigation action.
 *
 * This class encapsulates various parameters that can be used to control the behavior
 * of navigation, such as launch modes, animations, and whether to pop the back stack.
 *
 * @property launchSingleTop If set to true, this navigation action will be launched as single-top.
 *                           This means if the destination is already at the top of the back stack,
 *                           a new instance will not be created. Defaults to `false`.
 */
data class NavigatorOptions(
    val launchSingleTop: Boolean = false,
    val clearBackStack: Boolean = false
)