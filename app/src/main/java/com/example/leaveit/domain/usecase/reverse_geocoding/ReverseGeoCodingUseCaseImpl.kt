package com.example.leaveit.domain.usecase.reverse_geocoding

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.ReverseGeoCodingDomainModel
import com.example.leaveit.domain.repository.ReverseGeoCodingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReverseGeoCodingUseCaseImpl @Inject constructor(
    private val bindReverseGeoCodingRepository: ReverseGeoCodingRepository
) : ReverseGeoCodingUseCase {
    override suspend fun getReverseGeoCodingData(value: String): Flow<DataResource<ReverseGeoCodingDomainModel>> {
        return bindReverseGeoCodingRepository.getReverseGeoData(value)
    }
}