package com.fastcampus.part5.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fastcampus.part5.ui.theme.ShoppingMallTheme
import com.fastcampus.presentation.R

sealed class MainNavigationItem(
    var route: String,
    var name: String
) {
    data object Main : MainNavigationItem("Main", "Main")
    data object Category : MainNavigationItem("Category", "Category")
    data object MyPage : MainNavigationItem("MyPage", "MyPage")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoppingMallTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            MainBottomNavigationBar(navController)
        }
    ) { innerPadding ->
        MainNavigationScreen(
            paddingValues = innerPadding,
            navController = navController
        )
    }
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        MainNavigationItem.Main,
        MainNavigationItem.Category,
        MainNavigationItem.MyPage
    )

    NavigationBar(
        containerColor = Color(0xffff0000),
        contentColor = Color(0xff00ff00)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        bottomNavigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true // manifest의 startActivity와 동일한 역할
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.ic_launcher_foreground
                        ),
                        contentDescription = item.route
                    )
                })
        }
    }
}

@Composable
fun MainNavigationScreen(paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = MainNavigationItem.Main.route
    ) {
        composable(MainNavigationItem.Main.route) {
            Text(text = "Hello Main")
        }
        composable(MainNavigationItem.Category.route) {
            Text(text = "Hello Category")
        }
        composable(MainNavigationItem.MyPage.route) {
            Text(text = "Hello MyPage")
        }
    }
}