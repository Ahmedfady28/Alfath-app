package com.core.ui.mvi

interface IMviEventProcessor<in TEvent : Any> {
    fun onEvent(event: TEvent)
}