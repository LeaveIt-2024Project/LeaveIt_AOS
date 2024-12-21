package com.example.leaveit.di

import com.example.leaveit.data.review.LikeDataSourceInterface
import com.example.leaveit.data.review.ReviewRepositoryImpl
import com.example.leaveit.data.review.like.ReviewLikeRepositoryImpl
import com.example.leaveit.domain.repository.LikeRepository
import com.example.leaveit.domain.repository.ReviewRepository
import com.example.leaveit.domain.usecase.review.ReviewUseCaseImpl
import com.example.leaveit.domain.usecase.review.ReviewUseCaseInterface
import com.example.leaveit.domain.usecase.review.like.ReviewLikeUseCaseImpl
import com.example.leaveit.domain.usecase.review.like.ReviewLikeUsecaseInterface
import com.example.leaveit.remote.review.LikeDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReviewModule {

    @Binds
    abstract fun bindReviewRepositoryImpl(bindReviewRepositoryImpl : ReviewRepositoryImpl) : ReviewRepository

    @Binds
    abstract fun bindReviewSortByRegionUseCaseImpl(bindReviewUsecaseImpl : ReviewUseCaseImpl) : ReviewUseCaseInterface

    @Binds
    abstract fun bindReviewDataSourceImpl(bindLikeDataSourceImpl: LikeDataSourceImpl) : LikeDataSourceInterface

    @Binds
    abstract fun bindReviewLikeRepositoryImpl(bindReviewLikeRepositoryImpl: ReviewLikeRepositoryImpl) : LikeRepository

    @Binds
    abstract fun bindReviewLikeUsecaseImpl(bindReviewLikeUsecaseImpl : ReviewLikeUseCaseImpl) : ReviewLikeUsecaseInterface

}

