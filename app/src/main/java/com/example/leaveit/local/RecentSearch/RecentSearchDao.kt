package com.example.leaveit.local.RecentSearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecentSearchDao {

    @Query("SELECT * FROM RecentSearchEntity")
    suspend fun getAll() : List<RecentSearchEntity>?

    // 튜플 개수 파악
    @Query("SELECT COUNT(*) FROM RecentSearchEntity")
    suspend fun getItemCount() : Int?

    // 제일 첫 번째 튜플 삭제
    @Query("DELETE FROM RecentSearchEntity WHERE searchIndex = 1")
    suspend fun deleteFirstRow()

    // 삭제 후 남아있는 데이터 정렬
    @Query("UPDATE RecentSearchEntity SET searchIndex = searchIndex - 1 WHERE searchIndex > 1")
    suspend fun rearrangeAfterFirstDeletion()


    // 제일 마지막으로 들어간 데이터 인덱스 조회
    @Query("SELECT MAX(searchIndex) FROM RecentSearchEntity")
    suspend fun getMaxOrderIndex(): Int?

    // 튜플 삽입
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(value : RecentSearchEntity)

    // 전체 삭제
    @Query("DELETE FROM RecentSearchEntity")
    suspend fun deleteAllData()
}