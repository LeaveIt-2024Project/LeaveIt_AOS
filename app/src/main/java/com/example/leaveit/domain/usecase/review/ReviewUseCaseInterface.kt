package com.example.leaveit.domain.usecase.review

import androidx.paging.PagingData
import com.example.leaveit.data.model.ReviewDataModel
import kotlinx.coroutines.flow.Flow

interface ReviewUseCaseInterface {

    suspend fun getSortByRegion(regionCode : Int) : Flow<PagingData<ReviewDataModel>>

    suspend fun getSortByRank(regionCode : Int) : Flow<PagingData<ReviewDataModel>>

    suspend fun getSortByLike(regionCode : Int) : Flow<PagingData<ReviewDataModel>>

}