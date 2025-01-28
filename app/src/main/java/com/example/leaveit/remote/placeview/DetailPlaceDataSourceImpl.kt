package com.example.leaveit.remote.placeview

import com.example.leaveit.data.placeview.detailplace.DetailPlaceDataResourceDataModel
import com.example.leaveit.data.placeview.detailplace.DetailPlaceDataSourceInterface
import com.example.leaveit.remote.api.review.DetailPlaceApi
import javax.inject.Inject

class DetailPlaceDataSourceImpl @Inject constructor(
    private val service: DetailPlaceApi
) : DetailPlaceDataSourceInterface {
    override suspend fun getDetailPlaceInfo(value: String): DetailPlaceDataResourceDataModel {
        val data = service.getDetailPlaceInfo(contentId = value).response.body.detailPlaceItem.detailPlaceEntityList[0].toDataSourceModel()
        return data
    }

}