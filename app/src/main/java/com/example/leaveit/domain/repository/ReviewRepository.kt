package com.example.leaveit.domain.repository

import com.example.leaveit.remote.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    // 리뷰 레포지 인터페이스 선언

    suspend fun test() : Flow<List<ReviewEntity>>
}