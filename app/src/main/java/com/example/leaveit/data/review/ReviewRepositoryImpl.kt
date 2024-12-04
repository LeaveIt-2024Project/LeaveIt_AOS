package com.example.leaveit.data.review

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.domain.repository.ReviewRepository
import com.example.leaveit.remote.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor() : ReviewRepository {
    // 리뷰 레포지 구현

    @Inject lateinit var bindReviewRepositoryImpl: ReviewDataSourceInteface
    override suspend fun test(): Flow<List<ReviewDataModel>> {

        return bindReviewRepositoryImpl.test()

    }
}