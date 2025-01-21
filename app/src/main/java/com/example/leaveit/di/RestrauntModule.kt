package com.example.leaveit.di

import com.example.leaveit.data.restraunt.RestrauntDataSource
import com.example.leaveit.data.restraunt.RestrauntRepsitoryImpl
import com.example.leaveit.domain.repository.RestrauntRepository
import com.example.leaveit.domain.usecase.restraunt.RestrauntUseCase
import com.example.leaveit.domain.usecase.restraunt.RestrauntUseCaseImpl
import com.example.leaveit.remote.restraunt.RestrauntDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RestrauntModule {

    @Binds
    abstract fun bindRestrauntDataSourceImpl(bindRestrauntDataSourceImpl : RestrauntDataSourceImpl) : RestrauntDataSource

    @Binds
    abstract fun bindRestrauntRespoitoryImpl(bindRestrauntRespoitoryImpl : RestrauntRepsitoryImpl) : RestrauntRepository

    @Binds
    abstract fun bindRestrauntUseCaseImpl(bindRestrauntUseCaseImpl : RestrauntUseCaseImpl) : RestrauntUseCase

}