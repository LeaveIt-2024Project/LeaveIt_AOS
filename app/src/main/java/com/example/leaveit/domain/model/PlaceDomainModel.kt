package com.example.leaveit.domain.model

import com.example.leaveit.presentation.placeview.ShowPlaceModel
import com.example.leaveit.presentation.placeview.ShowPlaceModelMapper
import com.example.leaveit.remote.RemoteMapper

data class PlaceDomainModel(
    val addr : String,
    val areacode : String,
    val cat1 : String,
    val cat2 : String,
    val cat3 : String,
    val contentId : Int,
    val contenttypeid : Int,
    val image : Int,
    val image2 : Int,
    val mapx: String,
    val mapy : String,
    val sigungucode : Int,
    val tel : String,
    val title : String
) : ShowPlaceModelMapper<ShowPlaceModel>{
    override fun toPlaceModel(): ShowPlaceModel {
        return ShowPlaceModel(
            contentId = contentId,
            title = title,
            image= image.toString()
        )
    }
}