package com.example.leaveit.remote.review

internal interface ReviewEntityMapper<ReviewDataModel> {
    suspend fun entityToData() : List<ReviewDataModel>
}