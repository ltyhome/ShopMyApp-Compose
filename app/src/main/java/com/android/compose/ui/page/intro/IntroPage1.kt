package com.android.compose.ui.page.intro

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import com.android.compose.config.Intro

@Composable
fun IntroPage1(controller: NavHostController) {
  ClickableText(text = AnnotatedString("IntroPage1"), onClick = {
    controller.navigate(Intro.Intro2.route)
  })
}
