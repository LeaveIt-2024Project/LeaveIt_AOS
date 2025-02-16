package com.example.leaveit.data.placeview.detailplace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailCultureDomainModel
import com.example.leaveit.domain.model.DetailFestivalDomainModel
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import com.example.leaveit.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailPlaceRepositoryImpl @Inject constructor(
    private val bindDetailDataSource: DetailPlaceDataSourceInterface
) : DetailRepository {
    override suspend fun getPlaceDetailData(
        id: String,
        type: String
    ): Flow<DataResource<DetailPlaceDomainModel>> =
        flow {
            emit(DataResource.Loading())
            try {
                val result = bindDetailDataSource.getDetailPlaceInfo(id = id, type = type)
                emit(DataResource.success(result.toDomain()))
            } catch (e: Exception) {
                emit(DataResource.error(e))
            }
        }

    override suspend fun getCultureDetailData(
        id: String,
        type: String
    ): Flow<DataResource<DetailCultureDomainModel>> =
        flow {
            emit(DataResource.Loading())
            try {
                val result = bindDetailDataSource.getDetailCulturePlaceInfo(id = id, type = type)
                emit(DataResource.success(result.toDomain()))
            } catch (e: Exception) {
                emit(DataResource.error(e))
            }
        }


    override suspend fun getFestivalDetailData(
        id: String,
        type: String
    ): Flow<DataResource<DetailFestivalDomainModel>> =
        flow {
            emit(DataResource.Loading())
            try {
                val result = bindDetailDataSource.getDetailFestivalInfo(id = id, type = type)
                emit(DataResource.success(result.toDomain()))
            } catch (e: Exception) {
                emit(DataResource.error(e))
            }
        }

    companion object {
        const val TAG = "DetailPlaceRepository"
    }

}