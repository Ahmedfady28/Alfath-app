package com.profile.ui.model

data class ProfileUiState(
    val isLoading: Boolean = false,
    val username: String? = null,
    val bio: String? = null
)
