package com.example.leaveit.data.placeview.hotkeyowrd

interface HotKeyWordPlaceDataSource {
    suspend fun getHotKeyWordPlace() : List<String>
}