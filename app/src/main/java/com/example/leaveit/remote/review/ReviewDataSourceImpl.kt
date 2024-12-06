package com.example.leaveit.remote.review

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.data.review.ReviewDataSourceInteface
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.remote.entity.ReviewEntity
import com.example.leaveit.remote.entity.ReviewEntityList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.sql.Timestamp
import javax.inject.Inject

class ReviewDataSourceImpl @Inject constructor() : ReviewDataSourceInteface {
    //데이터소스 인터페이스 impl

    override suspend fun test(): Flow<DataResource<List<ReviewDataModel>>> = flow {
        emit(DataResource.loading()) // 로딩중
        delay(2000L) // 2초 딜레이
        try{
            val temp = createTestData().entityToData()
            emit(DataResource.success(temp)) // 데이터 불러오기 성공
        }catch (e : Exception){
            emit(error(e)) // 에러 던짐
        }
    }

     fun createTestData() : ReviewEntityList{
        val temp = ReviewEntityList(
                listOf(
                    ReviewEntity(
                        feedUid = "123",
                        userUid = "456",
                        writeUserNickname = "성욱",
                        content = "테스트입니다",
                        userImage = "https://img1.kakaocdn.net/thumb/R640x640.q70/?fname=https://t1.kakaocdn.net/account_images/default_profile.jpeg",
                        feedImage = testCreateByteImage(),
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
                        feedImage = testCreateByteImage(),
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
                        feedImage = testCreateByteImage(),
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

    fun testCreateByteImage() : List<ByteArray>{
        val temp = listOf(
            byteArrayOf(
                0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(),
                0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x0D.toByte(),
                0x49.toByte(), 0x48.toByte(), 0x44.toByte(), 0x52.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x10.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x10.toByte(),
                0x08.toByte(), 0x06.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x73.toByte(), 0x68.toByte(), 0x64.toByte(), 0x72.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte()
            ),
            byteArrayOf(
                0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(),
                0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x0D.toByte(),
                0x49.toByte(), 0x48.toByte(), 0x44.toByte(), 0x52.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x10.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x10.toByte(),
                0x08.toByte(), 0x06.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x73.toByte(), 0x68.toByte(), 0x64.toByte(), 0x72.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte()
            ),
            byteArrayOf(
                0x89.toByte(), 0x50.toByte(), 0x4E.toByte(), 0x47.toByte(),
                0x0D.toByte(), 0x0A.toByte(), 0x1A.toByte(), 0x0A.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x0D.toByte(),
                0x49.toByte(), 0x48.toByte(), 0x44.toByte(), 0x52.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x10.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x10.toByte(),
                0x08.toByte(), 0x06.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x73.toByte(), 0x68.toByte(), 0x64.toByte(), 0x72.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte(),
                0x00.toByte(), 0x00.toByte(), 0x00.toByte(), 0x00.toByte()
            )
        )
        return temp
    }

    companion object{
        val TAG = "ReviewDataSourceImpl"
    }

}