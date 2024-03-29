package com.android.compose.ui.page.nav

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.android.compose.ui.ComposeViewModel

@Composable
fun HomePage(controller: NavController, viewModel: ComposeViewModel) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  Text(text = "Hello World")
}
