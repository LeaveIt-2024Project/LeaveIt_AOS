package com.example.leaveit.remote.hotkeyword_place

import com.example.leaveit.data.model.HotKeyWordData
import com.example.leaveit.data.model.HotKeyWordDataModel
import com.example.leaveit.data.placeview.hotkeyowrd.HotKeyWordPlaceDataSource
import com.example.leaveit.remote.api.place.PlaceApi
import javax.inject.Inject

class HotKeyWordPlaceDataSourceImpl @Inject constructor(
    private val service : PlaceApi
) : HotKeyWordPlaceDataSource {
    override suspend fun getHotKeyWordPlace(): HotKeyWordDataModel {
        val data = service.getHotKeyWordPlace()

        val filledData = (data + List(10) { "기본 제목" }).take(10)

        val result = HotKeyWordDataModel(
            filledData.map {
                HotKeyWordData(
                    title = it,
                    number = ""
                )
            }
        )
        return result
    }

}