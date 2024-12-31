package com.example.leaveit.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class RecentSearchDataConverter {

    private val gson = Gson()


    // RecentSearchEntity → String (저장 시)
    @TypeConverter
    fun fromRecentSearchEntity(value: RecentSearchEntity?): String {
        return gson.toJson(value)
    }

    // String → RecentSearchEntity (가져올 때)
    @TypeConverter
    fun toRecentSearchEntity(value: String): RecentSearchEntity {
        return gson.fromJson(value, RecentSearchEntity::class.java)
    }

    // List<RecentSearchEntity> → String (저장 시)
    @TypeConverter
    fun fromRecentSearchEntityList(value: List<RecentSearchEntity>?): String {
        return gson.toJson(value)
    }

    // String → List<RecentSearchEntity> (읽을 때)
    @TypeConverter
    fun toRecentSearchEntityList(value: String): List<RecentSearchEntity>? {
        val type = object : TypeToken<List<RecentSearchEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}