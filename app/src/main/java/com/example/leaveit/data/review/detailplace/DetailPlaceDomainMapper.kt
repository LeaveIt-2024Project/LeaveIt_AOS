package com.example.leaveit.data.review.detailplace

import com.example.leaveit.domain.model.DetailPlaceDomainModel

interface DetailPlaceDomainMapper {

    fun toDomain() : DetailPlaceDomainModel

    fun isEmptyOrNull(value : String) : Boolean

    fun translateUseTimeFormat(value: String) : List<String>

    fun toInt() : Int
}