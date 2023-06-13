package com.example.ygoquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ygoquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        lateinit var binding:ActivityMainBinding
        var rightAnswer: String? = null
        var rightAnswerCount = 0
        var quizCount = 1

        var quizData = mutableListOf(
            mutableListOf("いつも苦しみに耐えているが、いつか必ず革命を起こすと心に誓っている。", "ゴキポン", "逃げ惑う民", "アイツ", "千眼の邪教神")
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showNextQuiz(quizData, binding);
    }

    fun showNextQuiz(quizData:  MutableList<MutableList<String>> , binding:  ActivityMainBinding){
        val quiz = quizData[0]
        binding.questionLabel.text = quiz[0]
        binding.firstAnswerBtn.text = quiz[1]
        binding.secondAnswerBtn.text = quiz[2]
        binding.thirdAnswerBtn.text = quiz[3]
        binding.fourthAnswerBtn.text = quiz[4]
    }

    fun checkAnswer(view: View){

    }

    fun checkQuizCount(){

    }
// 次回やること
//    Koin、Roomインストール
//    Koinの仕様理解＋ディレクトリ構造
}