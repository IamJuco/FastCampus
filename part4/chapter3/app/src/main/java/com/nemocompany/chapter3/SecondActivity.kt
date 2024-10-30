package com.nemocompany.chapter3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nemocompany.chapter3.ui.main.ColumnExample
import com.nemocompany.chapter3.ui.second.BoxWithConstraintsExample
import com.nemocompany.chapter3.ui.second.CardData
import com.nemocompany.chapter3.ui.second.ImageExample
import com.nemocompany.chapter3.ui.second.NetworkImageExample
import com.nemocompany.chapter3.ui.second.ProfileCardExample
import com.nemocompany.chapter3.ui.theme.Chapter3Theme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        BoxWithConstraintsExample()
                        ImageExample()
//                NetworkImageExample()
                        ProfileCardExample(cardData)
                        ProfileCardExample(cardData)
                    }
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUrl = "https://www.nextdaily.co.kr/news/photo/201605/18968_23931.jpg",
            imageDescription = "엔텔로프 캐년",
            author = "주성현",
            description = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingSecondPreview() {
    Chapter3Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                BoxWithConstraintsExample()
                ImageExample()
//                NetworkImageExample()
            }
        }
    }
}