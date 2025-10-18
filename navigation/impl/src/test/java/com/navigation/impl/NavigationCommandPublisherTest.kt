package com.navigation.impl

import app.cash.turbine.test
import com.navigation.api.Destination
import com.navigation.api.NavigationAction
import com.navigation.api.model.NavigationCommand
import com.navigation.api.model.NavigationId
import com.navigation.impl.contract.NavigationHandler
import com.navigation.impl.fake.FakeNavigationActionMapper
import com.navigation.impl.model.TestAction
import com.navigation.impl.model.TestDestination
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertIs

class NavigationCommandPublisherTest {
    private val publisher: NavigationHandler =
        NavigationCommandPublisher(setOf(FakeNavigationActionMapper))

    @Test
    fun `WHEN NavigateTo is handled THEN emits NavigationEvent NavigateTo`() = runTest {
        val command = NavigationCommand.NavigateTo(TestAction.TestAction1)

        // Act + Assert
        publisher.events.test {
            publisher.handle(command)

            val event = awaitItem()
            assertIs<NavigationEvent.NavigateTo>(event)
            assertEquals(TestDestination.TestDestination1, event.target)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `WHEN NavigateUp is handled THEN emits NavigationEvent NavigateUp`() = runTest {
        val command = NavigationCommand.NavigateUp()

        publisher.events.test {
            publisher.handle(command)

            val event = awaitItem()
            assertIs<NavigationEvent.NavigateUp>(event)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `WHEN no mapper found THEN throws error`() = runTest {
        val unknownAction = object : NavigationAction<Destination> {
            override val id: NavigationId
                get() = NavigationId(this::class)
        }
        val command = NavigationCommand.NavigateTo(unknownAction, null)

        assertFailsWith<IllegalStateException> {
            publisher.handle(command)
        }
    }

    @Test
    fun `WHEN NavigateToDestination handled THEN emits NavigateTo with correct destination`() =
        runTest {
            val command = NavigationCommand.NavigateToDestination(TestDestination.TestDestination1)

            publisher.events.test {
                publisher.handle(command)

                val event = awaitItem()
                assertIs<NavigationEvent.NavigateTo>(event)
                assertEquals(TestDestination.TestDestination1, event.target)
                cancelAndIgnoreRemainingEvents()
            }
        }
}