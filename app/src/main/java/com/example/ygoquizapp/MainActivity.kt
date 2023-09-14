package com.example.ygoquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.ygoquizapp.dataclass.MultipleChoice
import com.example.ygoquizapp.db.CardDataEntity
import com.example.ygoquizapp.model.CardDataInitialize
import com.example.ygoquizapp.veiwmodel.QuizResultViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity  () {

    @Inject
    lateinit var cardDataInitialize: CardDataInitialize

    val quizResultViewModel: QuizResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cardDataInitialize.test()
        quizResultViewModel.updateQuiz()
//        var array: ArrayList<MultipleChoice> = arrayListOf()

//        testQuiz.multipleChoices.forEach(){
//            array.add(it)
//        }

//        var flavorText: String = quizResultViewModel.uiState.value.flavorText
//        var multipleChoices: ArrayList<MultipleChoice> = quizResultViewModel.uiState.value.multipleChoices
        setContent{
//            Quiz(flavorText, multipleChoices)
            Quiz()
        }
    }

    @Composable
//    fun Quiz (flavorText: String ,choices: List<MultipleChoice>){
    fun Quiz (){
        val uiState by quizResultViewModel.uiState.collectAsState()
        Column() {
            Question(uiState.flavorText, uiState.quizCount)
            uiState.multipleChoices.forEach() { it ->
                Choice(
                    it.name.toString(),
                    it.isAnswer
                )
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Question (questionText: String, quizCount:Int){
        Card(
            onClick = {

            },
            modifier = Modifier
        ) {
            Column() {
                Text(
                    text = "題${quizCount}問",
                    style = MaterialTheme.typography.titleLarge
                    )
                Text(
                    text = questionText,
                    style = MaterialTheme.typography.bodyLarge
                )
            }


            }
    }

//    @Composable
//    fun Choices(choices: List<MultipleChoice>) {
//
//        }



//        MaterialTheme {
//            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
//                Choice(array)
//            }
//        }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Choice(choiceText: String, isAnswer: Boolean){
        var result by remember { mutableStateOf(false) }
        Card(
            onClick = { result = true}
        ) {
            Column() {
                Text(
                    text = choiceText,
                    style = MaterialTheme.typography.bodyLarge
                )
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

                },
                title = {
                    Text(text = choiceText)
                },
                text = {
                    Text(text = answer)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            quizResultViewModel.addAnswerList(isAnswer)
                            quizResultViewModel.incrementQuizCount()
                            quizResultViewModel.updateQuiz()
                            result = false
                        }
                    ) {
                        Text(text = "次の問題へ")
                    }
                }
            )
        }
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

//    @Composable
//    fun AnswerAlertDialog(){
//
//    }
}
