package com.core.ui.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DefaultStateHolder<TState : Any>(defaultState: TState) : MviStateHolder<TState> {
    private val _state = MutableStateFlow(defaultState)
    override val state = _state.asStateFlow()
    override fun updateState(newState: TState.() -> TState) = _state.update(newState)
}