package com.nemocompany.chapter3

import android.os.Bundle
import android.widget.Toast
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
import com.nemocompany.chapter3.ui.main.BoxExample1
import com.nemocompany.chapter3.ui.main.BoxExample2
import com.nemocompany.chapter3.ui.main.ButtonExample
import com.nemocompany.chapter3.ui.main.ColumnExample
import com.nemocompany.chapter3.ui.main.ColumnExample2
import com.nemocompany.chapter3.ui.main.ModifierExample3
import com.nemocompany.chapter3.ui.main.RowExample
import com.nemocompany.chapter3.ui.main.RowExample2
import com.nemocompany.chapter3.ui.main.RowExample3
import com.nemocompany.chapter3.ui.main.RowExample4
import com.nemocompany.chapter3.ui.main.SurfaceExample
import com.nemocompany.chapter3.ui.main.TextExample
import com.nemocompany.chapter3.ui.theme.Chapter3Theme

// 실제 적용되는 부분
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter3Theme {
                // Scaffold, modifier = 화면을 구성하는 큰 들 ( 부모 레이아웃 설정 )
                // 현재는 화면을 꽉 채울거냐, 에 따른 설정
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        TextExample(
                            name = "Android",
                        )
                        ButtonExample(
                            modifier = Modifier.padding(innerPadding),
                            onButtonClicked = {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Send Clicked",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                        ModifierExample3(
                        )
                        SurfaceExample()
                        BoxExample1()
                        BoxExample2()
//                        BoxExample3()
                        RowExample()
                        RowExample2()
                        RowExample3()
                        RowExample4()
                    }
                }
            }
        }
    }
}

// Preview
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter3Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                TextExample(
                    name = "Android",
                )
                ButtonExample() {  }
                ModifierExample3()
                SurfaceExample()
                BoxExample1()
                BoxExample2()
//                BoxExample3()
                RowExample()
                RowExample2()
                RowExample3()
                RowExample4()
                ColumnExample()
                ColumnExample2()
            }
        }
    }
}