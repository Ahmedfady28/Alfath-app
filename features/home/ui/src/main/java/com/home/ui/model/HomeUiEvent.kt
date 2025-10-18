package com.home.ui.model

sealed interface HomeUiEvent {
    data object LoadData : HomeUiEvent

    data object OnClick : HomeUiEvent

    data object OnGoBack : HomeUiEvent
}