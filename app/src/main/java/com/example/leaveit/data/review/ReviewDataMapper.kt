package com.example.leaveit.data.review

import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.data.model.ReviewDataModelList

interface ReviewDataMapper {
    suspend fun convertToReviewDataModelList (reviewDataModels: List<ReviewDataModel>): ReviewDataModelList
}