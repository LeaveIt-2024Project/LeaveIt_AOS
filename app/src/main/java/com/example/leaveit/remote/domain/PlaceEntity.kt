package com.example.leaveit.remote.domain

import android.util.Log
import com.example.leaveit.data.model.PlaceDataModel
import com.example.leaveit.remote.RemoteMapper

data class PlaceEntitiy(
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
    val sigungucode : String,
    val tel : String,
    val title : String
) : RemoteMapper<PlaceDataModel>{
    override fun toDomain(): PlaceDataModel {
        return PlaceDataModel(
            addr1 = addr1,
            areacode = areacode,
            cat1 = cat1,
            cat2 = cat2,
            cat3 = cat3,
            contentId = stringToInt(contentId),
            contenttypeid = stringToInt(contenttypeid),
            firstimage = firstimage,
            firstimage2 = firstimage2,
            mapx = mapx,
            mapy = mapy,
            sigungucode = stringToInt(sigungucode),
            tel = tel,
            title = title
        )
    }


    fun stringToInt(temp : String) : Int{
        return temp.toInt()
    }
}

