package com.android.compose.ui.page.intro

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun IntroPage(controller: NavController) {
  Text(text = "Hello World")
}