package com.example.leaveit.di

import com.example.leaveit.data.detail_restraunt.DetailRestrauntDataSource
import com.example.leaveit.data.detail_restraunt.DetailRestrauntRepositoryImpl
import com.example.leaveit.domain.repository.DetailRestrauntRepository
import com.example.leaveit.domain.usecase.detail_restraunt.DetailRestrauntUseCase
import com.example.leaveit.domain.usecase.detail_restraunt.DetailRestrauntUseCaseImpl
import com.example.leaveit.remote.detail_restraunt.DetailRestrauntDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailRestrauntModule {

    @Binds
    abstract fun bindDetailRestrauntDataSource(bindDetailRestrauntDataSource : DetailRestrauntDataSourceImpl) : DetailRestrauntDataSource

    @Binds
    abstract fun bindDetailRestrauntRespository(bindDetailRestrauntRespository : DetailRestrauntRepositoryImpl) : DetailRestrauntRepository

    @Binds
    abstract fun bindDetailRestrauntUseCase(bindDetailRestrauntUseCase : DetailRestrauntUseCaseImpl) : DetailRestrauntUseCase

}