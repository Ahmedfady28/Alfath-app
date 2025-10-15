package com.home.ui.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui.ScreenConfiguration
import com.home.ui.viewmodel.HomeUiEvent
import com.home.ui.viewmodel.HomeUiState
import com.home.ui.viewmodel.HomeViewModel

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    screenConfiguration: ScreenConfiguration,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(modifier, state, screenConfiguration, viewModel::onEvent)
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    screenConfiguration: ScreenConfiguration,
    onEvent: (HomeUiEvent) -> Unit,
) {

    if (state.isLoading)
        CircularProgressIndicator()
    else
        Box(modifier) {
            Text(
                text = "Home Screen Loaded",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .align(Alignment.Center)
                    .clickable{
                        onEvent(HomeUiEvent.OnClick)
                    }
            )
        }

}