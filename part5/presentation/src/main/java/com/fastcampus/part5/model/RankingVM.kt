package com.fastcampus.part5.model

import androidx.navigation.NavHostController
import com.fastcampus.part5.delegate.ProductDelegate
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.model.Ranking

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) :
    PresentationVM<Ranking>(model) {

    fun openRankingProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendRankingLog()
    }

    private fun sendRankingLog() {

    }

}
