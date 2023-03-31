package com.android.compose.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.compose.config.Intro
import com.android.compose.config.Router
import com.android.compose.ui.ComposeViewModel
import com.android.compose.ui.page.intro.*

@Composable
fun AppNavHost(viewModel: ComposeViewModel) {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = Router.SPLASH
    ) {
        composable(Router.SPLASH) { SplashPage(controller,viewModel) }
        composable(Intro.Intro1.route) { IntroPage1(controller) }
        composable(Intro.Intro2.route) { IntroPage2(controller) }
        composable(Intro.Intro3.route) { IntroPage3(controller) }
        composable(Intro.Intro4.route) { IntroPage4(controller) }
        composable(Intro.Intro5.route) { IntroPage5(controller,viewModel) }
        composable(Router.LOGIN) { LoginPage(controller,viewModel) }
        composable(Router.MAIN) { MainPage(controller,viewModel) }
    }
}


