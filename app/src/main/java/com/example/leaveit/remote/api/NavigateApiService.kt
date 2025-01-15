package com.example.leaveit.remote.api

import com.example.leaveit.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NavigateApiService {

    private const val BASE_URL  = "https://naveropenapi.apigw.ntruss.com/" // 에뮬레이터의 localhost는 10.0.2.2

    var gson = GsonBuilder()
        .setLenient()
        .create()

    // Naver api key, id 헤더에 추가
    val interceptor = (Interceptor { chain ->
        val id = BuildConfig.MAP_API_KEY_ID
        val key = BuildConfig.MAP_API_KEY
        val newRequest = chain.request().newBuilder()
            .addHeader("x-ncp-apigw-api-key-id",id)
            .addHeader("x-ncp-apigw-api-key", key) // Bearer 추가
            .build()
        val response = chain.proceed(newRequest)
        response
    })

    val okHttpClient : OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder() //레트로핏 객체 선언
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}