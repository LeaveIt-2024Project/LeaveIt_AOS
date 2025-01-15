package com.example.leaveit.data.navigate

import com.example.leaveit.domain.model.ReverseGeoCodingDomainModel

interface ReverseGeoCodingDataMapperInterface {
    fun toDomain() : ReverseGeoCodingDomainModel
}