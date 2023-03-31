package com.android.compose.ui.page.intro

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.android.compose.config.Intro
import com.android.compose.config.Router

@Composable
fun IntroPage5(controller: NavController) {
  ClickableText(text = AnnotatedString("IntroPage4"), onClick = {
    controller.navigate(Router.LOGIN)
  })
}
