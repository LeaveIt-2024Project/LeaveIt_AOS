package com.example.leaveit.domain.model

import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModelList
import com.example.leaveit.presentation.placeview.place.showplaceview.ShowPlaceModelMapper
import kotlinx.coroutines.Deferred

data class PlaceDomainModel(
    val addr : String,
    val areacode : Int,
    val cat1 : String,
    val cat2 : String,
    val cat3 : String,
    val contentId : String,
    val contenttypeid : String,
    val image : String,
    val image2 : String,
    val mapx: String,
    val mapy : String,
    val sigungucode : Int,
    val tel : String,
    val title : String
)

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
}