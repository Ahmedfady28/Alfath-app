package com.home.ui.viewmodel

sealed interface HomeUiEvent {
    object LoadData : HomeUiEvent

    data object OnClick : HomeUiEvent

    data object OnGoBack: HomeUiEvent
}