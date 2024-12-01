package com.example.leaveit.data.model

import com.example.leaveit.data.placeview.PlaceViewDataMapperInterface
import com.example.leaveit.domain.model.PlaceDomainListModel
import com.example.leaveit.domain.model.PlaceDomainModel
import kotlinx.coroutines.Deferred

data class PlaceDataModel(
    val addr1 : String,
    val areacode : String,
    val cat1 : String,
    val cat2 : String,
    val cat3 : String,
    val contentId : String,
    val contenttypeid : String,
    val firstimage : String,
    val firstimage2 : String,
    val mapx: String,
    val mapy : String,
    val sigungucode : Int,
    val tel : String,
    val title : String
)



data class PlaceViewDataModelList(
    val placeDataModel : List<PlaceDataModel>
): PlaceViewDataMapperInterface {
    override suspend fun toDomain(temp: Deferred<PlaceViewDataModelList>): PlaceDomainListModel {
        val result = temp.await().placeDataModel.map {entity ->
                    PlaceDomainModel(
                        addr = entity.addr1,
                        areacode = entity.areacode,
                        cat1 = entity.cat1,
                        cat2 = entity.cat2,
                        cat3 = entity.cat3,
                        contentId = entity.contentId,
                        contenttypeid = entity.contenttypeid,
                        image = entity.firstimage,
                        image2 = entity.firstimage2,
                        mapx = entity.mapx,
                        mapy = entity.mapy,
                        sigungucode = entity.sigungucode,
                        tel = entity.tel,
                        title = entity.title
                    )
             }
        return PlaceDomainListModel(placeDomainEntity = result)
    }
}
