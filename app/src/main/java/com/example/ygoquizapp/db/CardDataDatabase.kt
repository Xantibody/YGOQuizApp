package com.example.ygoquizapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardDataEntity::class], version = 1, exportSchema = false)
abstract class CardDataDatabase: RoomDatabase() {
    abstract fun cardDataDao(): CardDataDao
}
