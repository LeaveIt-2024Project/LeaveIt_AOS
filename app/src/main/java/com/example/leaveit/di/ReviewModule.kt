package com.example.leaveit.di

import com.example.leaveit.data.review.ReviewRepositoryImpl
import com.example.leaveit.domain.repository.ReviewRepository
import com.example.leaveit.domain.usecase.review.ReviewUseCaseImpl
import com.example.leaveit.domain.usecase.review.ReviewUseCaseInterface
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

}