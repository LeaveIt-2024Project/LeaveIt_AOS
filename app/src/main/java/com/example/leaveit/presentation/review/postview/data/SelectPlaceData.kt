package com.example.leaveit.presentation.review.postview.data

import java.time.LocalDateTime


data class SelectPlaceData(
    val feedUID: String?,
    val nickname: String?,
    val content: String?,
    val feedImage: String?,
    val likeCount: Int?,
    val starCount: Int?,
    val placeArea: String?,
    val isUserLiked: Boolean?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)