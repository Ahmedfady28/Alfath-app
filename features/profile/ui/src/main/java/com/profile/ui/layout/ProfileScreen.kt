package com.profile.ui.layout

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.profile.ui.model.ProfileUiEvent
import com.profile.ui.model.ProfileUiState
import com.profile.ui.viewmodel.ProfileViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
) = ProfileScreen(
    modifier = modifier,
    state = viewModel.state.collectAsStateWithLifecycle().value,
    onEvent = viewModel::onEvent
)

@Composable
private fun ProfileScreen(
    modifier: Modifier = Modifier,
    state: ProfileUiState,
    onEvent: (ProfileUiEvent) -> Unit,
) {
    if (state.isLoading)
        CircularProgressIndicator()
    else
        Text(
            text = "Profile Screen",
            modifier = modifier
        )


}

