package com.example.leaveit.domain.model

data class PathDomainModel(
    val path : List<path>,
    val distance : Int,
    val departureTime : String
)

data class path(
    val longitutde : String,
    val latitude : String
)