package com.example.ygoquizapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(state: List<CardDataEntity>)
    // 後でcorrect_countを取得する
    // SELECT * FROM datas WHERE 100 < atk ORDER BY RANDOM()
    @Query("SELECT * FROM card_data ORDER BY RANDOM() LIMIT 1")
    suspend fun selectRandomRecord(): CardDataEntity

    @Query("SELECT * FROM card_data WHERE type = :type ORDER BY RANDOM() LIMIT 3")
    suspend fun selectRandomRecordsByType(type: String?): List<CardDataEntity>

    //test
    @Query("delete FROM card_data")
    suspend fun deleteAll()

    @Query("select count(*) From card_data")
    suspend fun getCount():String
//    @Query("SELECT min(correct_count) FROM card_data")
//    fun getCorrectCountBy(): CardDataEntity
}