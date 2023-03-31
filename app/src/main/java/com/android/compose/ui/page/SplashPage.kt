package com.android.compose.ui.page

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.android.compose.config.Intro
import com.android.compose.ui.ComposeViewModel

@Composable
fun SplashPage(controller: NavController, viewModel:ComposeViewModel) {
    ClickableText(text = AnnotatedString("SplashPage"), onClick = {
        controller.navigate(Intro.Intro1.route)
    })
}
