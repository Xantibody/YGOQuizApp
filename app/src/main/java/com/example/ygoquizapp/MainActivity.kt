package com.example.ygoquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ygoquizapp.databinding.ActivityMainBinding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.ygoquizapp.dataclass.MultipleChoice
import com.example.ygoquizapp.dataclass.QuizData
import com.example.ygoquizapp.model.CardDataInitialize
import com.example.ygoquizapp.model.QuizDataModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity  () {

    @Inject
    lateinit var cardDataInitialize: CardDataInitialize

    @Inject
    lateinit var quizDataModel: QuizDataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        lateinit var binding:ActivityMainBinding
        var rightAnswer: String? = null
        var rightAnswerCount = 0
        var quizCount = 1

        cardDataInitialize.test()
        var testQuiz : QuizData = quizDataModel.create()
        var array: ArrayList<MultipleChoice> = arrayListOf()

        testQuiz.multipleChoices.forEach(){
            array.add(it)
        }
        setContent{
            Quiz(testQuiz.cardData.text.toString(), array)
        }
    }

    @Composable
    fun Quiz (flavorText: String ,choices: ArrayList<MultipleChoice>){
        Column() {
            Question(flavorText)
            Choices(choices)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Choice(msg: String, isAnswer: Boolean){
        var result by remember { mutableStateOf(false) }
        Card(
            onClick = { result = true},
            modifier = Modifier.size(width = 180.dp, height = 100.dp)
        ) {
            Box(Modifier.fillMaxSize()) {
                Text(msg, Modifier.align(Alignment.Center))
            }
        }

        if (result) {
            val answer = if(isAnswer) {
                "正解"
            }else{
                "不正解"
            }
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onDismissRequest.
                    var a = false
                },
                title = {
                    Text(text = msg)
                },
                text = {
                    Text(text = answer)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            var a = false
                        }
                    ) {
                        Text(text = "Confirm")
                    }
                }
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Question (text: String){
        Card(
            onClick = {

            },
            modifier = Modifier.size(width = 180.dp, height = 100.dp)
        ) {
            Box(Modifier.fillMaxSize()) {
                Text(text, Modifier.align(Alignment.Center))
            }
//        MaterialTheme {
//            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
//                Card(
//                    onClick = {
//
//                    },
//                    modifier = Modifier.size(width = 180.dp, height = 100.dp)
//                ) {
//                    Box(Modifier.fillMaxSize()) {
//                        Text(text, Modifier.align(Alignment.Center))
//                    }
//                }
//            }
        }
    }

    @Composable
    fun Choices(choices: ArrayList<MultipleChoice>) {
        choices.forEach() { it ->
            Choice(
                it.name.toString(),
                it.isAnswer
            )
        }


//        MaterialTheme {
//            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
//                Choice(array)
//            }
//        }
    }

//    @Composable
//    fun MessageCard(name: String, msg: String) {
//        Column {
//            Text(
//                text = "Hello $name!",
//                color = MaterialTheme.colors.secondaryVariant,
//                style = MaterialTheme.typography.subtitle2
//            )
//            Spacer(modifier = Modifier.height(4.dp))
//            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
//                Text(
//                    text = "Message ⇒ $msg!",
//                    color = MaterialTheme.colors.secondaryVariant,
//                    style = MaterialTheme.typography.subtitle2
//                )
//            }
//        }
//    }

    @Preview
    @Composable
    fun PreviewMessageCard() {
        var arry :ArrayList<String> = arrayListOf()
        arry.add("こんにちは")
        arry.add("さようなら")
        arry.add("いただきます")
        arry.add("ご馳走様")
        MaterialTheme {
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                //Conversion(arry)
            }
        }
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                var a = false
            },
            title = {
                Text(text = "Title")
            },
            text = {
                Text(text = "Text")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                    var a = false
                    }
                ) {
                    Text(text ="Confirm")
                }
            }
        )
    }

    @Composable
    fun AnswerAlertDialog(){

    }
}
