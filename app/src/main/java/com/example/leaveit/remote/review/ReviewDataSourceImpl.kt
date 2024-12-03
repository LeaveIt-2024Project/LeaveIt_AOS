package com.example.leaveit.remote.review

import com.example.leaveit.data.review.ReviewDataSourceInteface
import com.example.leaveit.remote.entity.ReviewEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.Timestamp
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor() : ReviewDataSourceInteface {
    //데이터소스 인터페이스 impl

    override suspend fun test(): Flow<List<ReviewEntity>> = flow {
        delay(2000L) // 2초 딜레이
        emit(createTestData())
    }

    suspend fun createTestData() : List<ReviewEntity>{
        val temp = listOf(
            ReviewEntity(
                feedUid = "123",
                userUid = "456",
                writeUserNickname = "성욱",
                content = "테스트입니다",
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
                writeUserNickname = "주형",
                content = "테스트입니다",
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
                writeUserNickname = "재건",
                content = "테스트입니다",
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
                writeUserNickname = "성진",
                content = "테스트입니다",
                feedImage = emptyList(),
                likeCount = 1,
                writeDate = Timestamp(123),
                starCount = 5,
                placeArea = "12",
                isUserLiked = false
            ),
        )
        return temp

    }
}