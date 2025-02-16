package com.example.leaveit.data.placeview.hotkeyowrd

import com.example.leaveit.data.model.HotKeyWordDataModel

interface HotKeyWordPlaceDataSource {
    suspend fun getHotKeyWordPlace() : HotKeyWordDataModel
}