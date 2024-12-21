package com.example.leaveit.domain.usecase.review.like

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.LikeRepository
import com.example.leaveit.remote.entity.LikeEntitiy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewLikeUseCaseImpl @Inject constructor(
     val bindReviewLikeRepositoryImpl : LikeRepository
) : ReviewLikeUsecaseInterface {

    override suspend fun upLike(signal : LikeEntitiy): Flow<DataResource<Boolean>> {
        return bindReviewLikeRepositoryImpl.upLiked(signal)
    }

    override suspend fun downLike(signal : LikeEntitiy): Flow<DataResource<Boolean>> {
       return bindReviewLikeRepositoryImpl.downLiked(signal)
    }
}