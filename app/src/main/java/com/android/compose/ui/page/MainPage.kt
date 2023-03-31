package com.android.compose.ui.page

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.android.compose.config.Screen
import com.android.compose.ui.ComposeViewModel
import com.android.compose.ui.theme.composeNavigationTineColor
import com.android.compose.ui.theme.composeNavigationTineSelectorColor

@Composable
fun MainPage(controller: NavController,viewModel: ComposeViewModel) {
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
}

private fun selectedColor(selected: Boolean) =
    if (selected) composeNavigationTineSelectorColor else composeNavigationTineColor