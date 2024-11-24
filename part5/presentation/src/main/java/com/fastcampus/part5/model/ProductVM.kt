package com.fastcampus.part5.model

import com.fastcampus.part5.delegate.ProductDelegate
import com.fastcampus.part5.domain.model.Product

class ProductVM(model: Product, productDelegate: ProductDelegate) : PresentationVM<Product>(model),
    ProductDelegate by productDelegate