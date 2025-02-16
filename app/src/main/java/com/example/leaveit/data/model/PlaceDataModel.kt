package com.example.leaveit.data.model

import com.example.leaveit.data.placeview.PlaceViewDataMapperInterface
import com.example.leaveit.domain.model.PlaceDomainListModel
import com.example.leaveit.domain.model.PlaceDomainModel
import kotlinx.coroutines.Deferred

data class PlaceDataModel(
    val addr1: String,
    val areacode: String,
    val cat: String,
    val contentId: String,
    val contenttypeid: String,
    val firstimage: String,
    val mapx: String,
    val mapy: String,
    val tel: String,
    val title: String
) : PlaceViewDataMapperInterface {
    override suspend fun toDomain(temp: Deferred<PlaceViewDataModelList>): PlaceDomainListModel {
        TODO("Not yet implemented")
    }

    override suspend fun toDomain(): PlaceDomainModel {
        return PlaceDomainModel(
            addr = addr1,
            areacode = areacode,
            cat = cat,
            contentId = contentId,
            contenttypeid = contenttypeid,
            image = firstimage,
            mapx = mapx,
            mapy = mapy,
            tel = tel,
            title = title
        )
    }

}


data class PlaceViewDataModelList(
    val placeDataModel: List<PlaceDataModel>
) : PlaceViewDataMapperInterface {
    override suspend fun toDomain(temp: Deferred<PlaceViewDataModelList>): PlaceDomainListModel {
        val result = temp.await().placeDataModel.map { entity ->
            PlaceDomainModel(
                addr = entity.addr1,
                areacode = entity.areacode,
                cat = entity.cat,
                contentId = entity.contentId,
                contenttypeid = entity.contenttypeid,
                image = entity.firstimage,
                mapx = entity.mapx,
                mapy = entity.mapy,
                tel = entity.tel,
                title = entity.title
            )
        }
        return PlaceDomainListModel(placeDomainEntity = result)
    }

    override suspend fun toDomain(): PlaceDomainModel {
        TODO("Not yet implemented")
    }
}
