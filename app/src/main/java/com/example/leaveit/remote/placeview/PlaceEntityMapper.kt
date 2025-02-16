package com.example.leaveit.remote.placeview

import com.example.leaveit.data.model.PlaceDataModel

interface PlaceEntityMapper {
    suspend fun toData(): PlaceDataModel
}