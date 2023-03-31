package com.android.compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.android.compose.ComposeApp
import com.android.compose.ui.theme.ComposeTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures

class ComposeActivity : ComponentActivity() {
    private val viewModel: ComposeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                val windowSize = calculateWindowSizeClass(this)
                val displayFeatures = calculateDisplayFeatures(this)
                ComposeApp ( windowSize = windowSize,
                    displayFeatures = displayFeatures,
                    viewModel = viewModel)
            }
        }
    }
}
