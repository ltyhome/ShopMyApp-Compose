package com.android.compose

import android.app.Application
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.android.compose.ui.ComposeViewModel
import com.android.compose.ui.page.AppNavHost
import com.android.library.utils.extension.*
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp class ComposeApplication : Application()

@Composable
fun ComposeApp(
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    viewModel: ComposeViewModel
) {
    val navigationType: AppNavigationType
    val contentType: AppContentType
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()
    val foldingDevicePosture = when {
        foldingFeature.isBookPosture() ->
            DevicePosture.BookPosture(foldingFeature.bounds)
        foldingFeature.isSeparating() ->
            DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)
        else -> DevicePosture.NormalPosture
    }
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
            contentType = AppContentType.SINGLE_PANE
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = AppNavigationType.NAVIGATION_RAIL
            contentType = if (foldingDevicePosture != DevicePosture.NormalPosture) {
                AppContentType.DUAL_PANE
            } else {
                AppContentType.SINGLE_PANE
            }
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = if (foldingDevicePosture is DevicePosture.BookPosture) {
                AppNavigationType.NAVIGATION_RAIL
            } else {
                AppNavigationType.PERMANENT_NAVIGATION_DRAWER
            }
            contentType = AppContentType.DUAL_PANE
        }
        else -> {
            navigationType = AppNavigationType.BOTTOM_NAVIGATION
            contentType = AppContentType.SINGLE_PANE
        }
    }
    val navigationContentPosition = when (windowSize.heightSizeClass) {
        WindowHeightSizeClass.Compact -> {
            AppNavigationContentPosition.TOP
        }
        WindowHeightSizeClass.Medium,
        WindowHeightSizeClass.Expanded -> {
            AppNavigationContentPosition.CENTER
        }
        else -> {
            AppNavigationContentPosition.TOP
        }
    }
    AppNavHost(viewModel)
}