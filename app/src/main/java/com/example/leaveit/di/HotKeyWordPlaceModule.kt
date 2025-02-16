package com.example.leaveit.di

import com.example.leaveit.data.placeview.hotkeyowrd.HotKeyWordPlaceDataSource
import com.example.leaveit.data.placeview.hotkeyowrd.HotKeyWordPlaceRepositoryImpl
import com.example.leaveit.domain.repository.HotKeyWordPlaceRepository
import com.example.leaveit.domain.usecase.hotkeyword_place.HotKeyWordPlaceUseCase
import com.example.leaveit.domain.usecase.hotkeyword_place.HotKeyWordPlaceUseCaseImpl
import com.example.leaveit.remote.hotkeyword_place.HotKeyWordPlaceDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HotKeyWordPlaceModule {

    @Binds
    abstract fun bindHotKeyWordPlaceDataSourceImpl(bindHotKeyWordPlaceDataSourceImpl : HotKeyWordPlaceDataSourceImpl) : HotKeyWordPlaceDataSource

    @Binds
    abstract fun bindHotKeyWordPlaceRepositoryImpl(bindHotKeyWordPlaceRepositoryImpl : HotKeyWordPlaceRepositoryImpl) : HotKeyWordPlaceRepository

    @Binds
    abstract fun bindHotKeyWordPlaceUseCaseImpl(bindHotKeyWordPlaceUseCaseImpl : HotKeyWordPlaceUseCaseImpl) : HotKeyWordPlaceUseCase
}