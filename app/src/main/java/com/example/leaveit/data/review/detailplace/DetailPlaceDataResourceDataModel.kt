package com.example.leaveit.data.review.detailplace

import com.example.leaveit.domain.model.DetailPlaceDomainModel

data class DetailPlaceDataResourceDataModel(
    val contentId: String,
    val contenttypeid: String,
    val infocenter: String?,
    val restdate: String?,
    val chkpet: String?,
    val parking: String?,
    val usetime: String?,
) : DetailPlaceDomainMapper {
    override fun toDomain(): DetailPlaceDomainModel {
        return DetailPlaceDomainModel(
            contentId = contentId,
            contenttypeid = contenttypeid,
            infocenter = infocenter!!,
            restdate = restdate!!,
            chkpet = isEmptyOrNull(chkpet!!),
            parking = isEmptyOrNull(parking!!),
            usetime = translateUseTimeFormat(usetime!!)

        )
    }

    override fun isEmptyOrNull(value: String): Boolean {
        var result = false
        if (value.isEmpty() || value == "없음" || value == "불가" || value == "0") {
            result = false
        } else {
            result = true
        }
        return result
    }

    override fun translateUseTimeFormat(value: String): List<String> {
        var result: MutableList<String> = mutableListOf()

        if(isEmptyOrNull(value)){
            val temp = value.split("<br>")
            if (temp.size == 1) {
                result.add(temp[0])
            } else if (temp.size > 1) {
                result.add(temp[0])
                result.add(temp[1])
            } else {
                result.add(0, "")
            }
        }else{
            result.add(value)
        }

        return result
    }

    override fun toInt(): Int {
        TODO("Not yet implemented")
    }
}
