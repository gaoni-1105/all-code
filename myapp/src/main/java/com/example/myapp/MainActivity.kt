package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val count = remember { mutableStateOf(0) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Counter(count)
                Spacer(modifier = Modifier.height(32.dp))
                StopWatch()
                Spacer(modifier = Modifier.height(32.dp))
                PerfectStopGame()
            }
        }
    }
}

// 카운터 앱
@Composable
fun Counter(count: MutableState<Int>) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Count: ${count.value}",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Row {
            Button(onClick = { count.value++ }) { Text("Increase") }
            Button(onClick = { count.value = 0 }) { Text("Reset") }
        }
    }
}

// 스톱워치 앱
@Composable
fun StopWatch() {
    var time by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (true) {
                delay(10L)
                time += 10L
            }
        }
    }

    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatTime(time),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { isRunning = true },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) { Text("Start") }
                Button(
                    onClick = { isRunning = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                ) { Text("Stop") }
                Button(
                    onClick = {
                        isRunning = false
                        time = 0L
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text("Reset") }
            }
        }
    }
}

// 퍼펙트 스톱 게임
@Composable
fun PerfectStopGame() {
    var time by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    val target = 10_000L // 10초 목표

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (isRunning) {
                delay(10L)
                time += 10L
            }
        }
    }

    Card(
        modifier = Modifier.padding(24.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "10초를 맞춰라! 🎯",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = formatTime(time),
                fontSize = 52.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (!isRunning) {
                            time = 0L
                            message = ""
                            isRunning = true
                        }
                    },
                    enabled = !isRunning,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                ) { Text("Start") }
                Button(
                    onClick = {
                        if (isRunning) {
                            isRunning = false
                            val diff = kotlin.math.abs(time - target)
                            val secondsOff = diff / 1000.0
                            val score = maxOf(0, (100 - secondsOff * 10).toInt())
                            message = "⏱ 오차: ${"%.2f".format(secondsOff)}초\n⭐ 점수: $score / 100"
                        }
                    },
                    enabled = isRunning,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
                ) { Text("Stop") }
                Button(
                    onClick = {
                        isRunning = false
                        time = 0L
                        message = ""
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) { Text("Reset") }
            }
            Spacer(modifier = Modifier.height(24.dp))
            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF3F51B5)
                )
            }
        }
    }
}

// 시간 포맷 함수
fun formatTime(time: Long): String {
    val minutes = (time / 1000) / 60
    val seconds = (time / 1000) % 60
    val millis = (time % 1000) / 10
    return String.format("%02d:%02d:%02d", minutes, seconds, millis)
}

// 색상 변경 버튼 (선택사항)
@Composable
fun ColorButton() {
    var color by remember { mutableStateOf(Color.Red) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color)
                .clickable {
                    color = if (color == Color.Red) Color.Blue else Color.Red
                }
                .padding(30.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Click Me",
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun CounterPreview() {
    val count = remember { mutableStateOf(0) }
    Counter(count)
}

@Preview(showBackground = true)
@Composable
fun StopWatchPreview() {
    StopWatch()
}

@Preview(showBackground = true)
@Composable
fun GamePreview() {
    PerfectStopGame()
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun ColorButtonPreview() {
    ColorButton()
}