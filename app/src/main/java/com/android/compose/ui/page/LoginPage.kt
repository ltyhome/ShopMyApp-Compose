package com.android.compose.ui.page

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.android.compose.config.Router
import com.android.compose.ui.ComposeViewModel

@Composable
fun LoginPage(controller: NavController, viewModel: ComposeViewModel) {
    ClickableText(
        text = AnnotatedString("LoginPage"),
        onClick = {
            controller.navigate(Router.MAIN)
        })
}
