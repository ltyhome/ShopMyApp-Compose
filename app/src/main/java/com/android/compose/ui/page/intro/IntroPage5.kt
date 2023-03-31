package com.android.compose.ui.page.intro

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import com.android.compose.config.Router
import com.android.compose.ui.ComposeViewModel

@Composable
fun IntroPage5(controller: NavHostController,viewModel: ComposeViewModel) {
  ClickableText(text = AnnotatedString("IntroPage4"), onClick = {
    controller.navigate(Router.LOGIN)
  })
}
