package com.example.leaveit.remote.api

import com.example.leaveit.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService{
    //"http://apis.data.go.kr/B551011/KorService1/"
    private const val BASE_URL  = "http://10.0.2.2:8080/" // 에뮬레이터의 localhost는 10.0.2.2
    var gson = GsonBuilder().setLenient().create()


    // Auth 토큰 인터셉터 구현
    val interceptor = (Interceptor { chain ->
        var token = BuildConfig.TEST_TOKEN
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token") // Bearer 추가
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
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}