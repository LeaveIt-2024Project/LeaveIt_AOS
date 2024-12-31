package com.example.leaveit.di

import com.example.leaveit.data.recent_search.RecentSearchDataSource
import com.example.leaveit.data.recent_search.RecentSearchRepositoryImpl
import com.example.leaveit.domain.repository.RecentSearchRepository
import com.example.leaveit.domain.usecase.recent_data.RecentSearchUseCase
import com.example.leaveit.domain.usecase.recent_data.RecentSearchUseCaseImpl
import com.example.leaveit.local.RecentSearchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RecentSearchModule {

    @Binds
    abstract fun bindRecentSearchDataSourceImpl(bindRecentSearchDataSourcImpl : RecentSearchDataSourceImpl) : RecentSearchDataSource

    @Binds
    abstract fun bindRecentSearchRepositoryImpl(bindRecentSearchRepository : RecentSearchRepositoryImpl) : RecentSearchRepository

    @Binds
    abstract fun bindRecentSearchUseCaseImpl(bindRecentSearchUseCase : RecentSearchUseCaseImpl) : RecentSearchUseCase
}