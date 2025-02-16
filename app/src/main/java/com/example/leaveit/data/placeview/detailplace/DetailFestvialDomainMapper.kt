package com.example.leaveit.data.placeview.detailplace

import com.example.leaveit.domain.model.DetailFestivalDomainModel

interface DetailFestvialDomainMapper {
    fun toDomain() : DetailFestivalDomainModel

    fun isEmptyOrNull(value: String?) : Boolean

    fun translateUseTimeFormat(value: String?) : String
}