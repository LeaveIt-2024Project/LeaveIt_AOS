package com.example.leaveit.remote.api

import com.example.leaveit.remote.api.search.SearchApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NaverRetrofit {
    private const val BASE_URL = "https://openapi.naver.com/"
    val searchApi: SearchApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }
}