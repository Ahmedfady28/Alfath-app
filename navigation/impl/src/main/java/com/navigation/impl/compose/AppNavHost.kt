package com.navigation.impl.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.home.ui.layout.HomeScreen
import com.home.ui.navigation.HomeRoute
import com.navigation.impl.NavigationCommandPublisher
import com.navigation.impl.contract.NavigationHandler
import com.navigation.impl.mappers.HomeActionMapper
import com.navigation.impl.mappers.ProfileActionMapper
import com.profile.ui.layout.ProfileScreen
import com.profile.ui.navigation.ProfileRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppRoot(
    modifier: Modifier = Modifier,
) {
    val navigationHandler = NavigationCommandPublisher(
        setOf(
            HomeActionMapper,
            ProfileActionMapper
        )
    )
    val navController = rememberNavController()
    AppRoot(
        modifier = modifier,
        navController = navController,
        navigationHandler = navigationHandler
    )
}

@Composable
private fun AppRoot(
    modifier: Modifier = Modifier,
    navigationHandler: NavigationHandler,
    navController: NavHostController,
) {
    NavigationHandlerEffect(
        navigationHandler = navigationHandler,
        onNavigateTo = navController::navigate,
        onNavigateBack = navController::navigateUp
    ) {

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = HomeRoute
        ) {
            composable<HomeRoute> {
                HomeScreen(
                    modifier = modifier,
                    viewModel = koinViewModel()
                )
            }


            composable<ProfileRoute> {
                ProfileScreen(
                    modifier = modifier,
                    viewModel = koinViewModel()
                )
            }

        }
    }
}