package com.example.leaveit.data.model

import java.time.LocalDateTime

data class ReviewDataModel(
    val content: String,
    val createdAt: LocalDateTime? = null,
    val feedImage: String,
    val userImage : String,
    val feedUID: String,
    val useruid : String?,
    val kakaouid : String?,
    val isUserLiked: Boolean,
    val likeCount: Int,
    val nickname: String,
    val placeArea: String,
    val region: String,
    val starCount: Int
)

data class ReviewDataModelList(
    val ReviewDataModelList : List<ReviewDataModel>
)
