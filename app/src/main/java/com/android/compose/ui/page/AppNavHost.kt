package com.android.compose.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.compose.config.Router
import com.android.compose.ui.fragment.IntroFragment
import com.android.compose.ui.fragment.LoginFragment
import com.android.compose.ui.fragment.MainFragment

@Composable
fun AppNavHost() {
  NavHost(navController = rememberNavController(), startDestination = Router.SPLASH) {
    composable(Router.INTRO) { IntroFragment() }
    composable(Router.LOGIN) { LoginFragment() }
    composable(Router.Main) { MainFragment() }
  }
}