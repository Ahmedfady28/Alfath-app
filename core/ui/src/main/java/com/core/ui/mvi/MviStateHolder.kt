package com.core.ui.mvi

import kotlinx.coroutines.flow.StateFlow

interface MviStateHolder<TState : Any> {
    val state: StateFlow<TState>
    fun updateState(newState: TState.() -> TState)
}