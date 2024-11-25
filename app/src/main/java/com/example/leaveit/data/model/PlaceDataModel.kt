package com.example.leaveit.data.model

import com.example.leaveit.data.DataMapper
import com.example.leaveit.domain.model.PlaceDomainModel

data class PlaceDataModel(
    val addr1 : String,
    val areacode : String,
    val cat1 : String,
    val cat2 : String,
    val cat3 : String,
    val contentId : Int,
    val contenttypeid : Int,
    val firstimage : String,
    val firstimage2 : String,
    val mapx: String,
    val mapy : String,
    val sigungucode : Int,
    val tel : String,
    val title : String
) : DataMapper<PlaceDomainModel>{
    override fun toDomain(): PlaceDomainModel {
        return PlaceDomainModel(
            addr = addr1,
            areacode = areacode,
            cat1 = cat1,
            cat2 = cat2,
            cat3 = cat3,
            contentId = contentId,
            contenttypeid = contenttypeid,
            image = firstimage.toInt(),
            image2 = firstimage2.toInt(),
            mapx = mapx,
            mapy = mapy,
            sigungucode = sigungucode,
            tel = tel,
            title = title
        )
    }
}
