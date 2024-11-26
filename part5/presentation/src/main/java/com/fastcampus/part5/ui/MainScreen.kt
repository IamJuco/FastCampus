package com.fastcampus.part5.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.ui.category.CategoryScreen
import com.fastcampus.part5.ui.main.MainCategoryScreen
import com.fastcampus.part5.ui.main.MainHomeScreen
import com.fastcampus.part5.ui.product_detail.ProductDetailScreen
import com.fastcampus.part5.ui.theme.ShoppingMallTheme
import com.fastcampus.part5.viewmodel.MainViewModel
import com.google.gson.Gson

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
    val navHostController = rememberNavController()

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            Header(viewModel)
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            if (NavigationItem.MainNav.isMainRoute(currentRoute)) {
                MainBottomNavigationBar(
                    navController = navHostController,
                    currentRoute = currentRoute
                )
            }
        }
    ) { innerPadding ->
        MainNavigationScreen(
            paddingValues = innerPadding,
            navHostController = navHostController,
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
fun MainBottomNavigationBar(navController: NavHostController, currentRoute: String?) {
    val bottomNavigationItems = listOf(
        NavigationItem.MainNav.Home,
        NavigationItem.MainNav.Category,
        NavigationItem.MainNav.MyPage
    )

    NavigationBar {
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
fun MainNavigationScreen(
    viewModel: MainViewModel,
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navHostController,
        startDestination = NavigationRouteName.MAIN_HOME
    ) {
        composable(NavigationRouteName.MAIN_HOME) {
            MainHomeScreen(navHostController, viewModel)
        }
        composable(NavigationRouteName.MAIN_CATEGORY) {
            MainCategoryScreen(viewModel, navHostController)
        }
        composable(NavigationRouteName.MAIN_MY_PAGE) {
            Text(text = "Hello MyPage")
        }
        composable(
            route = NavigationRouteName.CATEGORY + "/{category}",
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) {
            val categoryString = it.arguments?.getString("category")
            val category = Gson().fromJson(categoryString, Category::class.java)
            if (category != null) {
                CategoryScreen(category = category, navHostController = navHostController)
            }
        }
        composable(
            NavigationRouteName.PRODUCT_DETAIL + "/{product}",
            arguments = listOf(navArgument("product") {
                type = NavType.StringType
            })
        ) {
            val productString = it.arguments?.getString("product")
            if (productString != null) {
                ProductDetailScreen(productString)
            }
        }
    }
}