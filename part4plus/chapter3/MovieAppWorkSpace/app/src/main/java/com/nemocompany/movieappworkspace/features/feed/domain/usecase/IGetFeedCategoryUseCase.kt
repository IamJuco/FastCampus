package com.nemocompany.movieappworkspace.features.feed.domain.usecase

import com.nemocompany.movieappworkspace.features.common.entity.CategoryEntity
import com.nemocompany.movieappworkspace.features.common.entity.EntityWrapper
import com.nemocompany.movieappworkspace.features.feed.domain.enum.SortOrder

interface IGetFeedCategoryUseCase {
    // UseCase를 사용하려면 사용 하는곳에서 invoke 처리를 해야하는데 굳이 그럴필요없게 미리 처리,
    // invoke를 처리하기위해서 operator를 사용
    suspend operator fun invoke(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
}