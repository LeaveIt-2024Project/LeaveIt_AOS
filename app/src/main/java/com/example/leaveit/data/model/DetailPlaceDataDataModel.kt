package com.example.leaveit.data.model

import com.example.leaveit.data.placeview.detailplace.DetailPlaceDomainMapper
import com.example.leaveit.domain.model.DetailPlaceDomainModel

data class DetailPlaceDataDataModel(
    val contentId: String,
    val contenttypeid: String,
    val infocenter: String?,
    val restdate: String?,
    val chkpet: String?,
    val chkbabycarriage: String?,
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
            chkbabycarriage = isEmptyOrNull(chkbabycarriage!!),
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

    override fun translateUseTimeFormat(value: String): String {
        return value.replace("<br>", "").replace("/", "\n").replace("-", "")
    }
}
