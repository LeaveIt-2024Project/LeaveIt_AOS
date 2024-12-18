package com.example.leaveit.di

import com.example.leaveit.remote.api.RetrofitService
import com.example.leaveit.remote.api.review.ReviewApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideRetofitService() : RetrofitService {
        return RetrofitService
    }

    @Provides
    fun provideReviewApiService() : ReviewApi{ // 리뷰 호출 API 의존성 추가
        return RetrofitService.retrofit.create(ReviewApi::class.java)
    }

}
