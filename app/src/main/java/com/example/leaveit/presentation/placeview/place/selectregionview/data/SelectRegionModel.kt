package com.example.leaveit.presentation.placeview.place.selectregionview.data

data class SelectRegionModel(
    val contentId : String,
    val contentTypeId : String,
    val title : String,
    val image : String,
    val areaCode : Int
)


data class SelectRegionModelList(
    val placeViewEntity : List<SelectRegionModel>
) : SelectRegionModelMapper {
    override fun listToEntity(temp : SelectRegionModelList): List<SelectRegionModel> {
       val result = temp

        return result.placeViewEntity
    }
}