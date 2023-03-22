package com.android.compose.config

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.compose.dev.R

object Router {
  const val SPLASH = "SplashPage"
  const val INTRO = "IntroPage"
  const val LOGIN = "LoginPage"
  const val HOME = "HomePage"
  const val CART = "CartPage"
  const val MINE = "MinePage"
}

sealed class Screen(val route: String, @StringRes val strId: Int, @DrawableRes val resId: Int) {
  object Home :
      Screen(Router.HOME, R.string.bottom_navigation_home, R.drawable.bottom_navigation_home)
  object Cart :
      Screen(Router.CART, R.string.bottom_navigation_cart, R.drawable.bottom_navigation_cart)
  object Mine :
      Screen(Router.MINE, R.string.bottom_navigation_mine, R.drawable.bottom_navigation_mine)
}
