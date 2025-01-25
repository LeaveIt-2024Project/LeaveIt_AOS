package com.example.leaveit.data.detail_restraunt

import com.example.leaveit.data.model.DetailRestrauntDataModel

interface DetailRestrauntDataSource {
    suspend fun getDetailRestrauntData(value : String) : DetailRestrauntDataModel
}