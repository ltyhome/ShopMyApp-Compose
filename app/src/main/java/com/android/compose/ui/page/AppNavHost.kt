package com.android.compose.ui.page

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.android.compose.config.Intro
import com.android.compose.config.Router
import com.android.compose.config.Screen
import com.android.compose.ui.page.intro.*
import com.android.compose.ui.page.nav.CartPage
import com.android.compose.ui.page.nav.HomePage
import com.android.compose.ui.page.nav.MinePage
import com.android.compose.ui.theme.composeNavigationTineColor
import com.android.compose.ui.theme.composeNavigationTineSelectorColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                listOf(Screen.Home, Screen.Cart, Screen.Mine).forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.resId),
                                contentDescription = stringResource(screen.strId),
                                tint = selectedColor(selected)
                            )
                        },
                        modifier = Modifier.fillMaxHeight(63f),
                        label = {
                            Text(
                                text = stringResource(id = screen.strId),
                                color = selectedColor(selected)
                            )
                        })
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Router.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Router.SPLASH) { SplashPage(navController) }
            composable(Intro.Intro1.route) { IntroPage1(navController) }
            composable(Intro.Intro2.route) { IntroPage2(navController) }
            composable(Intro.Intro3.route) { IntroPage3(navController) }
            composable(Intro.Intro4.route) { IntroPage4(navController) }
            composable(Intro.Intro5.route) { IntroPage5(navController) }
            composable(Router.LOGIN) { LoginPage(navController) }
            composable(Screen.Home.route) { HomePage(navController) }
            composable(Screen.Cart.route) { CartPage(navController) }
            composable(Screen.Mine.route) { MinePage(navController) }
        }
    }
}

private fun selectedColor(selected: Boolean) =
    if (selected) composeNavigationTineSelectorColor else composeNavigationTineColor
