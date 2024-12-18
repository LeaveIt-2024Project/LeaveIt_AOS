package com.example.leaveit.domain.repository

import androidx.paging.PagingData
import com.example.leaveit.data.model.ReviewDataModel
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    // 리뷰 레포지 인터페이스 선언
    suspend fun getReviewDataSortByRegion(regionCode : Int) : Flow<PagingData<ReviewDataModel>>
    suspend fun getReviewDataSortByRank(regionCode : Int) : Flow<PagingData<ReviewDataModel>>
    suspend fun getReviewDataSortByLike(regionCode : Int) : Flow<PagingData<ReviewDataModel>>


}