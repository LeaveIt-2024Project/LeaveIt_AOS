package com.example.leaveit.data.placeview.detailplace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import com.example.leaveit.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailPlaceRepositoryImpl @Inject constructor(
    private val bindDetailDataSource: DetailPlaceDataSourceInterface
) : DetailRepository {
    override suspend fun getDetailData(value: String): Flow<DataResource<DetailPlaceDomainModel>> =
        flow {
            emit(DataResource.Loading())
            try {
                val result = bindDetailDataSource.getDetailPlaceInfo(value)
                emit(DataResource.success(result.toDomain()))
            } catch (e: Exception) {
                emit(DataResource.error(e))
            }
        }
    companion object {
        const val TAG = "DetailPlaceRepository"
    }
}