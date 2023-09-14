package com.example.ygoquizapp.model

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.ygoquizapp.db.CardDataDao
import com.example.ygoquizapp.db.CardDataDatabase
import com.example.ygoquizapp.db.CardDataEntity
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CardDataInitialize @Inject constructor(
    private val cardDataDao: CardDataDao,
    @ApplicationContext
    private val context: Context
    ) {

    fun test(){
        val test :MutableList<CardDataEntity>  = this.readCsv()
        runBlocking {
            println("db削除判定" + context.deleteDatabase("card_data"))
            cardDataDao.insert(test)
            var randomRecord = cardDataDao.selectRandomRecord()
            println(randomRecord)
//            println(cardDataDao.selectRandomRecordsByType(randomRecord.type))
            println(cardDataDao.getCount())
        }
    }

    fun readCsv (): MutableList<CardDataEntity> {
        // SharedPreferences
        val cardDataList = mutableListOf<CardDataEntity>()
        var fileName = "card_data.csv"
        var file = context.assets.open(fileName)
        csvReader().readAllWithHeader(file).forEach {
            var cardData = CardDataEntity(
                name        = it.get(key = "name"),
                text        = it.get(key = "text"),
                type        = it.get(key = "type"),
                attribute   = it.get(key = "attribute"),
                level       = it.get(key = "level"),
                atk         = it.get(key = "atk"),
                def         = it.get(key = "def")
            )
            cardDataList.add(cardData)
        }
        return cardDataList
    }
}