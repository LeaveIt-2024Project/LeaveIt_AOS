package com.example.leaveit.remote.reversegedocoding

import com.example.leaveit.data.navigate.ReverseGeoCodingDataSourceInterface
import com.example.leaveit.remote.api.navigate.NavigateAPI
import com.example.leaveit.remote.entity.Result
import javax.inject.Inject

class ReverseGeoCodingDataSourceImpl @Inject constructor(
    private val service : NavigateAPI
) : ReverseGeoCodingDataSourceInterface {
    override suspend fun getTranslateData(value : String): Result {
        val result = service.getAllRegionPlace(value).results[0]
        return result
    }
}