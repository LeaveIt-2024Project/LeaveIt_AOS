package com.example.leaveit.remote.placeview

import com.example.leaveit.data.placeview.PlaceViewDataSourceInterface
import javax.inject.Inject

class PlaceViewDataSourceImpl @Inject constructor() : PlaceViewDataSourceInterface {

    //관광지 정보 호출 데이터소스 인터페이스 구현체


    companion object{
        val TAG = "PlaceViewDataSourceImpl"
    }

    override suspend fun getPlaceData() {
        TODO("Not yet implemented")
    }

}