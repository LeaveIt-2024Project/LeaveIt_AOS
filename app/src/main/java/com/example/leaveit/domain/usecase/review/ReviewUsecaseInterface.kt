package com.example.leaveit.domain.usecase.review

import com.example.leaveit.remote.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow


interface ReviewUsecaseInterface {
    // 리뷰 유스케이스 인터페이스

    suspend fun getReviewDataTest() : Flow<List<ReviewEntity>>
}