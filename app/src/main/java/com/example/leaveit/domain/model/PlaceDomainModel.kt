package com.example.leaveit.domain.model

import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModel
import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModelList
import com.example.leaveit.presentation.placeview.showplaceview.ShowPlaceModelMapper
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
                areaCode = entity.areacode
            )
        }

        return SelectRegionModelList(placeViewEntity = result)
    }
}