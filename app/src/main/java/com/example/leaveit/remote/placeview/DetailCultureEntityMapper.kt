package com.example.leaveit.remote.placeview

import com.example.leaveit.data.model.DetailCultureDataModel

interface DetailCultureEntityMapper {
    fun toCultureDataSourceModel() : DetailCultureDataModel
}