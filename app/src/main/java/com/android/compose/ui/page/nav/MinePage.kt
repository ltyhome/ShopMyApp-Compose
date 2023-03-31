package com.android.compose.ui.page.nav

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.android.compose.ui.ComposeViewModel

@Composable
fun MinePage(controller: NavController, viewModel: ComposeViewModel) {
  Text(text = "Hello World")
}
