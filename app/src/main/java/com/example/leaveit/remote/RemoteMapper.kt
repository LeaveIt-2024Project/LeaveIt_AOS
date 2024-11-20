package com.example.leaveit.remote

internal interface RemoteMapper<PlaceDataModel>{
    fun toDomain() : PlaceDataModel
}