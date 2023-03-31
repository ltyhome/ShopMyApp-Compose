package com.android.compose.ui.page.nav

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.android.compose.ui.ComposeViewModel

@Composable
fun CartPage(controller: NavHostController,viewModel: ComposeViewModel) {
  Text(text = "Hello World")
}
