package com.example.leaveit.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.leaveit.local.PlaceSearch.PlaceSearchEntity
import com.example.leaveit.local.RecentSearch.RecentSearchEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class DataConverter {

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


    // PlaceSearchEntity → String (저장 시)
    @TypeConverter
    fun fromPlaceSearchEntity(value: PlaceSearchEntity?): String {
        return gson.toJson(value)
    }

    // String → PlaceSearchEntity (가져올 때)
    @TypeConverter
    fun toPlaceSearchEntity(value: String): PlaceSearchEntity {
        return gson.fromJson(value, PlaceSearchEntity::class.java)
    }

    // List<PlaceSearchEntity> → String (저장 시)
    @TypeConverter
    fun fromPlaceSearchEntityList(value: List<PlaceSearchEntity>?): String {
        return gson.toJson(value)
    }

    // String → List<PlaceSearchEntity> (읽을 때)
    @TypeConverter
    fun toPlaceSearchEntityList(value: String): List<PlaceSearchEntity>? {
        val type = object : TypeToken<List<PlaceSearchEntity>>() {}.type
        return gson.fromJson(value, type)
    }

}