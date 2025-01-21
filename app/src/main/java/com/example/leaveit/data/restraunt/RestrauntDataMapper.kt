package com.example.leaveit.data.restraunt

import com.example.leaveit.domain.model.RestrantListDomainModel

interface RestrauntDataMapper {
    fun toDomain() : RestrantListDomainModel
}