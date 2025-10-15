package com.navigation.impl.compose

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import com.navigation.api.Destination

typealias EntryProviderInstaller = EntryProviderScope<Any>.() -> Unit
typealias EntryMetadata = Map<String, Any>

interface NavigationEntry<out T : Destination> {
    val route: T
    val metadata: EntryMetadata
    val content: @Composable EntryProviderInstaller.() -> Unit
}