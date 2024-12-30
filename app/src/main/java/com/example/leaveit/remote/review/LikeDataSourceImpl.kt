package com.example.leaveit.remote.review

import com.example.leaveit.data.review.LikeDataSourceInterface
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.remote.api.review.ReviewApi
import com.example.leaveit.remote.entity.LikeEntitiy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LikeDataSourceImpl @Inject constructor(
    private val service  : ReviewApi
) : LikeDataSourceInterface {
    override suspend fun upLike(signal: LikeEntitiy): Flow<DataResource<Boolean>> = flow {
        emit(DataResource.loading())
        try {
            val response = withContext(Dispatchers.IO) {
                service.upLike(signal, signal.feedUID).execute() // 동기 호출
            }
            if (response.isSuccessful) {
                emit(DataResource.success(true)) // 성공 처리
            } else {
                emit(DataResource.error(Exception("Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    override suspend fun downLike(signal: LikeEntitiy): Flow<DataResource<Boolean>> = flow {
        emit(DataResource.loading())
        try {
            val response = withContext(Dispatchers.IO) {
                service.downLike(signal, signal.feedUID).execute() // 동기 호출
            }
            if (response.isSuccessful) {
                emit(DataResource.success(true)) // 성공 처리
            } else {
                emit(DataResource.error(Exception("Error: ${response.code()}")))
            }
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }
}