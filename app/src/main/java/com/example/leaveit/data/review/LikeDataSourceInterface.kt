package com.example.leaveit.data.review

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.remote.entity.LikeEntitiy
import kotlinx.coroutines.flow.Flow

interface LikeDataSourceInterface {
    suspend fun upLike(signal :LikeEntitiy) : Flow<DataResource<Boolean>>
    suspend fun downLike(signal : LikeEntitiy) : Flow<DataResource<Boolean>>
}