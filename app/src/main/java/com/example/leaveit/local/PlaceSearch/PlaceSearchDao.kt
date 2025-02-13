package com.example.leaveit.local.PlaceSearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceSearchDao {

    @Query("SELECT * FROM RecentSearchPlaceEntity ")
    suspend fun getAll() : List<RecentSearchPlaceEntity>

    // 튜플 개수 파악
    @Query("SELECT COUNT(*) FROM RecentSearchPlaceEntity")
    suspend fun getItemCount() : Int?

    // 선택된 아이템의 아이디에 해당하는 값 삭제
    @Query("DELETE FROM RecentSearchPlaceEntity WHERE searchContentId = (:selectItemId)")
    suspend fun deleteFirstRow(selectItemId : String)

    // 삭제 후 남아있는 데이터 정렬
    @Query("UPDATE RecentSearchPlaceEntity SET searchIndex = searchIndex - 1 WHERE searchIndex > 1")
    suspend fun rearrangeAfterFirstDeletion()


    // 제일 마지막으로 들어간 데이터 인덱스 조회
    @Query("SELECT MAX(searchIndex) FROM RecentSearchPlaceEntity")
    suspend fun getMaxOrderIndex(): Int?

    // 튜플 삽입
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(value : RecentSearchPlaceEntity)

    // 전체 삭제
    @Query("DELETE FROM RecentSearchEntity")
    suspend fun deleteAllData()

}