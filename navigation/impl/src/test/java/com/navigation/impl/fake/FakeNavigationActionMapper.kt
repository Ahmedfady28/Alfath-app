package com.navigation.impl.fake

import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.impl.contract.NavigationActionMapper
import com.navigation.impl.model.TestAction
import com.navigation.impl.model.TestDestination
import kotlin.reflect.KClass

object FakeNavigationActionMapper : NavigationActionMapper<TestAction> {
    override val actionType: KClass<out NavigationAction<*>>
        get() = TestAction::class

    override fun map(action: TestAction): Destination = TestDestination.TestDestination1
}