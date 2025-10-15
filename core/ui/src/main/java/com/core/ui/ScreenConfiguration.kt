package com.core.ui

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.window.core.layout.WindowSizeClass.Companion.HEIGHT_DP_EXPANDED_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.HEIGHT_DP_MEDIUM_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND


enum class ScreenConfiguration : Comparable<ScreenConfiguration> {

    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;
    companion object {
        fun getScreenConfiguration(adaptiveInfo: WindowAdaptiveInfo): ScreenConfiguration = when {
            adaptiveInfo
                .windowSizeClass
                .isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)
                .not() &&
                    adaptiveInfo
                        .windowSizeClass.isHeightAtLeastBreakpoint(HEIGHT_DP_MEDIUM_LOWER_BOUND) -> MOBILE_PORTRAIT

            adaptiveInfo.windowSizeClass
                .isWidthAtLeastBreakpoint(WIDTH_DP_EXPANDED_LOWER_BOUND) &&
                    adaptiveInfo
                        .windowSizeClass
                        .isHeightAtLeastBreakpoint(HEIGHT_DP_MEDIUM_LOWER_BOUND)
                        .not() -> MOBILE_LANDSCAPE

            adaptiveInfo.windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_EXPANDED_LOWER_BOUND) &&
                    adaptiveInfo
                        .windowSizeClass
                        .isHeightAtLeastBreakpoint(HEIGHT_DP_MEDIUM_LOWER_BOUND) -> TABLET_LANDSCAPE

            adaptiveInfo.windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND) &&
                    adaptiveInfo
                        .windowSizeClass
                        .isHeightAtLeastBreakpoint(HEIGHT_DP_EXPANDED_LOWER_BOUND) -> TABLET_PORTRAIT

            else -> DESKTOP
        }
    }
}