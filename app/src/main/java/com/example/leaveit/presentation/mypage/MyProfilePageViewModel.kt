package com.example.leaveit.presentation.myprofile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.presentation.myprofile.MyProfilePageModel

class MyProfilePageViewModel : ViewModel() {

    private val _myProfileReviewsData = MutableLiveData<List<MyProfilePageModel>>()
    val myProfileReviewsData: LiveData<List<MyProfilePageModel>> get() = _myProfileReviewsData

    fun loadMyReviews() {
        // 예시로 로컬 데이터나 API 호출을 통해 places 데이터를 설정
        _myProfileReviewsData.value = listOf(
            MyProfilePageModel(feedUID = "1", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "2", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "3", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "4", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "5", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "6", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "7", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "8", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "9", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "10", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "11", content = "재밌는 곳!", feedImage = "https://picsum.photos/200"),
            MyProfilePageModel(feedUID = "12", content = "재밌는 곳!", feedImage = "https://picsum.photos/200")
        )
    }

}