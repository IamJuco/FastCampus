package com.nemocompany.chapter4

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
import com.nemocompany.chapter4.ui.theme.Chapter4Theme
import com.nemocompany.chapter4.ui.third.CanvasExample
import com.nemocompany.chapter4.ui.third.CardData
import com.nemocompany.chapter4.ui.third.ChainAndBarrierExample
import com.nemocompany.chapter4.ui.third.ChainAndBarrierExample2
import com.nemocompany.chapter4.ui.third.ConstraintLayoutHardExample
import com.nemocompany.chapter4.ui.third.CustomDialogExample
import com.nemocompany.chapter4.ui.third.DialogExample
import com.nemocompany.chapter4.ui.third.DropDownExample
import com.nemocompany.chapter4.ui.third.SnackbarExample

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        // 테스트 해볼땐 얘네들만 주석 풀어야함 나머지는 주석
//                        ChainAndBarrierExample()
//                        ChainAndBarrierExample2()

                        // 테스트 해볼땐 얘네들만 주석 풀어야함 나머지는 주석
//                        ConstraintLayoutHardExample(cardData)
//                        ConstraintLayoutHardExample(cardData)

                        // 테스트 해볼땐 얘네들만 주석 풀어야함 나머지는 주석
                        // 얘는 실행해서 보려면 줌을 크게 확대해야함
                        CanvasExample()
                        DialogExample()
                        CustomDialogExample()
                        DropDownExample()
                        SnackbarExample()
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
fun GreetingPreview3() {
    Chapter4Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
//                ChainAndBarrierExample()
//                ChainAndBarrierExample2()
                CustomDialogExample()
                DropDownExample()
                SnackbarExample()
            }
        }
    }
}
