package com.example.leaveit.remote.review

import android.graphics.Bitmap
import com.example.leaveit.data.model.ReviewDataModelList

internal interface ReviewEntityMapper<ReviewDataModel> {
    suspend fun entityToData() : List<ReviewDataModel>
    suspend fun listToDataList(): ReviewDataModelList
    suspend fun translateBitmapToInputStream(image : List<ByteArray>) : List<Bitmap>

}