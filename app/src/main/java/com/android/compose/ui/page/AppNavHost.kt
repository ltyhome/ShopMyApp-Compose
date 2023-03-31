package com.android.compose.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.android.compose.config.Router
import com.android.compose.config.Screen
import com.android.compose.ui.ComposeViewModel
import com.android.compose.ui.page.nav.CartPage
import com.android.compose.ui.page.nav.HomePage
import com.android.compose.ui.page.nav.MinePage

@Composable
fun AppNavHost(viewModel: ComposeViewModel) {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = Router.SPLASH
    ) {
        loginGraph(controller,viewModel)
        navigation(
            startDestination = Screen.Home.route,
            route  = Router.MAIN
        ) {
            mainGraph(controller,viewModel)
        }
    }
}


