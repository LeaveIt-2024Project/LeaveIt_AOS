package com.example.leaveit.remote.api.review

import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewApi {
    //TODO 피드 api 완성되면 넣기

    // 지역별 최신 리뷰 가져오기
    @GET("/get/review/region/latest/{region}")
    suspend fun getReviewRegion(
        @Query("region") region : String,
        @Query("page") page : Int = 0,
        @Query("size") size : Int = 10
    )

    // 지역별 좋아요순 리뷰 가져오기
    @GET("/get/review/region/like/rank/{region}")
    suspend fun getRankReview(
        @Query("region") region : String,
        @Query("page") page : Int = 0,
        @Query("size") size : Int = 10
    )

    // 지역별 좋아요순 리뷰 가져오기
    @GET("/get/review/region/star/rank/{region}")
    suspend fun getLikedReview(
        @Query("region") region : String,
        @Query("page") page : Int = 0,
        @Query("size") size : Int = 10
    )



}