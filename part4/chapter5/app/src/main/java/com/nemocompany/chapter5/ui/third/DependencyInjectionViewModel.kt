package com.nemocompany.chapter5.ui.third

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemocompany.chapter5.model.Repo
import com.nemocompany.chapter5.service.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DependencyInjectionViewModel @Inject constructor(
    private val githubService: GithubService
) : ViewModel() {
    val repos = mutableStateListOf<Repo>()

    fun getRepos() {
        repos.clear()
        viewModelScope.launch {
            val result = githubService.listRepos("iamjuco")
            repos.addAll(result)
        }
    }
}