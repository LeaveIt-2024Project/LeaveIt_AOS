package com.example.leaveit.remote.path

import com.example.leaveit.data.model.PathDataModel
import com.example.leaveit.data.navigate.PathDataSourceInterface
import com.example.leaveit.remote.api.navigate.NavigateAPI
import javax.inject.Inject

class PathDataResourceImpl @Inject constructor(
    private val service : NavigateAPI
) : PathDataSourceInterface {
    override suspend fun getPath(start: String, goal: String): PathDataModel {
        val result = service.getPath(start,goal).route.pathTraoptimal[0].toDataModel()
        return result
    }

    companion object{
        const val TAG = "PathDataResourceImpl"
    }
}