package com.example.leaveit.di

import com.example.leaveit.data.navigate.PathDataSourceInterface
import com.example.leaveit.data.navigate.PathRepositoryImpl
import com.example.leaveit.domain.repository.PathRepository
import com.example.leaveit.domain.usecase.path.GetPathUseCase
import com.example.leaveit.domain.usecase.path.GetPathUseCaseInterface
import com.example.leaveit.remote.path.PathDataResourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PathModule {

    @Binds
    abstract fun bindPathDataSourceImpl(bindPathRepositoryImpl : PathDataResourceImpl) : PathDataSourceInterface

    @Binds
    abstract fun bindPathRepositoryImpl(bindPathRepositoryImpl : PathRepositoryImpl) : PathRepository

    @Binds
    abstract fun bindPathUseCaseImpl(bindPathUseCaseImpl : GetPathUseCase) : GetPathUseCaseInterface
}