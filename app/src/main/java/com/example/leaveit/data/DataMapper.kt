package com.example.leaveit.data

internal interface DataMapper <PlaceDataModel>{
    fun toDomain() : PlaceDataModel
}