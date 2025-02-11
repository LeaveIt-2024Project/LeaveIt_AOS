package com.example.leaveit.domain.model

import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import com.example.leaveit.presentation.placeview.place.showplaceview.DTO.CategoryDto
import com.example.leaveit.presentation.placeview.place.showplaceview.ShowPlaceModelMapper

data class PlaceDomainModel(
    val addr : String,
    val areacode : String,
    val cat : String,
    val contentId : String,
    val contenttypeid : String,
    val image : String,
    val mapx: String,
    val mapy : String,
    val tel : String,
    val title : String
) : ShowPlaceModelMapper {
    override suspend fun toSearchModel(): CategoryDto {
       return CategoryDto(
            title = title,
           image = image,
           contentTypeId = contenttypeid
       )
    }

    override suspend fun toPlaceModel(): SelectRegionModel {
        return SelectRegionModel(
            address = addr,
            contentId = contentId,
            contentTypeId = contenttypeid,
            title = title,
            image = image,
            areaCode = areacode,
            mapx = mapx,
            mapy = mapy
        )
    }
}

data class PlaceDomainListModel(
    val placeDomainEntity : List<PlaceDomainModel>
)