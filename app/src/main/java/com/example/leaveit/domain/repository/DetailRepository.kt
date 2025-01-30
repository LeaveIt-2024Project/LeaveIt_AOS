package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailCultureDomainModel
import com.example.leaveit.domain.model.DetailFestivalDomainModel
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getPlaceDetailData(id: String,type : String) : Flow<DataResource<DetailPlaceDomainModel>>

    suspend fun getCultureDetailData(id: String,type : String) : Flow<DataResource<DetailCultureDomainModel>>

    suspend fun getFestivalDetailData(id: String,type : String) : Flow<DataResource<DetailFestivalDomainModel>>
}