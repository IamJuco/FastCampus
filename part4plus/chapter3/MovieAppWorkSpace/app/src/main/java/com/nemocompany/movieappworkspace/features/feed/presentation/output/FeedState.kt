package com.nemocompany.movieappworkspace.features.feed.presentation.output

import com.nemocompany.movieappworkspace.features.common.entity.CategoryEntity

sealed class FeedState {
    data object Loading: FeedState()
    class Main(
        val categories: List<CategoryEntity>
    ): FeedState()
    class Failed(
        val reason: String
    ): FeedState()
}