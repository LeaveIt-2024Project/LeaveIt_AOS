package com.example.leaveit.data.placeview.detailplace

import com.example.leaveit.domain.model.DetailCultureDomainModel

interface DetailCultureDomainMapper {
    fun toDomain() : DetailCultureDomainModel

    fun isEmptyOrNull(value: String?) : Boolean

    fun translateUseTimeFormat(value: String?) : String
}