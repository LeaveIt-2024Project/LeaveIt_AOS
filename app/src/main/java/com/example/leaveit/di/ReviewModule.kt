package com.example.leaveit.di

import com.example.leaveit.data.review.ReviewDataSourceInteface
import com.example.leaveit.data.review.ReviewRepositoryImpl
import com.example.leaveit.domain.repository.ReviewRepository
import com.example.leaveit.domain.usecase.review.ReviewUsecaseImpl
import com.example.leaveit.domain.usecase.review.ReviewUsecaseInterface
import com.example.leaveit.remote.review.ReviewDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReviewModule {

    @Binds
    abstract fun bindReviewDataSourceImpl(bindReviewDataSourceImpl : ReviewDataSourceImpl) : ReviewDataSourceInteface

    @Binds
    abstract fun bindReviewRepositoryImpl(bindReviewRepositoryImpl : ReviewRepositoryImpl) : ReviewRepository

    @Binds
    abstract fun bindReviewUsecaseImpl(bindReviewUsecaseImpl : ReviewUsecaseImpl) : ReviewUsecaseInterface
}