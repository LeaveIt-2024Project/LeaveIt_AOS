package com.example.leaveit.domain.model

import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModelList
import com.example.leaveit.presentation.placeview.place.showplaceview.ShowPlaceModelMapper
import kotlinx.coroutines.Deferred

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
    override suspend fun toPlaceModel(temp: Deferred<PlaceDomainListModel>): SelectRegionModelList {
        TODO("Not yet implemented")
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
): ShowPlaceModelMapper {
    override suspend fun toPlaceModel(temp : Deferred<PlaceDomainListModel>): SelectRegionModelList {
        val result = temp.await().placeDomainEntity.map {entity ->
            SelectRegionModel(
                contentId = entity.contentId,
                contentTypeId = entity.contenttypeid,
                title = entity.title,
                image= entity.image,
                areaCode = entity.areacode,
                mapy = "123",
                mapx = "144",
                address = entity.addr
            )
        }

        return SelectRegionModelList(placeViewEntity = result)
    }

    override suspend fun toPlaceModel(): SelectRegionModel {
        TODO("Not yet implemented")
    }
}