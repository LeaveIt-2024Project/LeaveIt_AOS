package com.example.leaveit.di

import com.example.leaveit.data.placeview.searchPlace.StoreLogSearchKeywordDataSourceInterface
import com.example.leaveit.data.placeview.searchPlace.StoreLogSearchKeywordRepositoryImpl
import com.example.leaveit.domain.repository.StoreLogSearchKeywordRespoitory
import com.example.leaveit.domain.usecase.place.searchPlace.StoreLogSearchKeywordUseCase
import com.example.leaveit.domain.usecase.place.searchPlace.StoreLogSearchKeywordUseCaseImpl
import com.example.leaveit.remote.search_place.StoreLogSearchKeywordDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun bindStoreLogSearchKeywordDataSourceImpl(bindStoreLogSearchKeywordDataSourceImpl : StoreLogSearchKeywordDataSourceImpl) : StoreLogSearchKeywordDataSourceInterface

    @Binds
    abstract fun bindStoreLogSearchKeywordRepositoryImpl(bindStoreLogSearchKeywordRepositoryImpl : StoreLogSearchKeywordRepositoryImpl) : StoreLogSearchKeywordRespoitory

    @Binds
    abstract fun bindStoreLogSearchKeywordUseCaseImpl(bindStoreLogSearchKeywordUseCaseImpl : StoreLogSearchKeywordUseCaseImpl) : StoreLogSearchKeywordUseCase
}