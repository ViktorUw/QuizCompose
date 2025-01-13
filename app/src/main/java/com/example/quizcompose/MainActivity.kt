package com.example.quizcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizApp()
        }
    }
}
@Composable
fun QuizApp() {
    MaterialTheme {
            QuizScreen()
        }
}

val questions = listOf(
    "Jaką właściwość ciała określa stosunek masy do objętości?" to listOf("Prędkość", "Energia kinetyczna", "Gęstość", "Temperatura"),
    "Co jest jednostką pracy w układzie SI?" to listOf("Dżul", "Wat", "Niuton", "Amper"),
    "Który gaz dominuje w atmosferze ziemskiej?" to listOf("Tlen", "Azot", "Dwutlenek węgla", "Hel"),
    "Jaka jest prędkość światła w próżni?" to listOf("300 tys. km/s", "150 tys. km/s", "400 tys. km/s", "250 tys. km/s"),
    "Która planeta jest najbliżej Słońca?" to listOf("Ziemia", "Mars", "Merkury", "Wenus"),
    "Który pierwiastek ma symbol chemiczny H?" to listOf("Wodór", "Hel", "Węgiel", "Azot"),
    "Co jest podstawową jednostką masy w układzie SI?" to listOf("Kilogram", "Gram", "Tona", "Miligram"),
    "Który z poniższych jest planetą gazową?" to listOf("Mars", "Ziemia", "Jowisz", "Wenus"),
    "Jaka jest jednostka częstotliwości?" to listOf("Herc", "Wolt", "Niuton", "Dżul"),
    "Która z planet Układu Słonecznego ma największą liczbę księżyców?" to listOf("Saturn", "Jowisz", "Uran", "Neptun")
)

val correctAnswers = listOf(2, 0, 1, 0, 2, 0, 0, 2, 0, 0)

@Composable
fun QuizScreen() {
    var currentQuestionIndex by rememberSaveable { mutableStateOf(0) }
    var selectedOption by rememberSaveable { mutableStateOf(-1) }
    var score by rememberSaveable { mutableStateOf(0) }
    var showResult by rememberSaveable { mutableStateOf(false) }

    if (showResult) {
        ResultScreen(score = score, totalQuestions = questions.size)
    } else {
        val question = questions[currentQuestionIndex]
        val options = question.second

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
            ) {
                Text(
                    text = "Pytanie ${currentQuestionIndex + 1}/${questions.size}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(30.dp))
                LinearProgressIndicator(
                    progress = (currentQuestionIndex + 1).toFloat() / questions.size,

                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = question.first,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.padding(16.dp)
                )
            }


            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                options.forEachIndexed { index, option ->
                    RadioButtonWithLabel(
                        label = option,
                        selected = selectedOption == index,
                        onSelect = { selectedOption = index }
                    )
                }
            }


            Button(
                onClick = {
                    if (selectedOption == correctAnswers[currentQuestionIndex]) {
                        score++
                    }
                    if (currentQuestionIndex + 1 < questions.size) {
                        currentQuestionIndex++
                        selectedOption = -1
                    } else {
                        showResult = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedOption != -1
            ) {
                Text(text = "Następne")
            }
        }
    }
}

@Composable
fun RadioButtonWithLabel(label: String, selected: Boolean, onSelect: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            if (selected) MaterialTheme.colorScheme.secondary else Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            RadioButton(
                selected = selected,
                onClick = onSelect,
                colors = RadioButtonDefaults.colors(selectedColor = Color.White)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Twój wynik to",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "$score/$totalQuestions",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

    }
}
@Preview(showBackground = true)
@Composable
fun quizPrev(){
    QuizApp()
}


