package com.android.compose.ui.page

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
import com.android.compose.config.Screen
import com.android.compose.ui.fragment.nav.CartFragment
import com.android.compose.ui.fragment.nav.HomeFragment
import com.android.compose.ui.fragment.nav.MineFragment

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
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                  navController.navigate(screen.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    // Avoid multiple copies of the same destination when
                    // reselected the same item
                    launchSingleTop = true
                    // Restore state when reselected a previously selected item
                    restoreState = true
                  }
                },
                icon = {
                  Icon(
                      painter = painterResource(id = screen.resId),
                      contentDescription = stringResource(screen.strId))
                },
                label = { Text(text = stringResource(id = screen.strId)) })
          }
        }
      }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)) {
              composable(Screen.Home.route) { HomeFragment() }
              composable(Screen.Cart.route) { CartFragment() }
              composable(Screen.Mine.route) { MineFragment() }
            }
      }
}
