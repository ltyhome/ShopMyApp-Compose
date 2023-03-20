package com.android.compose.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.compose.config.Router
import com.android.compose.ui.fragment.intro.IntroFragment
import com.android.compose.ui.fragment.LoginFragment
import com.android.compose.ui.fragment.MainFragment
import com.android.compose.ui.fragment.SplashFragment

@Composable
fun AppNavHost() {
  NavHost(navController = rememberNavController(), startDestination = Router.SPLASH) {
    composable(Router.SPLASH) { SplashFragment() }
    composable(Router.INTRO) { IntroFragment() }
    composable(Router.LOGIN) { LoginFragment() }
    composable(Router.Main) { MainFragment() }
  }
}
