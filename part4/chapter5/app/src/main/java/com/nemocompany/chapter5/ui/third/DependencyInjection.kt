package com.nemocompany.chapter5.ui.third

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ReposScreen(viewModel: DependencyInjectionViewModel = viewModel()) {
    LazyColumn {
        item {
            Button(onClick = {
                viewModel.getRepos()
            }) {
                Text(text = "레포지토리 가져오기")
            }
        }
        // items = 가져온 repos의 갯수에따라 Text를 보여줌
        items(viewModel.repos) {
            Text(text = it.name)
        }
    }
}