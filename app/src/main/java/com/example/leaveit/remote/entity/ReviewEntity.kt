package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.remote.review.ReviewEntityMapper
import java.sql.Timestamp

data class ReviewEntity(
    val feedUid : String,
    val userUid : String,
    val writeUserNickname : String,
    val content : String,
    val feedImage : List<ByteArray>, // 바이트로 받아서 비트맵으로 변환
    val likeCount : Int,
    val writeDate : Timestamp,
    val starCount : Int,
    val placeArea : String,
    val isUserLiked : Boolean
) : ReviewEntityMapper<ReviewDataModel>{

    override suspend fun entityToData(): ReviewDataModel {
       // ByteArray로 오는 이미지 비트맵 -> webp ->  InpuStream으로 변환 후 리턴


        // 테스트 데이터
        val test = ReviewDataModel(
            "테스트",
            "테스트",
            "테스트",
            "테스트",
            emptyList(),
            1,
            Timestamp(123),
            1,
            "테스트",
            false,
        )

        return test
    }
}
