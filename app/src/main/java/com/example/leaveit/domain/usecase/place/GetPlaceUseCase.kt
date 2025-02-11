package com.example.leaveit.domain.usecase.place

import androidx.paging.PagingData
import androidx.paging.map
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPlaceUseCase @Inject constructor(
    private val bindPlaceViewRepositoryImpl: GetPlaceRepositoryInterface
) : GetPlaceUseCaseInterface {

    // 관굉지 정보 호출 유스케이스 정의

    override suspend fun getSortByRegionUseCase(
        contentTypedId: String,
        areaCode: String
    ): Flow<PagingData<SelectRegionModel>> {
        return bindPlaceViewRepositoryImpl.getSortByRegionPlaceData(
            contentTypeId = contentTypedId,
            areadCode = areaCode
        ).map { paggingData ->
            paggingData.map {
                it.toPlaceModel()
            }
        }

    }

    override suspend fun getAllPlaceUseCase(contentTypedId: String): Flow<PagingData<SelectRegionModel>> {
        return bindPlaceViewRepositoryImpl.getAllPlaceData(contentTypedId).map { pagingData ->
            pagingData.map {
                it.toPlaceModel()
            }
        }
    }

    companion object {
        val TAG = "GetPlaceUseCase"
    }

}