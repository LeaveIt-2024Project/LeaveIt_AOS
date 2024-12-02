package com.example.leaveit.presentation.placeview.selectregionview.data

interface SelectRegionModelMapper {
    fun listToEntity(temp : SelectRegionModelList): List<SelectRegionModel>
}