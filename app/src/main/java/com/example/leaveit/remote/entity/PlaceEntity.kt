package com.example.leaveit.remote.entity

data class PlaceEntity(
    val response : List<PlaceItem>
)

data class PlaceItem(
    val addr1: String,
    val areaCode: String,
    val cat: String,
    val contentId: String,
    val firstImage: String,
    val mapX: String,
    val mapY: String,
    val tel: String,
    val title: String
)