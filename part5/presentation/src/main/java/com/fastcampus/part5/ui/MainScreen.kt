package com.fastcampus.part5.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fastcampus.part5.ui.main.CategoryScreen
import com.fastcampus.part5.ui.main.HomeScreen
import com.fastcampus.part5.ui.theme.ShoppingMallTheme
import com.fastcampus.part5.viewmodel.MainViewModel

sealed class MainNavigationItem(
    var route: String,
    val icon: ImageVector,
    var name: String
) {
    data object Main : MainNavigationItem("Main", Icons.Filled.Home, "Main")
    data object Category : MainNavigationItem("Category", Icons.Filled.Star, "Category")
    data object MyPage : MainNavigationItem("MyPage", Icons.Filled.AccountBox, "MyPage")
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
    val viewModel = hiltViewModel<MainViewModel>()
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            Header(viewModel)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            MainBottomNavigationBar(navController)
        }
    ) { innerPadding ->
        MainNavigationScreen(
            paddingValues = innerPadding,
            navController = navController,
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class) // TopAppBar가 미래에 사라짐
@Composable
fun Header(viewModel: MainViewModel) {
    TopAppBar(
        title = {
            Text(
                text = "My App"
            )
        },
        actions = {
            IconButton(
                onClick = {
                    viewModel.openSearchForm()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = "SearchIcon"
                )
            }
        }
    )
}

@Composable
fun MainBottomNavigationBar(navController: NavHostController) {
    val bottomNavigationItems = listOf(
        MainNavigationItem.Main,
        MainNavigationItem.Category,
        MainNavigationItem.MyPage
    )

    NavigationBar {
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
                        imageVector = item.icon,
                        contentDescription = item.route
                    )
                })
        }
    }
}

@Composable
fun MainNavigationScreen(viewModel: MainViewModel, paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = MainNavigationItem.Main.route
    ) {
        composable(MainNavigationItem.Main.route) {
            HomeScreen(viewModel)
        }
        composable(MainNavigationItem.Category.route) {
            CategoryScreen(viewModel)
        }
        composable(MainNavigationItem.MyPage.route) {
            Text(text = "Hello MyPage")
        }
    }
}