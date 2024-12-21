package com.example.leaveit.data.review.like

import android.util.Log
import com.example.leaveit.data.review.LikeDataSourceInterface
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.LikeRepository
import com.example.leaveit.remote.entity.LikeEntitiy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewLikeRepositoryImpl @Inject constructor() : LikeRepository {

    @Inject
    lateinit var bindReviewDataSourceImpl : LikeDataSourceInterface

    override suspend fun upLiked(signal: LikeEntitiy): Flow<DataResource<Boolean>>{
        bindReviewDataSourceImpl.upLike(signal).collect {dataState ->
            when(dataState){
                is DataResource.Error ->{
                    Log.e(TAG,dataState.throwable.message.toString())
                }
                is DataResource.Loading -> {
                    Log.d(TAG,"upLiked 데이터 로딩중")
                }
                is DataResource.Success -> {
                    Log.d(TAG,"upLiked 데이터 : ${dataState.data}")
                }
            }
        }

        return bindReviewDataSourceImpl.upLike(signal)
    }

    override suspend fun downLiked(signal: LikeEntitiy):  Flow<DataResource<Boolean>>{
        bindReviewDataSourceImpl.downLike(signal).collect{dataState ->
            when(dataState){
                is DataResource.Error ->{
                    Log.e(TAG,dataState.throwable.message.toString())
                }
                is DataResource.Loading -> {
                    Log.d(TAG,"DownLiked 데이터 로딩중")
                }
                is DataResource.Success -> {
                    Log.d(TAG,"DownLiked 데이터 : ${dataState.data}")
                }
            }
        }
        return bindReviewDataSourceImpl.downLike(signal)
    }

    companion object{
        const val TAG = "ReviewLikeRepositoryImpl"
    }
}