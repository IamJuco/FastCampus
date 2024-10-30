package com.nemocompany.chapter3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nemocompany.chapter3.ui.fourth.CatalogExample
import com.nemocompany.chapter3.ui.fourth.Item
import com.nemocompany.chapter3.ui.theme.Chapter3Theme

class FourthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        color = MaterialTheme.colorScheme.error
                    ) {
                        CatalogExample(itemList = items)
                    }
                }
            }
        }
    }
}

data class ItemData(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String
)

val items = listOf(
    ItemData(
        imageId = R.drawable.wall_hdpi,
        title = "미국 사막",
        description = "여긴 미국 사막 사막 사막 이다."
    ),
    ItemData(
        imageId = R.drawable.wall_hdpi,
        title = "미국 사막",
        description = "여긴 미국 사막 사막 사막 이다."
    ),
    ItemData(
        imageId = R.drawable.wall_hdpi,
        title = "미국 사막",
        description = "여긴 미국 사막 사막 사막 이다."
    ),
    ItemData(
        imageId = R.drawable.wall_hdpi,
        title = "미국 사막",
        description = "여긴 미국 사막 사막 사막 이다."
    ),
    ItemData(
        imageId = R.drawable.wall_hdpi,
        title = "미국 사막",
        description = "여긴 미국 사막 사막 사막 이다."
    ),
)

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Chapter3Theme {
        Item(
            ItemData(
                imageId = R.drawable.wall_hdpi,
                title = "미국 사막",
                description = "여긴 미국 사막 사막 사막 이다."
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Chapter3Theme {
        CatalogExample(itemList = items)
    }
}