package com.example.leaveit.remote.entity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.data.model.ReviewDataModelList
import com.example.leaveit.remote.review.ReviewEntityMapper
import java.time.LocalDateTime

data class ReviewData(
    val content: List<ReviewEntity>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: SortX,
    val totalElements: Int,
    val totalPages: Int
) : ReviewEntityMapper<ReviewDataModel> {

    override suspend fun entityToData(): List<ReviewDataModel> {
        return this.content.map {
            ReviewDataModel(
                feedUID = it.feedUID,
                userImage = "https://img1.kakaocdn.net/thumb/R640x640.q70/?fname=https://t1.kakaocdn.net/account_images/default_profile.jpeg",
                nickname = it.nickname,
                content = it.content,
                feedImage = "a",
//                translateBitmapToInputStream(it.feedImage)
                likeCount = it.likeCount,
                isUserLiked = false,
                createdAt = it.createdAt,
                starCount = it.starCount,
                placeArea = it.placeArea,
                region = it.region
            )
        }
    }

    override suspend fun listToDataList(): ReviewDataModelList {
        val temp = this.content.map {
            ReviewDataModel(
                feedUID = it.feedUID,
                userImage = "https://img1.kakaocdn.net/thumb/R640x640.q70/?fname=https://t1.kakaocdn.net/account_images/default_profile.jpeg",
                nickname = it.nickname,
                content = it.content,
                feedImage = "a",
//                translateBitmapToInputStream(it.feedImage)
                likeCount = it.likeCount,
                isUserLiked = false,
                createdAt = it.createdAt,
                starCount = it.starCount,
                placeArea = it.placeArea,
                region = it.region
            )
        }
        return ReviewDataModelList(temp)
    }


    // ByteArray로 오는 이미지 비트맵 -> webp ->  InpuStream으로 변환 후 리턴
    // webp 변환 과정이 너무 불필요하단 생각이 들어서 webp 변환은 일단 보류.
    // 충분한 테스트 후 할지 말지 결정.
    override suspend fun translateBitmapToInputStream(image: List<ByteArray>): List<Bitmap> {

        val result = image.map {
            Log.d(TAG, "${it}")
            BitmapFactory.decodeByteArray(it, 0, it.size)
        }

        Log.d(TAG, "${result[0]}")

        return result
    }

    data class ReviewEntity(
        val content: String,
        val createdAt: LocalDateTime,
        val feedImage: String,
        val feedUID: String,
        val isUserLiked: Boolean,
        val likeCount: Int,
        val nickname: String,
        val placeArea: String,
        val region: String,
        val starCount: Int
    )

    data class Pageable(
        val offset: Int,
        val pageNumber: Int,
        val pageSize: Int,
        val paged: Boolean,
        val sort: SortX,
        val unpaged: Boolean
    )

    data class SortX(
        val empty: Boolean,
        val sorted: Boolean,
        val unsorted: Boolean
    )

    companion object {
        val TAG = "ReviewEntity"
    }
}
