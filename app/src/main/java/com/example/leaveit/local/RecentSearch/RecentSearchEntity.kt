package com.example.leaveit.local.RecentSearch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class RecentSearchEntity(
    @PrimaryKey val uid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "recent_data") val recentData: String?,
    @ColumnInfo(name = "searchIndex") var searchIndex: Int?
)

