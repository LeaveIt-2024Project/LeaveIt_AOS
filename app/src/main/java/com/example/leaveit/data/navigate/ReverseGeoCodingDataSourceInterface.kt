package com.example.leaveit.data.navigate

import com.example.leaveit.remote.entity.Result

interface ReverseGeoCodingDataSourceInterface {
    suspend fun getTranslateData(value : String) : Result
}