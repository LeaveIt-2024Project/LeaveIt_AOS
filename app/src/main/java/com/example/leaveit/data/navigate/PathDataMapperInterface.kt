package com.example.leaveit.data.navigate

import com.example.leaveit.domain.model.PathDomainModel

interface PathDataMapperInterface {
    fun toDomain() : PathDomainModel
}