package com.profile.ui.model

sealed interface ProfileUiEvent{
    data object GoBack: ProfileUiEvent
}