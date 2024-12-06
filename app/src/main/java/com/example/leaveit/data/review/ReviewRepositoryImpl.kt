package com.example.leaveit.data.review

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor() : ReviewRepository {
    // 리뷰 레포지 구현

    @Inject lateinit var bindReviewDataSourceImpl: ReviewDataSourceInteface
    override suspend fun test(): Flow<DataResource<List<ReviewDataModel>>> = flow{
            val result = bindReviewDataSourceImpl.test().collect{
                when(it){
                    is DataResource.Success -> emit(DataResource.success(it.data))
                    is DataResource.Error -> emit(DataResource.error(it.throwable))
                    is DataResource.Loading -> emit(DataResource.loading())
                }
            }
    }

    companion object{
        val TAG = "ReviewRepositoryImpl"
    }
}