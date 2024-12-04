package com.example.leaveit.domain.usecase.review

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewUsecaseImpl @Inject constructor() : ReviewUsecaseInterface {
    //리뷰 유스케이스 인터페이스 구현

    @Inject
    lateinit var ReviewRepositoryImpl : ReviewRepository
    override suspend fun getReviewDataTest(): Flow<List<ReviewDataModel>> {
        return ReviewRepositoryImpl.test()
    }
}