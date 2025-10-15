package com.navigation.api

import com.navigation.api.model.NavigationId

/**
 * Represents a destination for navigation within feature in this application.
 *
 * This sealed interface defines a contract for all possible navigation directions.
 * Each implementation should correspond to a specific screen or navigation action.
 * This should be answer, what are screens or actions can this feature handle. in this application.
 *
 * @param R The type of the result that can be returned from this navigation destination.
 *          Use [Unit] if no result is expected.
 */
interface NavigationAction<R : Destination> {
    val id: NavigationId
}
