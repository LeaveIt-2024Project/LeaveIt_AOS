package com.example.leaveit.data.navigate

import com.example.leaveit.domain.model.PathDomainModel

interface PathDataMapperInterface {
    fun toDomain() : PathDomainModel
    fun mappingDepartureTime() : String

    fun mappingDistance() : Int

    fun mappingPath() : List<com.example.leaveit.domain.model.path>
}