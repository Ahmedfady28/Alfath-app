package com.navigation.impl

import com.navigation.api.Navigator
import com.navigation.api.model.NavigationCommand
import com.navigation.api.model.NavigatorOptions
import com.navigation.impl.contract.NavigationHandler
import com.navigation.impl.model.TestAction
import com.navigation.impl.model.TestDestination
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class DefaultNavigatorTest {
    private val handler: NavigationHandler = mockk(relaxed = true)
    private val navigator: Navigator<TestAction> = DefaultNavigator(handler)

    @BeforeEach
    fun setUp() = clearMocks(handler)

    @Test
    @Tag("NavigateTo")
    fun `GIVEN NavigationCommand (NavigateTo) WHEN navigateTo is called THEN handler receives NavigateTo command`() =
        runTest {
            // Arrange
            val action = TestAction.TestAction1
            val optionsBuilder: (NavigatorOptions.() -> Unit)? =
                { /* e.g. launchSingleTop = true */ }
            val expectedCommand = NavigationCommand.NavigateTo(action, optionsBuilder)

            // Act
            navigator.navigateTo(action, optionsBuilder)

            // Assert
            coVerify(exactly = 1) { handler.handle(eq(expectedCommand)) }
            confirmVerified(handler)
        }

    @Test
    @Tag("NavigateTo")
    fun `navigateTo should send NavigateTo command with null builder`() = runTest {
        // Arrange
        val action = TestAction.TestAction1
        val expectedCommand = NavigationCommand.NavigateTo(action)

        // Act
        navigator.navigateTo(action)

        // Assert
        coVerify(exactly = 1) { handler.handle(expectedCommand) }
        confirmVerified(handler)
    }

    // --------------------------------------------------------------------
    // region navigateUp()
    // --------------------------------------------------------------------
    @Test
    @Tag("NavigateUp")
    fun `WHEN navigateTo is called without builder THEN handler receives NavigateTo with null`() =
        runTest {
            // Arrange
            val builder: (NavigatorOptions.() -> Unit)? = {
                copy(launchSingleTop = false)
            }
            val expectedCommand = NavigationCommand.NavigateUp(builder)
            // Act
            navigator.navigateUp(builder)

            // Assert
            coVerify(exactly = 1) { handler.handle(expectedCommand) }
            confirmVerified(handler)
        }

    // endregion

    @Test
    @Tag("NavigateUp")
    fun `WHEN navigateUp is called THEN handler receives NavigateUp command`() = runTest {
        // Arrange
        val expectedCommand = NavigationCommand.NavigateUp()
        // Act
        navigator.navigateUp()
        // Assert
        coVerify(exactly = 1) { handler.handle(expectedCommand) }
    }


    @Test
    @Tag("NavigateToDestination")
    fun `GIVEN NavigationCommand (NavigateToDestination) WHEN navigateTo is called with destination THEN handler receives NavigateToDestination command`() =
        runTest {
            val expectedCommand =
                NavigationCommand.NavigateToDestination(TestDestination.TestDestination1)

            navigator.navigateTo(TestDestination.TestDestination1)

            coVerify(exactly = 1) { handler.handle(expectedCommand) }
        }
}