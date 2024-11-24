package com.fastcampus.part5.model

import com.fastcampus.part5.delegate.BannerDelegate
import com.fastcampus.part5.domain.model.BannerList

class BannerListVM(model: BannerList, private val bannerDelegate: BannerDelegate) :
    PresentationVM<BannerList>(model) {

    fun openBannerList(bannerId: String) {
        bannerDelegate.openBanner(bannerId)
    }
}