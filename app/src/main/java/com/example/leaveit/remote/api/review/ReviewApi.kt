package com.example.leaveit.remote.api.review

import com.example.leaveit.remote.entity.LikeEntitiy
import com.example.leaveit.remote.entity.ReviewData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewApi {
    //TODO 피드 api 완성되면 넣기

    // 지역별 최신 리뷰 가져오기
    @GET("get/review/region/latest/{region}")
    suspend fun getReviewRegion(
        @Path("region") region : Int,
        @Query("page") page : Int,
        @Query("size") size : Int = 10
    ) : ReviewData

    // 지역별 인기순 리뷰 가져오기
    @GET("get/review/region/like/rank/{region}")
    suspend fun getRankReview(
        @Path("region") region : Int,
        @Query("page") page : Int = 0,
        @Query("size") size : Int = 10
    ) : ReviewData

    // 지역별 좋아요순 리뷰 가져오기
    @GET("get/review/region/star/rank/{region}")
    suspend fun getLikedReview(
        @Path("region") region : Int,
        @Query("page") page : Int = 0,
        @Query("size") size : Int = 10
    ) :  ReviewData

    @POST("/save/like/{feedUID}")
     fun upLike(
        @Body feedUid : LikeEntitiy,
        @Path("feedUID") feeduid : String
    ) : Call<Void>

    @POST("/cancel/like/{feedUID}")
     fun downLike(
        @Body feedUid : LikeEntitiy,
        @Path("feedUID") feeduid : String
    ):  Call<Void>

}