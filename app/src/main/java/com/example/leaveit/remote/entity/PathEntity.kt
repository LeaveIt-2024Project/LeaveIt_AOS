package com.example.leaveit.remote.entity

import android.util.Log
import com.example.leaveit.data.model.PathDataModel
import com.example.leaveit.data.model.path
import com.example.leaveit.remote.path.PathDataEntityMapperInterface
import com.google.gson.annotations.SerializedName

data class responsePath(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("currentDateTime") val currentDateTime: String,
    @SerializedName("route") val route: pathRoute,
)

data class pathRoute(
    @SerializedName("traoptimal") val pathTraoptimal: List<pathTraoptimal>
)

data class pathTraoptimal(
    @SerializedName("guide") val guide: List<guide>,
    @SerializedName("path") val path: List<List<Double>>,
    @SerializedName("summary") val summary: summary,
) : PathDataEntityMapperInterface {
    // path 데이터를 변환

    override fun toDataModel(): PathDataModel {
        val convertedPaths = path.map { coordinates ->
            path(
                longitutde = coordinates[0].toString(), // 경도
                latitude = coordinates[1].toString()   // 위도
            )
        }
        Log.d("PathEntity",convertedPaths[0].latitude)

        return PathDataModel(
            distance = this.summary.distance,
            departureTime = this.summary.departureTime,
            path = convertedPaths,
            duration = this.summary.duration
        )
    }
}

data class guide(
    @SerializedName("distance") val distance: Int,
    @SerializedName("duration") val duration: Int,
    @SerializedName("instructions") val instructions: String,
    @SerializedName("pointIndex") val pointIndex: Int,
    @SerializedName("type") val type: Int
)

data class path(
    val longitutde : Double,
    val latitude : Double
)

data class summary(
    @SerializedName("bbox") val bbox: List<List<Double>>,
    @SerializedName("departureTime") val departureTime: String,
    @SerializedName("distance") val distance: Int,
    @SerializedName("duration") val duration: Int,
    @SerializedName("fuelPrice") val fuelPrice: Int,
    @SerializedName("goal") val goal: goal,
    @SerializedName("start") val start: start,
    @SerializedName("taxiFare") val taxiFare: Int,
    @SerializedName("tollFare") val tollFare: Int,
)

data class goal(
    @SerializedName("dir") val dir : String,
    @SerializedName("location") val location :List<Double>
)

data class start(
    @SerializedName("location") val location : List<Double>
)
