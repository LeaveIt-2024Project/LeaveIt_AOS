package com.example.leaveit.di

import android.content.Context
import androidx.room.Room
import com.example.leaveit.local.AppDatabase
import com.example.leaveit.remote.api.RetrofitService
import com.example.leaveit.remote.api.ReverseGeoCodingRetrofitService
import com.example.leaveit.remote.api.TourApiRetrofitService
import com.example.leaveit.remote.api.navigate.ReverseGeoCodingAPI
import com.example.leaveit.remote.api.review.DetailPlaceApi
import com.example.leaveit.remote.api.review.ReviewApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideRetofitService() : RetrofitService {
        return RetrofitService
    }

    @Provides
    fun provideReviewApiService() : ReviewApi{ // 리뷰 호출 API 의존성 추가
        return RetrofitService.retrofit.create(ReviewApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTourApiRetrofitService() : TourApiRetrofitService{
        return TourApiRetrofitService
    }

    @Provides
    fun provideDetailPlaceApiService() : DetailPlaceApi{
        return TourApiRetrofitService.retrofit.create(DetailPlaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReverseGeoCodingRetrofitService() : ReverseGeoCodingRetrofitService {
        return ReverseGeoCodingRetrofitService
    }

    @Provides
    @Singleton
    fun provideReverseGeoCodingApiService() : ReverseGeoCodingAPI{
        return ReverseGeoCodingRetrofitService.retrofit.create(ReverseGeoCodingAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.appData()

}
