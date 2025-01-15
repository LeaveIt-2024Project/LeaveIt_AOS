package com.example.leaveit.di

import com.example.leaveit.data.navigate.ReverseGeoCodingDataSourceInterface
import com.example.leaveit.data.navigate.ReverseGeoCodingRepsitoryImpl
import com.example.leaveit.domain.repository.ReverseGeoCodingRepository
import com.example.leaveit.domain.usecase.reverse_geocoding.ReverseGeoCodingUseCase
import com.example.leaveit.domain.usecase.reverse_geocoding.ReverseGeoCodingUseCaseImpl
import com.example.leaveit.remote.reversegedocoding.ReverseGeoCodingDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReverseGeoCodingModule {
    @Binds
    abstract fun bindReverseGeoCodingDataSourceImpl(bindReverseGeoCodingDataSourceImpl: ReverseGeoCodingDataSourceImpl): ReverseGeoCodingDataSourceInterface

    @Binds
    abstract fun bindReverseGeoCodingRepositoryImpl(bindReverseGeoCodingDataSourceImpl: ReverseGeoCodingRepsitoryImpl): ReverseGeoCodingRepository

    @Binds
    abstract fun bindReverseGeoCodingUsecaseImpl(bindReverseGeoCodingUsecaseImpl: ReverseGeoCodingUseCaseImpl): ReverseGeoCodingUseCase

}