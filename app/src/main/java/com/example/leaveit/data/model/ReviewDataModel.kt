package com.example.leaveit.data.model

import android.graphics.Bitmap
import java.sql.Timestamp

data class ReviewDataModel(
    val feedUid : String,
    val userUid : String,
    val userImg : String,
    val writeUserNickname : String,
    val content : String,
    val feedImage : List<Bitmap>,
    val likeCount : Int,
    val writeDate : Timestamp,
    val starCount : Int,
    val placeArea : String,
    val isUserLiked : Boolean,
)
