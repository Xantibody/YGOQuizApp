package com.example.ygoquizapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_data")
data class CardDataEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "text")
    var text: String?,

    @ColumnInfo(name = "type")
    var type: String?,

    @ColumnInfo(name = "attribute")
    var attribute: String?,

    @ColumnInfo(name = "level")
    var level: String?,

    @ColumnInfo(name = "atk")
    var atk: String?,

    @ColumnInfo(name = "def")
    var def: String?,

    @ColumnInfo(name = "correct_count")
    var correctCount: Int = 0
)