package com.example.leaveit.domain.usecase.review

import androidx.paging.PagingData
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewUseCaseImpl @Inject constructor(
    private val bindReviewRepositoryImpl : ReviewRepository
) : ReviewUseCaseInterface {

    override suspend fun getSortByRegion(regionCode: Int): Flow<PagingData<ReviewDataModel>> {
        return bindReviewRepositoryImpl.getReviewDataSortByRegion(regionCode)
    }

    override suspend fun getSortByRank(regionCode: Int): Flow<PagingData<ReviewDataModel>> {
        return bindReviewRepositoryImpl.getReviewDataSortByRank(regionCode)
    }

    override suspend fun getSortByLike(regionCode: Int): Flow<PagingData<ReviewDataModel>> {
        return bindReviewRepositoryImpl.getReviewDataSortByLike(regionCode)
    }
}