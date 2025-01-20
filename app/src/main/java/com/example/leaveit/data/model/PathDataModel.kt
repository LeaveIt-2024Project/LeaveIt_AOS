package com.example.leaveit.data.model

import com.example.leaveit.data.navigate.PathDataMapperInterface
import com.example.leaveit.domain.model.PathDomainModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class PathDataModel(
    val path: List<path>,
    val distance: Int?,
    val duration: Int?,
    val departureTime: String?
) : PathDataMapperInterface {
    override fun toDomain(): PathDomainModel {
        val departureTime = mappingDepartureTime()
        val distance = mappingDistance()
        val path = mappingPath()
        return PathDomainModel(
            distance = distance,
            path = path,
            departureTime = departureTime
        )
    }

    override fun mappingDepartureTime(): String {
        val calendar = Calendar.getInstance() // 현재 시간 가져오기
        val originalDay = calendar.get(Calendar.DAY_OF_YEAR) // 현재 날짜 저장

        calendar.timeInMillis += this.duration!! // 밀리초 추가

        val newDay = calendar.get(Calendar.DAY_OF_YEAR) // 변경된 날짜 가져오기
        val hours = calendar.get(Calendar.HOUR_OF_DAY) // 24시간 형식의 시간
        val minutes = calendar.get(Calendar.MINUTE) // 분
        val dateFormat = SimpleDateFormat("MM월 dd일", Locale.getDefault()) // 날짜 포맷

        return if (newDay == originalDay) {
            // 날짜가 바뀌지 않음 -> 시간만 표시
            String.format("%02d시 %02d분", hours, minutes)
        } else {
            // 날짜가 바뀜 -> 날짜 + 시간 표시
            String.format("%s %02d시 %02d분", dateFormat.format(calendar.time), hours, minutes)
        }

    }

    override fun mappingDistance(): Int {
        var distance = 0

        val distanceValue = this.distance?.div(1000)?.times(100)?.div(100) ?: 0

        if (distanceValue == 0) {
            return 0
        } else {
            return distanceValue
        }
    }

    override fun mappingPath(): List<com.example.leaveit.domain.model.path> {
        return this.path.map {
            com.example.leaveit.domain.model.path(
                longitutde = it.longitutde,
                latitude = it.latitude
            )
        }
    }
}

data class path(
    val longitutde: String,
    val latitude: String
)
