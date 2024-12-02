package com.example.leaveit.di

import com.example.leaveit.data.placeview.PlaceViewDataSourceInterface
import com.example.leaveit.data.placeview.PlaceViewRepositoryImpl
import com.example.leaveit.domain.usecase.GetPlaceRepositoryInterface
import com.example.leaveit.domain.usecase.GetPlaceUseCase
import com.example.leaveit.domain.usecase.GetPlaceUseCaseInterface
import com.example.leaveit.remote.placeview.PlaceViewDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlaceViewModule {

    @Binds
    abstract fun bindPlaceViewDataSourceImpl(bindPlaceViewDataSourceImpl: PlaceViewDataSourceImpl): PlaceViewDataSourceInterface

    @Binds
    abstract fun bindPlaceViewRepositoryImpl(bindPlaceViewRepositoryImpl: PlaceViewRepositoryImpl): GetPlaceRepositoryInterface

    @Binds
    abstract fun bindPlaceUseCaseImpl(bindPlaceUseCaseImpl: GetPlaceUseCase): GetPlaceUseCaseInterface

}