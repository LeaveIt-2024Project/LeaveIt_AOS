package com.example.leaveit.presentation.myprofile

import android.graphics.Bitmap

data class MyProfilePageModel(
    var feedUID: String,
    val content: String,
    val feedImage: String, // Bitmap으로 전환 예정
)
