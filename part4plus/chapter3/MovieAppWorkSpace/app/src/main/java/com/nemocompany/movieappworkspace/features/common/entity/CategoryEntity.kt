package com.nemocompany.movieappworkspace.features.common.entity

data class CategoryEntity(
    val id: Int,
    val genre: String,
    val movieDetailEntities: List<MovieFeedItemEntity>
)
