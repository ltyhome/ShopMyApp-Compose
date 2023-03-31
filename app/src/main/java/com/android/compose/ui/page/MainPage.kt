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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.compose.config.Router
import com.android.compose.config.Screen
import com.android.compose.ui.ComposeViewModel
import com.android.compose.ui.page.intro.*
import com.android.compose.ui.page.nav.CartPage
import com.android.compose.ui.page.nav.HomePage
import com.android.compose.ui.page.nav.MinePage
import com.android.compose.ui.theme.composeNavigationTineColor
import com.android.compose.ui.theme.composeNavigationTineSelectorColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage(controller: NavHostController,viewModel: ComposeViewModel) {
    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                val navBackStackEntry by controller.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                listOf(Screen.Home, Screen.Cart, Screen.Mine).forEach { screen ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            controller.navigate(screen.route) {
                                popUpTo(controller.graph.findStartDestination().id) {
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
            navController = controller,
            startDestination = Router.MAIN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomePage(controller,viewModel) }
            composable(Screen.Cart.route) { CartPage(controller,viewModel) }
            composable(Screen.Mine.route) { MinePage(controller,viewModel) }
        }
    }
}

private fun selectedColor(selected: Boolean) =
    if (selected) composeNavigationTineSelectorColor else composeNavigationTineColor