package com.example.ygoquizapp.dataclass

import com.example.ygoquizapp.db.CardDataEntity

data class QuizData(val cardData: CardDataEntity, val multipleChoices: List<MultipleChoice>)