package com.example.leaveit.presentation.placeview.selectregionview.data

data class SelectRegionModel(
    val contentId : String,
    val title : String,
    val image : String
)

data class SelectRegionModelList(
    val placeViewEntity : List<SelectRegionModel>
) : SelectRegionModelMapper {
    override fun listToEntity(temp : SelectRegionModelList): List<SelectRegionModel> {
       val result = temp

        return result.placeViewEntity
    }
}