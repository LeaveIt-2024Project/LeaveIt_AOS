package com.example.leaveit.local.PlaceSearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceSearchDao {

    @Query("SELECT * FROM RecentSearchPlaceEntity ")
    suspend fun getAll() : List<RecentSearchPlaceEntity>

    // 선택된 아이템의 아이디에 해당하는 값 삭제
    @Query("DELETE FROM RecentSearchPlaceEntity WHERE uid = (:selectItemId)")
    suspend fun deleteFirstRow(selectItemId : String)

    // 튜플 삽입
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(value : RecentSearchPlaceEntity)

    // 전체 삭제
    @Query("DELETE FROM RecentSearchPlaceEntity")
    suspend fun deleteAllData()

}