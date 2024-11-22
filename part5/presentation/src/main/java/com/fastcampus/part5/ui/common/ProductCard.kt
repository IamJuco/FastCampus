package com.fastcampus.part5.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Price
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.model.SalesStatus
import com.fastcampus.part5.domain.model.Shop
import com.fastcampus.part5.ui.theme.Purple80
import com.fastcampus.presentation.R

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit?) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp)
            .shadow(elevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                contentDescription = "descripition",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            )
            Text(
                text = product.shop.shopName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = product.productName,
                fontSize = 14.sp
            )
            Price(product = product)
        }
    }
}

@Composable
fun Price(product: Product) {
    when (product.price.salesStatus) {
        SalesStatus.ON_SALE -> {
            Text(
                text = "${product.price.originPrice}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SalesStatus.ON_DISCOUNT -> {
            Text(
                text = "${product.price.originPrice}",
                fontSize = 14.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
            Row(
                // 강의와 다르게 높이 값이 맞지않아서 해당 값 추가
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "할인가: ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${product.price.finalPrice}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple80
                )
            }
        }

        SalesStatus.SOLD_OUT -> {
            Text(
                text = "판매종료",
                fontSize = 18.sp,
                color = Color(0xFF666666)
            )
        }
    }
}

@Preview
@Composable
private fun PreviewProductCard() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품 이름",
            imageUrl = "",
            price = Price(
                30000,
                30000,
                SalesStatus.ON_SALE
            ),
            category = Category.Top,
            shop = Shop(
                "1",
                "샵이름",
                ""
            ),
            isNew = false,
            isFreeShipping = false
        )
    ) {

    }
}

@Preview
@Composable
private fun PreviewProductCardDiscount() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품 이름",
            imageUrl = "",
            price = Price(
                30000,
                20000,
                SalesStatus.ON_DISCOUNT
            ),
            category = Category.Top,
            shop = Shop(
                "1",
                "샵이름",
                ""
            ),
            isNew = false,
            isFreeShipping = false
        )
    ) {

    }
}

@Preview
@Composable
private fun PreviewProductCardSoldOut() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품 이름",
            imageUrl = "",
            price = Price(
                30000,
                30000,
                SalesStatus.SOLD_OUT
            ),
            category = Category.Top,
            shop = Shop(
                "1",
                "샵이름",
                ""
            ),
            isNew = false,
            isFreeShipping = false
        )
    ) {

    }
}