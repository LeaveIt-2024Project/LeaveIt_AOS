package com.example.leaveit.data.detail_restraunt

import com.example.leaveit.domain.model.DetailRestrauntDomainModel

interface DetailRestrauntDataMapper {
    fun toDomain() : DetailRestrauntDomainModel
}