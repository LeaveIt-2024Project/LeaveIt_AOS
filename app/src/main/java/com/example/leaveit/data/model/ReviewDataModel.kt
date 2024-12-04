package com.example.leaveit.data.model

import java.sql.Timestamp

data class ReviewDataModel(
    val feedUid : String,
    val userUid : String,
    val userImg : String,
    val writeUserNickname : String,
    val content : String,
    val feedImage : List<ByteArray>, // 바이트로 받아서 비트맵으로 변환
    val likeCount : Int,
    val writeDate : Timestamp,
    val starCount : Int,
    val placeArea : String,
    val isUserLiked : Boolean,
)
