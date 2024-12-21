package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.remote.entity.LikeEntitiy
import kotlinx.coroutines.flow.Flow

interface LikeRepository {
    suspend fun upLiked(signal : LikeEntitiy) : Flow<DataResource<Boolean>>

    suspend fun downLiked(signal : LikeEntitiy) : Flow<DataResource<Boolean>>
}