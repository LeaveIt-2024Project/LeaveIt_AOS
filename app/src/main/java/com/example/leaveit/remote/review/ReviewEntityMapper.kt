package com.example.leaveit.remote.review

import android.graphics.Bitmap

internal interface ReviewEntityMapper<ReviewDataModel> {
    suspend fun entityToData() : List<ReviewDataModel>
    suspend fun translateBitmapToInputStream(image : List<ByteArray>) : List<Bitmap>
}