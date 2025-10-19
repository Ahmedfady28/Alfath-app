package com.navigation.implementation.compose

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItem
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.home.ui.layout.HomeScreen
import com.home.ui.navigation.HomeRoute
import com.navigation.api.Destination
import com.navigation.implementation.contract.NavigationHandler
import com.profile.ui.layout.ProfileScreen
import com.profile.ui.navigation.ProfileRoute
import org.koin.compose.getKoin

@Composable
fun AppRoot(
    modifier: Modifier = Modifier
) {
    val navigationHandler = getKoin().get<NavigationHandler>()
    val navController = rememberNavController()
    NavigationHandlerEffect(
        navigationHandler = navigationHandler,
        onNavigateTo = navController::navigate,
        onNavigateBack = navController::navigateUp
    )
    AppRoot(
        modifier = modifier,
        navController = navController
    )
}

@Composable
private fun AppRoot(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Destination = HomeRoute
) {
    NavigationSuiteScaffold(
        modifier = modifier,
        navigationItems = {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination

            NavigationItems.entries.forEach { item ->
                val isSelected = currentDestination?.hasRoute(item.route::class) == true
                NavigationSuiteItem(
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.icon,
                            contentDescription = item.iconContentDescription,
                        )
                    },
                    label = { Text(item.label) },
                    selected = isSelected,
                    enabled = item.enabled,
                    onClick = { navController.navigate(item.route) },
                )
            }
        }
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable<HomeRoute> { HomeScreen() }
            composable<ProfileRoute> { ProfileScreen() }
        }
    }

}
