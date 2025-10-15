package com.navigation.impl.model

import com.navigation.api.NavigationAction
import com.navigation.api.model.NavigationId

sealed interface TestAction : NavigationAction<TestDestination> {
    override val id: NavigationId
        get() = NavigationId(TestAction::class)
    data object TestAction1 : TestAction
    data object TestAction2 : TestAction
}