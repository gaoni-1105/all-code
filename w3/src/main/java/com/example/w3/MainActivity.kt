package com.example.w03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.R
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w03.drawable
import com.example.w3.ui.theme.AllCodeTheme

private val Unit.drawable: Any

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AllCodeTheme {
                HomeScreen()
            }
        }
    }
}
@Composable()
fun HomeScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "망한 Apple",
            color = Color(0xFF666666),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "아이폰 17 Pro",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
            Card(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iphone),
                    contentDescription = "아이폰 17 Pro 이미지",
                    modifier = Modifier
                        .size(280.dp)
                        .padding(16.dp)
                )
            }
            Text(
                text = "디자인 폭망 했어요!",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFFE91E63),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
        }
        Text(
            text = "202511062 옥가온",
            color = Color(0xFF666666),
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = Color(0xFF4CAF50),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AllCodeTheme {
        Greeting("Android")
    }
}