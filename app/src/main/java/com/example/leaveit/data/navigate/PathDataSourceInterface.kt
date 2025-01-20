package com.example.leaveit.data.navigate

import com.example.leaveit.data.model.PathDataModel

interface PathDataSourceInterface {
    suspend fun getPath(start : String, goal : String) : PathDataModel
}