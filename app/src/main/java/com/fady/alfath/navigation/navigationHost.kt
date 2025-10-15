package com.fady.alfath.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.navigation.api.Destination
import com.navigation.impl.NavigationEvent
import com.navigation.impl.contract.NavigationHandler
import org.koin.compose.getKoin

interface ScreenFeatureUi {
    val route: Destination
    val graphFeature: GraphFeatureUi?
    val metadata: Map<String, Any>?
    val content: @Composable (Destination) -> Unit
}

data class GraphFeatureUi(
    val route: Destination,
    val startScreen: Destination,
    val priority: Int
)


@Composable
fun AlfathNavHost(
    modifier: Modifier = Modifier,
    startDestination: Destination,
    onNavigateBack: () -> Unit,
    onNavigateTo: (Destination) -> Unit,
    navigationHandler: NavigationHandler = getKoin().get(),
    features: Set<ScreenFeatureUi> = setOf(),
) {
    LaunchedEffect(navigationHandler) {
        navigationHandler.events.collect { event ->
            when (event) {
                is NavigationEvent.NavigateTo -> onNavigateTo(event.target)
                is NavigationEvent.NavigateUp -> onNavigateBack()
            }
        }
    }



    NavDisplay(
        modifier = modifier,
        backStack = listOf(),
        onBack = onNavigateBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            val featuresGroupedWithGraphs = features.groupBy { it.graphFeature }

            featuresGroupedWithGraphs
                .filterKeys { it == null }
                .forEach {
                    entry(
                        key = it.value.first().route,
                        metadata = it.value.first().metadata ?: mapOf(),
                        content = it.value.first().content
                    )
                }

            featuresGroupedWithGraphs
                .filterKeys { it != null }
                .forEach { (graph, features) ->
                    entry(graph!!.route) {
                        features.forEach { feat ->
                            entry(
                                key = feat.route,
                                metadata = feat.metadata ?: mapOf(),
                                content = feat.content
                            )
                        }
                    }
                }
        }
    )
}