package com.example.leaveit.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [RecentSearchEntity::class], version = 1)
@TypeConverters(RecentSearchDataConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appData(): RecentSearchDao
}