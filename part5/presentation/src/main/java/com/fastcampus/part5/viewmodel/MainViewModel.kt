package com.fastcampus.part5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.fastcampus.part5.delegate.BannerDelegate
import com.fastcampus.part5.delegate.CategoryDelegate
import com.fastcampus.part5.delegate.ProductDelegate
import com.fastcampus.part5.domain.model.Banner
import com.fastcampus.part5.domain.model.BannerList
import com.fastcampus.part5.domain.model.BaseModel
import com.fastcampus.part5.domain.model.Carousel
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.ModelType
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.model.Ranking
import com.fastcampus.part5.domain.usecase.CategoryUseCase
import com.fastcampus.part5.domain.usecase.MainUseCase
import com.fastcampus.part5.model.BannerListVM
import com.fastcampus.part5.model.BannerVM
import com.fastcampus.part5.model.CarouselVM
import com.fastcampus.part5.model.PresentationVM
import com.fastcampus.part5.model.ProductVM
import com.fastcampus.part5.model.RankingVM
import com.fastcampus.part5.ui.NavigationRouteName
import com.fastcampus.part5.utils.NavigationUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    mainUseCase: MainUseCase,
    categoryUseCase: CategoryUseCase
) : ViewModel(), ProductDelegate, BannerDelegate, CategoryDelegate {
    private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
    val columnCount: StateFlow<Int> = _columnCount
    val modelList = mainUseCase.getModelList().map(::convertToPresentationVM)
    val categories = categoryUseCase.getCategories()

    fun openSearchForm() {
        println("openSearchForm")
    }

    fun updateColumnCount(count: Int) {
        viewModelScope.launch {
            _columnCount.emit(count)
        }
    }

    override fun openProduct(navHostController: NavHostController, product: Product) {
        NavigationUtil.navigate(navHostController, NavigationRouteName.PRODUCT_DETAIL, product)
    }

    override fun openBanner(bannerId: String) {

    }

    override fun openCategory(navHostController: NavHostController, category: Category) {
        NavigationUtil.navigate(navHostController, NavigationRouteName.CATEGORY, category)
    }

    private fun convertToPresentationVM(list: List<BaseModel>): List<PresentationVM<out BaseModel>> {
        return list.map { model ->
            when (model) {
                is Product -> ProductVM(model, this)
                is Ranking -> RankingVM(model, this)
                is Carousel -> CarouselVM(model, this)
                is Banner -> BannerVM(model, this)
                is BannerList -> BannerListVM(model, this)
            }
        }
    }

    companion object {
        private const val DEFAULT_COLUMN_COUNT = 2
    }


}