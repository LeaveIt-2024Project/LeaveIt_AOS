package com.example.leaveit.remote.restraunt

import com.example.leaveit.data.model.RestrauntDataListModel

interface RestrauntEntityMapper {
    fun toData() : RestrauntDataListModel
}