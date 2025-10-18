package com.fady.alfath.navigation
//
//import android.util.Log
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.SideEffect
//import androidx.compose.runtime.saveable.rememberSerializable
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
//import androidx.navigation3.runtime.EntryProviderScope
//import androidx.navigation3.runtime.NavBackStack
//import androidx.navigation3.runtime.NavKey
//import androidx.navigation3.runtime.entryProvider
//import androidx.navigation3.runtime.rememberNavBackStack
//import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
//import androidx.navigation3.runtime.serialization.NavBackStackSerializer
//import androidx.navigation3.runtime.serialization.NavKeySerializer
//import androidx.navigation3.ui.NavDisplay
//import androidx.savedstate.serialization.SavedStateConfiguration
//import com.core.ui.ScreenFeatureUi
//import com.home.ui.layout.HomeScreen
//import com.home.ui.navigation.HomeRoute
//import com.navigation.api.Destination
//import com.navigation.impl.NavigationEvent
//import com.navigation.impl.contract.NavigationHandler
//import com.navigation.impl.navigation3.NavigationKeyAdapter
//import com.profile.ui.layout.ProfileScreen
//import com.profile.ui.navigation.ProfileRoute
//import kotlinx.serialization.modules.SerializersModule
//import kotlinx.serialization.modules.polymorphic
//import kotlinx.serialization.modules.subclass
//import org.koin.compose.getKoin
//
//@Composable
//fun AlfathNavHost(
//    modifier: Modifier = Modifier,
//    startDestination: Destination,
//    navigationHandler: NavigationHandler = getKoin().get(),
//) {
//    val backStack = rememberDestinationBackStack(startDestination)
//
//    LaunchedEffect(navigationHandler) {
//        navigationHandler.events.collect { event ->
//            when (event) {
//                is NavigationEvent.NavigateTo -> backStack.add(DestinationNavKey(event.target))
//                is NavigationEvent.NavigateUp -> if (backStack.size > 1) backStack.removeLastOrNull()
//            }
//        }
//    }
//
//
//    NavDisplay(
//        modifier = modifier,
//        backStack = backStack,
//        onBack = {
//            if (backStack.size > 1) backStack.removeLastOrNull()
//        },
//        entryDecorators = listOf(
//            rememberSaveableStateHolderNavEntryDecorator(),
//            rememberViewModelStoreNavEntryDecorator()
//        ),
//        entryProvider = entryProvider {
//            entry(
//                key = DestinationNavKey(HomeRoute)
//            ) {
//                HomeScreen()
//            }
//
//
//            entry(
//                key = DestinationNavKey(ProfileRoute)
//            ) {
//                ProfileScreen()
//            }
//        }
//    )
//
//}
//
//
//@Composable
//fun rememberDestinationBackStack(vararg elements: Destination): NavBackStack<DestinationNavKey> {
//    val adapted = elements.map { DestinationNavKey(it) }.toTypedArray()
//    return rememberSerializable(
//        serializer = NavBackStackSerializer(elementSerializer = NavKeySerializer())
//    ) {
//        NavBackStack(*adapted)
//    }
//}
//
//data class DestinationNavKey(val route: Destination) : NavKey {
//    override fun toString(): String = route.toString()
//}
//
//
//@Composable
//fun AlfathNavHost(
//    modifier: Modifier = Modifier,
//    startDestination: Destination,
//    navigationHandler: NavigationHandler = getKoin().get(),
//    features: Set<ScreenFeatureUi> = getKoin().getAll<ScreenFeatureUi>().toSet(),
//) {
//    val backStack = rememberNavBackStack(configuration = SavedStateConfiguration {
//        // Register subtypes for open polymorphism or multiplatform use.
//        serializersModule = SerializersModule {
//
//            polymorphic(NavKey::class) {
//                subclass(NavigationKeyAdapter::class, NavigationKeyAdapter.serializer())
//            }
//
//            polymorphic(baseClass = Destination::class) {
//                subclass(serializer = HomeRoute.serializer())
//                subclass(serializer = ProfileRoute.serializer())
//            }
//        }
//    }, startDestination.toNavKey())
//
//
//    SideEffect {
//        Log.d("Navigation", "Current route: ${backStack.last()}")
//    }
//
//
//    LaunchedEffect(navigationHandler) {
//        navigationHandler.events.collect { event ->
//            when (event) {
//                is NavigationEvent.NavigateTo -> backStack.add(event.target.toNavKey())
//                is NavigationEvent.NavigateUp -> if (backStack.size > 1) backStack.removeLastOrNull()
//            }
//        }
//    }
//    NavDisplay(
//        modifier = modifier,
//        backStack = backStack,
//        onBack = {
//            if (backStack.size > 1) backStack.removeLastOrNull()
//        },
//        entryDecorators = listOf(
//            rememberSaveableStateHolderNavEntryDecorator(),
//            rememberViewModelStoreNavEntryDecorator()
//        ),
//        entryProvider = entryProvider {
//            createFeatures(features)
//        }
//    )
//}
//
//private fun EntryProviderScope<NavKey>.createFeatures(features: Set<ScreenFeatureUi>) {
//    val groupedFeatures = features
//        .sortedBy { it.graphFeature?.priority }
//        .groupBy { it.graphFeature }
//
//    val featuresNotIncludeOnAnyGraph = groupedFeatures.filterKeys { it == null }
//
//    featuresNotIncludeOnAnyGraph
//        .values
//        .flatten()
//        .forEach { feat ->
//
//            createScreen(
//                feat.route,
//                feat.metadata
//            ) { feat.content(feat.route) }
//
//        }
//
//
//    groupedFeatures.filterKeys { key -> key != null }
//        .forEach { (graph, features) ->
//            graph?.let {
//                createGraph(graph.route, features.toSet())
//            }
//        }
//}
//
//private fun Destination.toNavKey() = NavigationKeyAdapter(this)
//
//private fun EntryProviderScope<NavKey>.createScreen(
//    feat: Destination,
//    metadata: Map<String, Any>? = null,
//    content: @Composable () -> Unit,
//) = entry(
//    key = feat.toNavKey(),
//    metadata = metadata ?: emptyMap(),
//    content = { content }
//)
//
//private fun EntryProviderScope<NavKey>.createGraph(
//    route: Destination,
//    features: Set<ScreenFeatureUi>,
//) = entry(route.toNavKey()) {
//    features.forEach { feat ->
//        createScreen(feat.route, feat.metadata) {
//            feat.content(feat.route)
//        }
//    }
//}