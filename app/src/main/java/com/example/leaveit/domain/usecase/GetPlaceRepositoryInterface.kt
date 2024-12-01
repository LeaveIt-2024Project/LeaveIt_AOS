package com.example.leaveit.domain.usecase

import com.example.leaveit.domain.model.PlaceDomainListModel

interface GetPlaceRepositoryInterface {
    suspend fun getPlaceRepository(contentTypedId : String, areaCode : String) : PlaceDomainListModel
}