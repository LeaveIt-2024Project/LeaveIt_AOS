package com.example.leaveit.domain.usecase.review

import com.example.leaveit.domain.repository.ReviewRepository
import com.example.leaveit.remote.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewUsecaseImpl @Inject constructor() : ReviewUsecaseInterface {
    //리뷰 유스케이스 인터페이스 구현

    @Inject
    lateinit var ReviewRepositoryImpl : ReviewRepository
    override suspend fun getReviewDataTest(): Flow<List<ReviewEntity>> {
        return ReviewRepositoryImpl.test()
    }
}