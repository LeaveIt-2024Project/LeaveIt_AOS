package com.example.leaveit.data.placeview.hotkeyowrd

import com.example.leaveit.domain.model.HotKeyWordDomainModel

interface HotKeyWordPalceMapper {
    fun toDomain() : HotKeyWordDomainModel
}