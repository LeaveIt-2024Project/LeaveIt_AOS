package com.example.leaveit.data.model

import com.example.leaveit.data.placeview.detailplace.DetailCultureDomainMapper
import com.example.leaveit.domain.model.DetailCultureDomainModel

data class DetailCultureDataModel(
    val contentid: String,
    val contenttypeid: String,
    val infocenterculture: String?,
    val restdateculture: String?,
    val chkpetculture: String?,
    val chkbabycarriageculture: String?,
    val parkingculture: String?,
    val usetimeculture: String?,
    val usefee: String?
) : DetailCultureDomainMapper {
    override fun toDomain(): DetailCultureDomainModel {
        return DetailCultureDomainModel(
            contentId = this.contentid,
            contenttypeid = this.contenttypeid,
            infocenterculture = this.infocenterculture,
            restdateculture = this.restdateculture,
            chkbabycarriageculture = isEmptyOrNull(this.chkbabycarriageculture),
            chkpetculture = isEmptyOrNull(this.chkpetculture),
            parkingculture = isEmptyOrNull(this.parkingculture),
            usetimeculture = translateUseTimeFormat(this.usetimeculture),
            usefee = translateUseTimeFormat(this.usefee)
        )
    }

    override fun isEmptyOrNull(value: String?): Boolean {
        var result = false
        if (value.isNullOrEmpty() || value == "없음" || value == "불가" || value == "0") {
            result = false
        } else {
            result = true
        }
        return result
    }

    override fun translateUseTimeFormat(value: String?): String {
        if (value.isNullOrEmpty()) {
            return "정보 없음"
        } else {
            return value.replace("<br>", "").replace("/", "\n")
        }
    }
}
