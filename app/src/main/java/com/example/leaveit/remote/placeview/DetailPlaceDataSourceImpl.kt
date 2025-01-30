package com.example.leaveit.remote.placeview

import com.example.leaveit.data.model.DetailCultureDataModel
import com.example.leaveit.data.model.DetailFestivalDataModel
import com.example.leaveit.data.model.DetailPlaceDataDataModel
import com.example.leaveit.data.placeview.detailplace.DetailPlaceDataSourceInterface
import com.example.leaveit.remote.api.place.DetailPlaceApi
import javax.inject.Inject

class DetailPlaceDataSourceImpl @Inject constructor(
    private val service: DetailPlaceApi
) : DetailPlaceDataSourceInterface {
    override suspend fun getDetailPlaceInfo(id: String,type : String): DetailPlaceDataDataModel {
      return service.getDetailPlaceInfo(contentId = id, contentTypedId = type).response.body.detailPlaceItem.detailPlaceEntityList[0].toPlaceDataSourceModel()
    }

    override suspend fun getDetailCulturePlaceInfo(
        id: String,
        type: String
    ): DetailCultureDataModel {
       return service.getDetailCultureInfo(contentId = id, contentTypedId = type).response.body.detailCultureItem.detailCultureEntityList[0].toCultureDataSourceModel()
    }

    override suspend fun getDetailFestivalInfo(id: String, type: String): DetailFestivalDataModel {
        return service.getDetailFestivalInfo(contentId = id, contentTypedId = type).response.body.detailFestivalItem.detailFestivalEntityList[0].toFestivalDataSourceModel()
    }

}