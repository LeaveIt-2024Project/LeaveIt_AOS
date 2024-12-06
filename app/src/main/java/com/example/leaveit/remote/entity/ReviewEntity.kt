package com.example.leaveit.remote.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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

        return this.ReviewEntityList.map {
            ReviewDataModel(
                feedUid = it.feedUid,
                userUid = it.userUid,
                userImg = it.userImage,
                writeUserNickname = it.writeUserNickname,
                content = it.content,
                feedImage = translateBitmapToInputStream(it.feedImage),
                likeCount = it.likeCount,
                writeDate = it.writeDate,
                starCount = it.starCount,
                placeArea = it.placeArea,
                isUserLiked = it.isUserLiked
            )
        }
    }


    // ByteArray로 오는 이미지 비트맵 -> webp ->  InpuStream으로 변환 후 리턴
    // webp 변환 과정이 너무 불필요하단 생각이 들어서 webp 변환은 일단 보류.
    // 충분한 테스트 후 할지 말지 결정.
    override suspend fun translateBitmapToInputStream(image : List<ByteArray>): List<Bitmap> {

        val result = image.map {
            Log.d(TAG,"${it}")
            BitmapFactory.decodeByteArray(it,0,it.size )
        }

        Log.d(TAG,"${result[0]}")

        return result
    }

    companion object{
        val TAG = "ReviewEntity"
    }
}
