package com.nemocompany.chapter5.ui.second

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationExample(
    modifier: Modifier = Modifier,
    navCotroller: NavHostController = rememberNavController()
) {
    // 단계 3: `NavHost`를 만듭니다.
    // `navController`, `"Home"`, `modifier`를 전달합시다.
    NavHost(navController = navCotroller, "Home", modifier = modifier) {
        composable("Home") {
            Column {
                Text(text = "Home")
                Button(onClick = {
                    navCotroller.navigate("Office") {
                        // popUpTo = 모든 스텍 제거, Home으로 이동
                        popUpTo("Home")
                        // inclusive -> Login 같은 한번 쓰는걸 제외시키기 위해 씀
//                        {
//                            inclusive = true
//                        }
                    }
                }) {
                    Text(text = "Office로 이동")
                }
                Button(onClick = {
                    navCotroller.navigate("PlayGround") {
                        popUpTo("Home")
                    }
                }) {
                    Text(text = "PlayGround로 이동")
                }
                Button(onClick = {
                    navCotroller.navigate("Argument/fastcampus") {
                        // 동일한 인스턴스 화면이 있을때 더이상 백스택이 쌓이지 않게해줌
                        launchSingleTop = true
                    }
                }) {
                    Text(text = "fastCampus 아이디 연결")
                }
            }
        }

        composable("Office") {
            Column {
                Text(text = "Office")
                Button(onClick = {
                    navCotroller.navigate("Home") {
                        popUpTo("Home")
                    }
                }) {
                    Text(text = "Home으로 이동")
                }
                Button(onClick = {
                    navCotroller.navigate("PlayGround") {
                        popUpTo("Home")
                    }
                }) {
                    Text(text = "PlayGround로 이동")
                }
            }
        }

        composable("Playground") {
            Column {
                Text(text = "Playground")
                Button(onClick = {
                    navCotroller.navigate("Home") {
                        popUpTo("Home")
                    }
                }) {
                    Text(text = "Home으로 이동")
                }
                Button(onClick = {
                    navCotroller.navigate("Office") {
                        popUpTo("Home")
                    }
                }) {
                    Text(text = "Office로 이동")
                }
            }
        }

        composable("Argument/{userId}") {
            val userId = it.arguments?.get("userId")
            Text(text = "userId: $userId")
        }
    }
}