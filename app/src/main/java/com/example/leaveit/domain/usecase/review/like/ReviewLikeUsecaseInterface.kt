package com.example.leaveit.domain.usecase.review.like

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.remote.entity.LikeEntitiy
import kotlinx.coroutines.flow.Flow

interface ReviewLikeUsecaseInterface {

    suspend fun upLike(feedUid : LikeEntitiy) : Flow<DataResource<Boolean>>
    suspend fun downLike(feedUid : LikeEntitiy) : Flow<DataResource<Boolean>>
}