package com.example.leaveit.domain.usecase.review.detailplace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import com.example.leaveit.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailPlaceUseCaseImpl @Inject constructor(
    private val bindDetailRepository: DetailRepository
) : DetailPlaceUseCaseInterface {
    override suspend fun getDetailPlaceData(value : String): Flow<DataResource<DetailPlaceDomainModel>> {
        return bindDetailRepository.getDetailData(value)
    }
}