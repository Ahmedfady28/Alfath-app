package com.home.ui.layout

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui.ScreenConfiguration
import com.home.ui.model.HomeUiEvent
import com.home.ui.model.HomeUiState
import com.home.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(modifier, state, viewModel::onEvent)
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
) {
    val layoutType = ScreenConfiguration.getScreenConfiguration(currentWindowAdaptiveInfo())

    SideEffect {
        Log.d("LayoutTypeHomeScreen", layoutType.toString())
    }

    Box(modifier) {
        if (state.isLoading)
            CircularProgressIndicator()
        else
            Column(modifier) {
                Text(
                    text = "Home Screen Loaded",
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clickable {
                            onEvent(HomeUiEvent.OnClick)
                        }
                )

                Button(onClick = {
                    onEvent(HomeUiEvent.OnClick)
                }) {
                    Text(text = "Click Me")
                }

            }

    }
}