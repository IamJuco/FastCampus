package com.fastcampus.part5.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.model.Ranking
import com.fastcampus.part5.model.RankingVM
import com.fastcampus.presentation.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(presentationVM: RankingVM) {
    val pagerState = rememberPagerState()
    val pageCount = presentationVM.model.productList.size / DEFAULT_RANKING_ITEM_COUNT

    Column {
        Text(
            text = presentationVM.model.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        )
        HorizontalPager(
            count = pageCount,
            state = pagerState,
            // 다음 아이템 살짝 보이게 end만 50dp 설정
            contentPadding = PaddingValues(end = 50.dp)
        ) { index ->
            Column {
                RankingProductCard(
                    index = index * 3,
                    product = presentationVM.model.productList[index * 3], presentationVM
                ) { product ->
                    presentationVM.openRankingProduct(product)
                }

                RankingProductCard(
                    index = index * 3 + 1,
                    product = presentationVM.model.productList[index * 3 + 1], presentationVM
                ) { product ->
                    presentationVM.openRankingProduct(product)
                }

                RankingProductCard(
                    index = index * 3 + 2,
                    product = presentationVM.model.productList[index * 3 + 2], presentationVM
                ) { product ->
                    presentationVM.openRankingProduct(product)
                }

            }
        }
    }
}

@Composable
fun RankingProductCard(
    index: Int,
    product: Product,
    presentationVM: RankingVM,
    onClick: (Product) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${index + 1}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.product_image),
            contentDescription = "description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(0.7f)
        )
        Column(
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Text(
                text = product.shop.shopName,
                fontSize = 14.sp,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Text(
                text = product.productName,
                fontSize = 14.sp,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Price(
                product = product
            )
        }
    }
}

private const val DEFAULT_RANKING_ITEM_COUNT = 3