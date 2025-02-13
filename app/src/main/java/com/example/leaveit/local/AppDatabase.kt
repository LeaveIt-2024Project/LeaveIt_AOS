package com.example.leaveit.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.leaveit.local.PlaceSearch.PlaceSearchEntity
import com.example.leaveit.local.RecentSearch.RecentSearchDao
import com.example.leaveit.local.RecentSearch.RecentSearchEntity

@Database(entities = [RecentSearchEntity::class,PlaceSearchEntity::class], version = 1)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appData(): RecentSearchDao
}