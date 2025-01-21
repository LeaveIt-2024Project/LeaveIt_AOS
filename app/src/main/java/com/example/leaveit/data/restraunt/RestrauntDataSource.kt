package com.example.leaveit.data.restraunt

import com.example.leaveit.data.model.RestrauntDataListModel

interface RestrauntDataSource {

    suspend fun getRestrauntData(longitude : String, latitude : String) : RestrauntDataListModel
}