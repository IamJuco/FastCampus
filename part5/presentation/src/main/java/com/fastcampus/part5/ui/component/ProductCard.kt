package com.fastcampus.part5.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fastcampus.part5.delegate.ProductDelegate
import com.fastcampus.part5.domain.model.Category
import com.fastcampus.part5.domain.model.Price
import com.fastcampus.part5.domain.model.Product
import com.fastcampus.part5.domain.model.SalesStatus
import com.fastcampus.part5.domain.model.Shop
import com.fastcampus.part5.model.ProductVM
import com.fastcampus.part5.ui.theme.Purple80
import com.fastcampus.presentation.R

@Composable
fun ProductCard(navHostController: NavHostController, presentationVM: ProductVM) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp)
            .shadow(elevation = 10.dp),
        onClick = {
            presentationVM.openProduct(navHostController, presentationVM.model)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                contentDescription = "descripition",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    // 이미지 비율이 1:1로 구성이 됌
                    .aspectRatio(1f)
            )
            Text(
                text = presentationVM.model.shop.shopName,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = presentationVM.model.productName,
                fontSize = 14.sp
            )
            Price(product = presentationVM.model)
        }
    }
}

@Composable
fun Price(product: Product) {
    when (product.price.salesStatus) {
        SalesStatus.ON_SALE -> {
            Text(
                text = "${product.price.originPrice}원",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        SalesStatus.ON_DISCOUNT -> {
            Text(
                text = "${product.price.originPrice}원",
                fontSize = 14.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )

            Text(
                text = "${product.price.finalPrice}원",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Purple80
            )
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
        rememberNavController(),
        ProductVM(
            model = Product(
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
            ),
            object : ProductDelegate {
                override fun openProduct(navHostController: NavHostController, product: Product) {
                }
            }
        )
    )
}

@Preview
@Composable
private fun PreviewProductCardDiscount() {
    ProductCard(
        rememberNavController(),
        ProductVM(
            model = Product(
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
            ),
            object : ProductDelegate {
                override fun openProduct(navHostController: NavHostController, product: Product) {
                }
            }
        )
    )
}

@Preview
@Composable
private fun PreviewProductCardSoldOut() {
    ProductCard(
        rememberNavController(),
        ProductVM(
            model = Product(
                productId = "1",
                productName = "상품 이름",
                imageUrl = "",
                price = Price(
                    30000,
                    20000,
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
            ),
            object : ProductDelegate {
                override fun openProduct(navHostController: NavHostController, product: Product) {
                }
            }
        )
    )
}