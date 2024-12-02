package com.example.leaveit.di

import com.example.leaveit.domain.testHiltClass
import com.example.leaveit.remote.api.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun testHilt() : testHiltClass {
        return testHiltClass()
    }

    @Provides
    @Singleton
    fun provideRetofitService() : RetrofitService {
        return RetrofitService
    }
}
