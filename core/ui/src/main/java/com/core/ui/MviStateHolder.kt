package com.core.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface IMviStateHolder<TState : Any> {
    val state: StateFlow<TState>
    fun updateState(newState: TState.() -> TState)
}

interface IMviEventProcessor<in TEvent : Any> {
    fun onEvent(event: TEvent)
}

class DefaultStateHolder<TState : Any>(defaultState: TState) : IMviStateHolder<TState> {
    private val _state = MutableStateFlow(defaultState)
    override val state = _state.asStateFlow()
    override fun updateState(newState: TState.() -> TState) = _state.update(newState)
}