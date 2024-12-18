package com.example.leaveit.data.review

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.domain.repository.ReviewRepository
import com.example.leaveit.remote.api.review.ReviewApi
import com.example.leaveit.remote.review.ReviewSortByRegionPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val apiService : ReviewApi
) : ReviewRepository{
    override suspend fun getReviewDataSortByRegion(regionCode : Int): Flow<PagingData<ReviewDataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // 한 페이지당 10개씩 불러옴
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReviewSortByRegionPagingSource(apiService,regionCode) }
        ).flow
    }

    override suspend fun getReviewDataSortByRank(regionCode: Int): Flow<PagingData<ReviewDataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // 한 페이지당 10개씩 불러옴
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReviewSortByRegionPagingSource(apiService,regionCode) }
        ).flow
    }

    override suspend fun getReviewDataSortByLike(regionCode: Int): Flow<PagingData<ReviewDataModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10, // 한 페이지당 10개씩 불러옴
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ReviewSortByRegionPagingSource(apiService,regionCode) }
        ).flow
    }

    companion object {
        private const val TAG = "ReviewRepository"
        private const val STARTING_PAGE = 1
    }
}