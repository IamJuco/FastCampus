package com.fastcampus.part5.model

import com.fastcampus.part5.delegate.BannerDelegate
import com.fastcampus.part5.domain.model.Banner

class BannerVM(model: Banner, bannerDelegate: BannerDelegate) : PresentationVM<Banner>(model),
    BannerDelegate by bannerDelegate