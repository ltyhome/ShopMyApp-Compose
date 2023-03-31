package com.android.compose

import android.app.Application
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.window.layout.DisplayFeature
import com.android.compose.data.HomeUIState
import com.android.compose.ui.page.AppNavHost
import com.android.library.utils.extension.AppContentType
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp class ComposeApplication : Application()

@Composable
fun ComposeApp(
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    replyHomeUIState: HomeUIState,
    closeDetailScreen: () -> Unit = {},
    navigateToDetail: (String, AppContentType) -> Unit = { _, _ -> }
) {
    AppNavHost()
}