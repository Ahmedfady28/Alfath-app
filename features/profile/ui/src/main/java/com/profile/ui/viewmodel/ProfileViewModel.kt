package com.profile.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.core.ui.DefaultStateHolder
import com.core.ui.IMviEventProcessor
import com.core.ui.IMviStateHolder
import com.profile.ui.model.ProfileUiEvent
import com.profile.ui.model.ProfileUiState

class ProfileViewModel : ViewModel(), IMviEventProcessor<ProfileUiEvent>,
    IMviStateHolder<ProfileUiState> by DefaultStateHolder(ProfileUiState()) {


    override fun onEvent(event: ProfileUiEvent) {
        TODO("Not yet implemented")
    }
}