package com.example.leaveit.remote.entity

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.remote.review.ReviewEntityMapper
import java.sql.Timestamp

data class ReviewEntity(
    val feedUid : String,
    val userUid : String,
    val userImage : String,
    val writeUserNickname : String,
    val content : String,
    val feedImage : List<ByteArray>, // 바이트로 받아서 비트맵으로 변환
    val likeCount : Int,
    val writeDate : Timestamp,
    val starCount : Int,
    val placeArea : String,
    val isUserLiked : Boolean
)

data class ReviewEntityList(
    val ReviewEntityList : List<ReviewEntity>
) : ReviewEntityMapper<ReviewDataModel> {

    override suspend fun entityToData(): List<ReviewDataModel> {
        // ByteArray로 오는 이미지 비트맵 -> webp ->  InpuStream으로 변환 후 리턴

        return this.ReviewEntityList.map {
            ReviewDataModel(
                feedUid = it.feedUid,
                userUid = it.userUid,
                userImg = it.userImage,
                writeUserNickname = it.writeUserNickname,
                content = it.content,
                feedImage = it.feedImage,
                likeCount = it.likeCount,
                writeDate = it.writeDate,
                starCount = it.starCount,
                placeArea = it.placeArea,
                isUserLiked = it.isUserLiked
            )
        }
    }
}
