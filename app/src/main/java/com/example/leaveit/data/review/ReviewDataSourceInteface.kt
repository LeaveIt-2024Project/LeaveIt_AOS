package com.example.leaveit.data.review

import com.example.leaveit.remote.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

interface ReviewDataSourceInteface {
    //리뷰 데이터소스 인터페이스

    suspend fun test() : Flow<List<ReviewEntity>>
}