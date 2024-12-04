package com.example.leaveit.remote.review

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.data.review.ReviewDataSourceInteface
import com.example.leaveit.remote.entity.ReviewEntity
import com.example.leaveit.remote.entity.ReviewEntityList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.Timestamp
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor() : ReviewDataSourceInteface {
    //데이터소스 인터페이스 impl

    override suspend fun test(): Flow<List<ReviewDataModel>> = flow {
        delay(2000L) // 2초 딜레이
        val temp = createTestData().entityToData()
        emit(temp)
    }

    suspend fun createTestData() : ReviewEntityList{
        val temp = ReviewEntityList(
                listOf(
                    ReviewEntity(
                        feedUid = "123",
                        userUid = "456",
                        writeUserNickname = "성욱",
                        content = "테스트입니다",
                        userImage = "https://img1.kakaocdn.net/thumb/R640x640.q70/?fname=https://t1.kakaocdn.net/account_images/default_profile.jpeg",
                        feedImage = emptyList(),
                        likeCount = 1,
                        writeDate = Timestamp(123),
                        starCount = 5,
                        placeArea = "12",
                        isUserLiked = false
                    ),
                    ReviewEntity(
                        feedUid = "123",
                        userUid = "456",
                        writeUserNickname = "성욱",
                        content = "테스트입니다",
                        userImage = "https://img1.kakaocdn.net/thumb/R640x640.q70/?fname=https://t1.kakaocdn.net/account_images/default_profile.jpeg",
                        feedImage = emptyList(),
                        likeCount = 1,
                        writeDate = Timestamp(123),
                        starCount = 5,
                        placeArea = "12",
                        isUserLiked = false
                    ),
                    ReviewEntity(
                        feedUid = "123",
                        userUid = "456",
                        writeUserNickname = "성욱",
                        content = "테스트입니다",
                        userImage = "https://img1.kakaocdn.net/thumb/R640x640.q70/?fname=https://t1.kakaocdn.net/account_images/default_profile.jpeg",
                        feedImage = emptyList(),
                        likeCount = 1,
                        writeDate = Timestamp(123),
                        starCount = 5,
                        placeArea = "12",
                        isUserLiked = false
                    )
                )
            )
        return temp

    }
}