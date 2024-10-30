package com.nemocompany.chapter3.ui.second

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarExample() {
    // 지금 TopAppBar에 ArrowBack이 있을텐데 이게 있다면 예시로 메일을 읽고있다.
    // 근데 ArrowBack을 눌렀으면 메일목록 화면으로 가야함
    // 근데 Back이라는 단어는 메일을 읽기전 화면으로 가야하는데
    // ArrowUp 이라는 표현이 맞지 않나 싶다.
    TopAppBar(
        title = { Text(text = "TopAppBar") },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "업 네비게이션"
                )
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "검색"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "설정"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "계정"
                )
            }
        }
    )
}