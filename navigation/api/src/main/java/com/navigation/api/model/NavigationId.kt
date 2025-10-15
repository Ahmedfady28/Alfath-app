package com.navigation.api.model

import com.navigation.api.NavigationAction
import kotlin.reflect.KClass

@JvmInline
value class NavigationId(val value: KClass<out NavigationAction<*>>)