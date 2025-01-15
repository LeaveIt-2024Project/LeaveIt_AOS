package com.example.leaveit.data.model

import com.example.leaveit.data.navigate.PathDataMapperInterface
import com.example.leaveit.domain.model.PathDomainModel

data class PathDataModel(
    val path : List<path>,
    val distance : Int?,
    val departureTime : String?
) : PathDataMapperInterface {
    override fun toDomain(): PathDomainModel {
        var distance = 0

        val distanceValue  = this.distance?.div(1000)?.times(100)?.div(100) ?: 0

        if(distanceValue == 0){
            distance = 0
        }else{
            distance = distanceValue
        }

        val path = this.path.map {
            com.example.leaveit.domain.model.path(
                longitutde = it.longitutde,
                latitude = it.latitude
            )
        }

        // "2025-01-15T18:22:48" 형식을 분리
        val datePart = this.departureTime?.substringBefore("T") // "2025-01-15"
        val timePart = this.departureTime?.substringAfter("T")?.substring(0, 5) // "18:22"

        // 날짜 부분에서 월/일 추출
        val month = "${datePart?.substring(5, 7)}" // "01"
        val day = "${datePart?.substring(8, 10)}"  // "15"

        val departureTime = "$month/$day $timePart"
       return PathDomainModel(
           distance = distance,
           path = path,
           departureTime = departureTime
       )
    }
}

data class path(
    val longitutde : String,
    val latitude : String
)
