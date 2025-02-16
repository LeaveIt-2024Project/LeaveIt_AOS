package com.example.leaveit.data.placeview

import com.example.leaveit.data.model.PlaceViewDataModelList
import com.example.leaveit.domain.model.PlaceDomainListModel
import com.example.leaveit.domain.model.PlaceDomainModel
import kotlinx.coroutines.Deferred

internal interface PlaceViewDataMapperInterface{
    suspend fun toDomain(temp: Deferred<PlaceViewDataModelList>): PlaceDomainListModel
    suspend fun toDomain() : PlaceDomainModel
}