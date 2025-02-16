package com.example.leaveit.di

import com.example.leaveit.data.placeview.recentsearchplace.RecentSearchPlaceRepositoryImpl
import com.example.leaveit.data.placeview.storeKeyword.PlaceSearchDataSource
import com.example.leaveit.domain.repository.RecentSearchPlaceRepository
import com.example.leaveit.domain.usecase.place.recentSearchPlace.RecentSearchPlaceUseCase
import com.example.leaveit.domain.usecase.place.recentSearchPlace.RecentSearchPlaceUseCaseImpl
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RecentSearchPlaceModule {

    @Binds
    abstract fun bindPlaceSearchDataSourceImpl(bindRecentSearchPlaceDataSourceImplImpl : RecentSearchPlaceDataSourceImpl) : PlaceSearchDataSource

    @Binds
    abstract fun bindRecentSearchPlaceRepositoryImpl(bindRecentSearchPlaceRepositoryImpl : RecentSearchPlaceRepositoryImpl) : RecentSearchPlaceRepository

    @Binds
    abstract fun bindRecentSearchPlaceUseCaseImpl(bindRecentSearchPlaceUseCaseImpl : RecentSearchPlaceUseCaseImpl) : RecentSearchPlaceUseCase
}