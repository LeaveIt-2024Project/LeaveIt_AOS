package com.example.leaveit.remote.detail_restraunt

import com.example.leaveit.data.detail_restraunt.DetailRestrauntDataSource
import com.example.leaveit.data.model.DetailRestrauntDataModel
import com.example.leaveit.remote.api.Restraunt.DetailRestrauntAPI
import javax.inject.Inject

class DetailRestrauntDataSourceImpl @Inject constructor(
    private val service : DetailRestrauntAPI
) : DetailRestrauntDataSource{
    override suspend fun getDetailRestrauntData(value : String): DetailRestrauntDataModel {
        return service.getDetailRestrauntData(contentId = value).response.body.items.item[0].toData()
    }
}