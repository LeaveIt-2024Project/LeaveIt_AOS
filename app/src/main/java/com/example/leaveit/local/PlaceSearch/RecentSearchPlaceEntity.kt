package com.example.leaveit.local.PlaceSearch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class RecentSearchPlaceEntity(
    @PrimaryKey val uid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "searchContentId") var searchContentId : String,
    @ColumnInfo(name = "contentTypeId") var contentTypeId: String,
    @ColumnInfo(name = "mapx") var mapx: String,
    @ColumnInfo(name = "mapy") var mapy: String,
    @ColumnInfo(name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "addr") var addr: String,

    )
