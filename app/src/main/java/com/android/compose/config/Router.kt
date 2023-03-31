package com.android.compose.config

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.compose.R

object Router {
    const val SPLASH = "SplashPage"
    const val INTRO1 = "IntroPage1"
    const val INTRO2 = "IntroPage2"
    const val INTRO3 = "IntroPage3"
    const val INTRO4 = "IntroPage4"
    const val INTRO5 = "IntroPage5"
    const val LOGIN = "LoginPage"
    const val HOME = "HomePage"
    const val CART = "CartPage"
    const val MINE = "MinePage"
}

sealed class Intro(val route: String) {
    object Intro1 : Intro(Router.INTRO1)
    object Intro2 : Intro(Router.INTRO2)
    object Intro3 : Intro(Router.INTRO3)
    object Intro4 : Intro(Router.INTRO4)
    object Intro5 : Intro(Router.INTRO5)
}

sealed class Screen(val route: String, @StringRes val strId: Int, @DrawableRes val resId: Int) {
    object Home :
        Screen(Router.HOME, R.string.bottom_navigation_home, R.drawable.bottom_navigation_home)

    object Cart :
        Screen(Router.CART, R.string.bottom_navigation_cart, R.drawable.bottom_navigation_cart)

    object Mine :
        Screen(Router.MINE, R.string.bottom_navigation_mine, R.drawable.bottom_navigation_mine)
}
