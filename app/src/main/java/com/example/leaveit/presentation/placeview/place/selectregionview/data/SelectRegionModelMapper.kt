package com.example.leaveit.presentation.placeview.place.selectregionview.data

interface SelectRegionModelMapper {
    fun listToEntity(temp : SelectRegionModelList): List<SelectRegionModel>
}