package com.example.leaveit.remote.detail_restraunt

import com.example.leaveit.data.model.DetailRestrauntDataModel

interface DetailRestrauntEntityMapper {
    fun toData() : DetailRestrauntDataModel
}