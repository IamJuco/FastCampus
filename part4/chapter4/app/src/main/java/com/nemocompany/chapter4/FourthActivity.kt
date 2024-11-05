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
import com.nemocompany.chapter4.ui.fourth.PyeongToSquareMeter
import com.nemocompany.chapter4.ui.fourth.PyeongToSquareMeterStateless
import com.nemocompany.chapter4.ui.theme.Chapter4Theme

class FourthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        PyeongToSquareMeter()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview4() {
    Chapter4Theme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {

            }
        }
    }
}