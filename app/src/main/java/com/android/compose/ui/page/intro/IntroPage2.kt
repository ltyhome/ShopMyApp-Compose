package com.android.compose.ui.page.intro

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.android.compose.config.Intro

@Composable
fun IntroPage2(controller: NavController) {
  ClickableText(text = AnnotatedString("IntroPage2"), onClick = {
    controller.navigate(Intro.Intro3.route)
  })
}
