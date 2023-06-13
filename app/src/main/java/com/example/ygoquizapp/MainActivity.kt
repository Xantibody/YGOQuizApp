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

}

// https://codeforfun.jp/android-studio-quiz-game-with-kotlin-4/
// とりあえず構造を真似する
// ダメそうなので一から考える
// 軽い設計
// DBからランダムに10問文のデータ取得⇒4つの中から正解を作成（正解flgをつける）⇒画面に1問目表示⇒成否判定画面（出来ればカードURL）⇒次の問題⇒ラスト終わったら正解数画面⇒トップページへ

//　必要メソッド
//DBアクセスメソッド
//問題作成メソッド（出題回数インサート、DTO作成、リストで持つ）
//画面へセットするメソッド
//成否判定表示メソッド同時に正解数加算（問題を押したとき）
//最後の問題か判定するメソッド（成否表をとじたとき）
//次の問題を取り出すメソッド（上の次）






//https://developer.android.com/training/data-storage/room?hl=ja#kotlin
//データの永続化に使う