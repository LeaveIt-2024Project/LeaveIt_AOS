package com.example.leaveit.domain

import android.app.Application
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
    fun testHilt() : testHiltClass{
        return testHiltClass()
    }
}