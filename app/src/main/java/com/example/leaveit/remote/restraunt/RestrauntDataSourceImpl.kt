package com.example.leaveit.remote.restraunt

import android.util.Log
import com.example.leaveit.data.model.RestrauntDataListModel
import com.example.leaveit.data.restraunt.RestrauntDataSource
import com.example.leaveit.remote.api.Restraunt.RestrauntAPI
import javax.inject.Inject

class RestrauntDataSourceImpl @Inject constructor(
    private val service: RestrauntAPI
) : RestrauntDataSource {
    override suspend fun getRestrauntData(
        longitude: String,
        latitude: String
    ): RestrauntDataListModel {
        val temp = service.getAllRegionPlace(
            longitutde = longitude,
            langitutde = latitude
        )

        if (temp.restrauntResponse == null) {
            Log.e("ERROR", "restrauntResponse가 null입니다.")
            return RestrauntDataListModel(emptyList()) // 기본값 반환
        }

        if (temp.restrauntResponse.restrauntBody == null) {
            Log.e("ERROR", "restrauntBody가 null입니다.")
            return RestrauntDataListModel(emptyList()) // 기본값 반환
        }

        return temp.restrauntResponse.restrauntBody.items.toData()
    }
}