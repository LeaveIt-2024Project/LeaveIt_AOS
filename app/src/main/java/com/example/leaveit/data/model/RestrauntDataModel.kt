package com.example.leaveit.data.model

import com.example.leaveit.data.restraunt.RestrauntDataMapper
import com.example.leaveit.domain.model.RestrantListDomainModel
import com.example.leaveit.domain.model.RestrauntDomainModel

data class RestrauntDataListModel(
    val dataList : List<RestrauntDataModel>
) : RestrauntDataMapper {
    override fun toDomain(): RestrantListDomainModel {
        val temp = this.dataList.map {
            RestrauntDomainModel(
                title = it.title,
                longitutde = it.longitutde,
                langtitude = it.langtitude,
                image = it.image ?: "",
                contentid = it.contentid,
                addr = it.addr
            )
        }

        return RestrantListDomainModel(
            list = temp
        )
    }
}

data class RestrauntDataModel(
    val title : String,
    val contentid : String,
    val addr : String,
    val image : String?,
    val longitutde : String,
    val langtitude : String
)