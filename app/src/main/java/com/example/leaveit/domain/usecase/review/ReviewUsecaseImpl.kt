package com.example.leaveit.domain.usecase.review

import android.util.Log
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewUsecaseImpl @Inject constructor() : ReviewUsecaseInterface {
    //리뷰 유스케이스 인터페이스 구현

    @Inject
    lateinit var bindReviewRepositoryImpl : ReviewRepository
    override suspend fun getReviewDataTest(): Flow<DataResource<List<ReviewDataModel>>> {
        val temp = bindReviewRepositoryImpl.test()

        if(temp == null){
            Log.d(TAG,"null")
        }else{
            Log.d(TAG,"notnull")
        }

        return temp
    }

    companion object{
        val TAG = "ReviewUsecaseImpl"
    }
}