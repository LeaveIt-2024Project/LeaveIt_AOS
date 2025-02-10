package com.example.leaveit.data.placeview

import com.example.leaveit.data.model.PlaceViewDataModelList


interface PlaceViewDataSourceInterface {
    // 관광지 데이터소스 인터페이스 정의
    suspend fun getAllPlaceData(Category : String, PagingNumber : Int) : PlaceViewDataModelList


}