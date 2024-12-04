package com.example.leaveit.data.review

import com.example.leaveit.data.model.ReviewDataModel
import kotlinx.coroutines.flow.Flow

interface ReviewDataSourceInteface {
    //리뷰 데이터소스 인터페이스

    suspend fun test() : Flow<List<ReviewDataModel>>
}