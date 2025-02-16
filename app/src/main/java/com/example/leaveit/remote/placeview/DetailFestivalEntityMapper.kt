package com.example.leaveit.remote.placeview

import com.example.leaveit.data.model.DetailFestivalDataModel

interface DetailFestivalEntityMapper {

    fun toFestivalDataSourceModel() : DetailFestivalDataModel
}