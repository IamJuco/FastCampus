package com.nemocompany.movieappworkspace.features.feed.presentation.output

sealed class FeedState {
    data object Loading: FeedState()
    class Main(
        val movieList: List<MovieFeedItemEntity>
    ): FeedState()
    class Failed(
        val reason: String
    ): FeedState()
}