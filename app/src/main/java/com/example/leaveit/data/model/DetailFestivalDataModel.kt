package com.example.leaveit.data.model

import com.example.leaveit.data.placeview.detailplace.DetailFestvialDomainMapper
import com.example.leaveit.domain.model.DetailFestivalDomainModel

data class DetailFestivalDataModel(
    val contentId: String,
    val contenttypeid: String,
    val eventenddate: String?,
    val agelimit: String?,
    val usetimefestival: String?,
    val spendtimefestival: String?,
    val bookingplace: String?,
    val sponsor2tel: String?,
    val playTime: String?
) : DetailFestvialDomainMapper {
    override fun toDomain(): DetailFestivalDomainModel {
        return DetailFestivalDomainModel(
            contentId = this.contentId,
            contenttypeid = this.contenttypeid,
            playTime = translateUseTimeFormat(this.playTime),
            usetimefestival = translateUseTimeFormat(this.usetimefestival),
            eventenddate = this.eventenddate,
            agelimit = this.agelimit,
            spendtimefestival = this.spendtimefestival,
            bookingplace = this.bookingplace,
            sponsor2tel = this.sponsor2tel
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