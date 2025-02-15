package com.example.leaveit.remote.hotkeyword_place

import com.example.leaveit.data.placeview.hotkeyowrd.HotKeyWordPlaceDataSource
import com.example.leaveit.remote.api.place.PlaceApi
import javax.inject.Inject

class HotKeyWordPlaceDataSourceImpl @Inject constructor(
    private val service : PlaceApi
) : HotKeyWordPlaceDataSource {
    override suspend fun getHotKeyWordPlace(): List<String> {
        return service.getHotKeyWordPlace()
    }

}