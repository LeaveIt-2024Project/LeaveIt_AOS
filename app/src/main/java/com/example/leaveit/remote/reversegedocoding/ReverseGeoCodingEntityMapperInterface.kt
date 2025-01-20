package com.example.leaveit.remote.reversegedocoding

import com.example.leaveit.data.model.ReverseGeoCodingDataModel

interface ReverseGeoCodingEntityMapperInterface {
    fun toDataModel() : ReverseGeoCodingDataModel
}