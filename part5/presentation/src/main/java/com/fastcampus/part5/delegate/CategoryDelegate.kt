package com.fastcampus.part5.delegate

import androidx.navigation.NavHostController
import com.fastcampus.part5.domain.model.Category

interface CategoryDelegate {
    fun openCategory(navHostController: NavHostController, category: Category)
}