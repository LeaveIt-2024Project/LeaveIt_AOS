package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.ReverseGeoCodingDomainModel
import kotlinx.coroutines.flow.Flow

interface ReverseGeoCodingRepository {
    suspend fun getReverseGeoData(value : String) : Flow<DataResource<ReverseGeoCodingDomainModel>>
}