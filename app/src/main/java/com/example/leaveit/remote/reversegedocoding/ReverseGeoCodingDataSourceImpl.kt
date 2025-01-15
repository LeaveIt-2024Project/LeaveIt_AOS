package com.example.leaveit.remote.reversegedocoding

import com.example.leaveit.data.navigate.ReverseGeoCodingDataSourceInterface
import com.example.leaveit.remote.api.navigate.ReverseGeoCodingAPI
import com.example.leaveit.remote.entity.Result
import javax.inject.Inject

class ReverseGeoCodingDataSourceImpl @Inject constructor(
    private val service : ReverseGeoCodingAPI
) : ReverseGeoCodingDataSourceInterface {
    override suspend fun getTranslateData(value : String): Result {
        val result = service.getAllRegionPlace(value).results[0]
        return result
    }
}