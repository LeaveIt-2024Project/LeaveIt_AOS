package com.example.leaveit.data.model

import com.example.leaveit.data.navigate.ReverseGeoCodingDataMapperInterface
import com.example.leaveit.domain.model.ReverseGeoCodingDomainModel

data class ReverseGeoCodingDataModel(
    val address : String
) : ReverseGeoCodingDataMapperInterface {
    override fun toDomain(): ReverseGeoCodingDomainModel {
        return ReverseGeoCodingDomainModel(
            address = address
        )
    }
}
