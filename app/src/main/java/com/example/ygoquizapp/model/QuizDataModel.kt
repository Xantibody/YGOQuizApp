package com.example.ygoquizapp.model

import com.example.ygoquizapp.dataclass.MultipleChoice
import com.example.ygoquizapp.dataclass.QuizData
import com.example.ygoquizapp.db.CardDataDao
import com.example.ygoquizapp.db.CardDataEntity
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

// レイアウト参考
// https://www.youtube.com/watch?v=xc8nAcVvpxY

class QuizDataModel @Inject constructor(
    private val cardDataDao: CardDataDao) {


    fun create(): QuizData {
        var randomRecord: CardDataEntity
        var randomRecordsByType: List<CardDataEntity>

        runBlocking {
            randomRecord = cardDataDao.selectRandomRecord()
            randomRecordsByType = cardDataDao.selectRandomRecordsByType(randomRecord.type)
        }
        var multipleChoices = createMultipleChoice(randomRecord, randomRecordsByType)
        var shuffledMultipleChoices = multipleChoices.shuffled()
        return createQuizData(randomRecord, shuffledMultipleChoices)
    }


    private fun createMultipleChoice(record: CardDataEntity, records: List<CardDataEntity>): ArrayList<MultipleChoice>{
        var multipleChoices: ArrayList<MultipleChoice> = arrayListOf()

        multipleChoices.add(MultipleChoice(name = record.name, isAnswer = true))
        records.forEach(){
            multipleChoices.add(MultipleChoice(name = it.name, isAnswer = false))
        }
        return multipleChoices
    }


    private fun createQuizData (record: CardDataEntity, multipleChoices: List<MultipleChoice>) :QuizData{
        return QuizData(cardData = record, multipleChoices= multipleChoices)
    }
}