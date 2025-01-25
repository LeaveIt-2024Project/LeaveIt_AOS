package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.RestrantListDomainModel
import kotlinx.coroutines.flow.Flow

interface RestrauntRepository {

    suspend fun getRestrauntData(longitude : String, latitude : String) : Flow<DataResource<RestrantListDomainModel>>
}