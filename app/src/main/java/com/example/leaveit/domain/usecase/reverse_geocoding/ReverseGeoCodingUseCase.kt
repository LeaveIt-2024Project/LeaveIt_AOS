package com.example.leaveit.domain.usecase.reverse_geocoding

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.ReverseGeoCodingDomainModel
import kotlinx.coroutines.flow.Flow

interface ReverseGeoCodingUseCase {

    suspend fun getReverseGeoCodingData(value : String) : Flow<DataResource<ReverseGeoCodingDomainModel>>

}