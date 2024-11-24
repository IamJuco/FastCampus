package com.fastcampus.part5.model

import com.fastcampus.part5.delegate.ProductDelegate
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.model.Ranking

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) : PresentationVM(){

    fun openRankingProduct(product: Product) {
        productDelegate.openProduct(product)
        sendRankingLog()
    }

    private fun sendRankingLog() {

    }

}
