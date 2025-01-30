package com.example.leaveit.domain.usecase.place.detailplace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailCultureDomainModel
import com.example.leaveit.domain.model.DetailFestivalDomainModel
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import com.example.leaveit.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailPlaceUseCaseImpl @Inject constructor(
    private val bindDetailRepository: DetailRepository
) : DetailPlaceUseCaseInterface {
    override suspend fun getDetailPlaceData(
        id: String,
        type: String
    ): Flow<DataResource<DetailPlaceDomainModel>> {
        return bindDetailRepository.getPlaceDetailData(id = id, type = type)
    }

    override suspend fun getDetailCultureData(
        id: String,
        type: String
    ): Flow<DataResource<DetailCultureDomainModel>> {
        return bindDetailRepository.getCultureDetailData(id = id, type = type)
    }

    override suspend fun getDetailFestivalData(
        id: String,
        type: String
    ): Flow<DataResource<DetailFestivalDomainModel>> {
        return bindDetailRepository.getFestivalDetailData(id = id, type = type)
    }
}