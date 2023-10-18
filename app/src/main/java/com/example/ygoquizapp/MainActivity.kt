package com.example.ygoquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.TextButton
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ygoquizapp.model.CardDataInitialize
import com.example.ygoquizapp.theme.YGOQUizTheme
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

        setContent {
            this.naviTest()
        }
    }

    @Composable
    fun Quiz() {
        val navController = rememberNavController()
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val uiState by quizResultViewModel.uiState.collectAsState()
        Column() {
            Question(
                uiState.flavorText,
                uiState.quizCount,
                screenHeight,
                screenWidth
            )
            OutlinedCard(
                modifier = Modifier
                    .height(screenHeight / 5 * 3)
                    .width(screenWidth)
                    .padding(all = screenHeight / 20)

            ) {
                uiState.multipleChoices.forEach() { it ->
                    Choice(
                        it.name.toString(),
                        it.isAnswer,
                        screenHeight,
                        screenWidth
                    )
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Question(
        questionText: String,
        quizCount: Int,
        screenHeight: Dp,
        screenWidth: Dp
    ) {
        Card(
            onClick = {

            },
            modifier = Modifier
                .height(screenHeight / 5 * 2)
                .width(screenWidth)
                .padding(all = screenWidth / 16)
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


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Choice(
        choiceText: String,
        isAnswer: Boolean,
        screenHeight: Dp,
        screenWidth: Dp
    ) {
        var result by remember { mutableStateOf(false) }
        ElevatedCard(
            modifier = Modifier
                .height(screenHeight / 8)
                .width(screenWidth)
                .padding(
                    horizontal = screenWidth / 14,
                    vertical = screenHeight / 80
                ),
            onClick = { result = true }
        ) {
            Column() {
                Text(
                    text = choiceText,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        if (result) {
            val answer = if (isAnswer) {
                "正解"
            } else {
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


    @Preview
    @Composable
    fun PreviewMessageCard() {
        var arry: ArrayList<String> = arrayListOf()
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
                    Text(text = "Confirm")
                }
            }
        )
    }
    @Composable
    fun naviTest(){
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "top",
            modifier = Modifier
        ) {
            composable(route = "Quiz") {
                YGOQUizTheme {
                    Quiz()
                }
            }
            composable(route = "top") {
                    Card {

                    }
                }
            }
        }
}

