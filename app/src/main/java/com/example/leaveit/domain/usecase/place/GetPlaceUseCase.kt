package com.example.leaveit.domain.usecase.place

import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModelList
import javax.inject.Inject

class GetPlaceUseCase @Inject constructor() : GetPlaceUseCaseInterface {

    // 관굉지 정보 호출 유스케이스 정의

    override suspend fun getSortByRegionUseCase(
        contentTypedId: String,
        areaCode: String
    ): SelectRegionModelList {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPlaceUseCase(contentTypedId: String): SelectRegionModelList {
        TODO("Not yet implemented")
    }

    companion object {
        val TAG = "GetPlaceUseCase"
    }

}