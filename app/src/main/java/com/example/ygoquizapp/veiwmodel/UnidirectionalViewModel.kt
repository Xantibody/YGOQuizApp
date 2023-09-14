package com.example.ygoquizapp.veiwmodel

import androidx.lifecycle.ViewModel
import com.example.ygoquizapp.dataclass.MultipleChoice
import com.example.ygoquizapp.db.CardDataDao
import com.example.ygoquizapp.db.CardDataEntity
import com.example.ygoquizapp.model.QuizDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuizResultViewModel @Inject constructor(
    private val cardDataDao: CardDataDao,
    private val quizDataModel: QuizDataModel
): ViewModel() {

    data class UiState(
        var quizCount: Int = 1,
        var totalQuizCount: Int = 0,
        var answerList: ArrayList<Boolean> = arrayListOf<Boolean>(),
        var flavorText: String = "",
        var multipleChoices: List<MultipleChoice> = listOf<MultipleChoice>()
        ){ companion object {
            val default = UiState()
        }
    }



    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.default)
    val uiState : StateFlow<UiState> = _uiState.asStateFlow()

    fun setTotalQuizCount(num :Int){
        _uiState.value.totalQuizCount = num
    }

    fun incrementQuizCount(){
        ++_uiState.value.quizCount
    }

    fun addAnswerList(isAnswer: Boolean){
        _uiState.value.answerList.add(isAnswer)
    }

    fun isLastQuestion(): Boolean {
        return _uiState.value.quizCount === _uiState.value.totalQuizCount
    }
    fun updateQuiz() {
        val answerCardData: CardDataEntity = quizDataModel.createAnswerRecord()
        _uiState.update { it.copy(flavorText = answerCardData.text.toString()) }
        _uiState.update {
            it.copy(
                multipleChoices = quizDataModel.createMultipleChoice(
                    baseCardType = answerCardData.type.toString(),
                    baseCardName = answerCardData.name.toString()
                )
            )
        }
    }
}