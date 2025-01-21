package com.example.leaveit.remote.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TourApiRetrofitService {
    private const val BASE_URL  = "http://apis.data.go.kr/B551011/KorService1/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit: Retrofit = Retrofit.Builder() //레트로핏 객체 선언
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}