package com.example.leaveit.remote.api

import android.util.Log
import com.example.leaveit.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://travel.zkrp.site/"

    // Gson 설정
    var gson = GsonBuilder().setLenient().create()

    // HttpLoggingInterceptor로 요청 URL과 메서드만 로깅
    val logging = HttpLoggingInterceptor { message -> Log.d("Retrofit_URL", message) }
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        } // Logs URL, method, request/response body

    // 인증 토큰 인터셉터
    val interceptor = Interceptor { chain ->
        val token = BuildConfig.TEST_TOKEN
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token") // Bearer 추가
            .build()
        chain.proceed(newRequest)
    }

    // OkHttpClient 설정
    val okHttpClient: OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(interceptor) // 인증 토큰 인터셉터 추가
        .addInterceptor(logging) // 로깅 인터셉터 추가
        .build()

    // Retrofit 객체 선언
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Base URL 설정
        .client(okHttpClient) // OkHttpClient 설정
        .addConverterFactory(GsonConverterFactory.create(gson)) // Gson 변환기 설정
        .build()
}
