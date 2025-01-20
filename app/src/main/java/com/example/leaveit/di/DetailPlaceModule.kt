package com.example.leaveit.di

import com.example.leaveit.data.review.detailplace.DetailPlaceDataSourceInterface
import com.example.leaveit.data.review.detailplace.DetailPlaceRepositoryImpl
import com.example.leaveit.domain.repository.DetailRepository
import com.example.leaveit.domain.usecase.review.detailplace.DetailPlaceUseCaseImpl
import com.example.leaveit.domain.usecase.review.detailplace.DetailPlaceUseCaseInterface
import com.example.leaveit.remote.placeview.DetailPlaceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DetailPlaceModule {

    @Binds
    abstract fun bindDetailPlaceDataSourceImpl(bindDetailPlaceDataSourceImpl: DetailPlaceDataSourceImpl) : DetailPlaceDataSourceInterface

    @Binds
    abstract fun bindDetailPlaceRepositoryImpl(bindDetailPlaceRepositoryImpl : DetailPlaceRepositoryImpl) : DetailRepository

    @Binds
    abstract fun bindDetailPlaceUseCaseImpl(bindDetailPlaceUseCaseImpl : DetailPlaceUseCaseImpl) : DetailPlaceUseCaseInterface

}