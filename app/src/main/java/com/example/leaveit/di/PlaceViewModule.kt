package com.example.leaveit.di

import com.example.leaveit.data.placeview.PlaceViewRepositoryImpl
import com.example.leaveit.domain.usecase.place.GetPlaceRepositoryInterface
import com.example.leaveit.domain.usecase.place.GetPlaceUseCase
import com.example.leaveit.domain.usecase.place.GetPlaceUseCaseInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlaceViewModule {
    @Binds
    abstract fun bindPlaceViewRepositoryImpl(bindPlaceViewRepositoryImpl: PlaceViewRepositoryImpl): GetPlaceRepositoryInterface

    @Binds
    abstract fun bindPlaceUseCaseImpl(bindPlaceUseCaseImpl: GetPlaceUseCase): GetPlaceUseCaseInterface

}