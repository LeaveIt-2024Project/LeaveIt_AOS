package com.example.leaveit.domain.model

data class RestrantListDomainModel(
    val list : List<RestrauntDomainModel>
)

data class RestrauntDomainModel(
    val title : String,
    val contentid : String,
    val image : String?,
    val longitutde : String,
    val langtitude : String
)
