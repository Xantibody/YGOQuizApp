package com.example.ygoquizapp.veiwmodel



class QuizResultViewModel {
    private var quizCount = 1
    private var totalQuizCount = 0
    private var answerList = arrayListOf<Boolean>()

    fun setTotalQuizCount(num :Int){
        this.totalQuizCount = num
    }

    fun incrementQuizCount(){
        ++this.quizCount
    }

    fun addAnswerList(isAnswer: Boolean){
        this.answerList.add(isAnswer)
    }

    fun isLastQuestion(): Boolean {
        return this.quizCount === this.totalQuizCount
    }
}