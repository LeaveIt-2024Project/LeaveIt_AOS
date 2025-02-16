package com.example.leaveit.data.placeview.detailplace

import com.example.leaveit.domain.model.DetailPlaceDomainModel

interface DetailPlaceDomainMapper {

    fun toDomain() : DetailPlaceDomainModel

    fun isEmptyOrNull(value : String) : Boolean

    fun translateUseTimeFormat(value: String) : String
}