package com.android.compose.ui.page

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.compose.config.Intro
import com.android.compose.config.Router
import com.android.compose.config.Screen
import com.android.compose.ui.ComposeViewModel
import com.android.compose.ui.page.intro.*
import com.android.compose.ui.page.nav.CartPage
import com.android.compose.ui.page.nav.HomePage
import com.android.compose.ui.page.nav.MinePage

fun NavGraphBuilder.loginGraph(controller: NavController,viewModel: ComposeViewModel) {
    composable(Router.SPLASH) { SplashPage(controller,viewModel) }
    composable(Intro.Intro1.route) { IntroPage1(controller) }
    composable(Intro.Intro2.route) { IntroPage2(controller) }
    composable(Intro.Intro3.route) { IntroPage3(controller) }
    composable(Intro.Intro4.route) { IntroPage4(controller) }
    composable(Intro.Intro5.route) { IntroPage5(controller,viewModel) }
    composable(Router.LOGIN) { LoginPage(controller,viewModel) }
    composable(Router.MAIN) { MainPage(controller,viewModel) }
}

fun NavGraphBuilder.mainGraph(controller: NavController,viewModel: ComposeViewModel) {
    composable(Screen.Home.route) { HomePage(controller,viewModel) }
    composable(Screen.Cart.route) { CartPage(controller,viewModel) }
    composable(Screen.Mine.route) { MinePage(controller,viewModel) }
}