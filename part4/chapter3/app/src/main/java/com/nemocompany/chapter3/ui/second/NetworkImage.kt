package com.nemocompany.chapter3.ui.second

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter

@Composable
fun NetworkImageExample() {
    // 뒤에 내용(이미지주소)을 저장했다가 composable을 재 호출 할떄 기억했다가 알려주는 메서드 (?)
    // 라이브러리 coil 기능임
    // 이미지 주소를 가져오는건 애뮬레이터로 확인해야함 ( 프리뷰는 안보여줌 )
    val painter = rememberAsyncImagePainter(model = "https://developer.android.com/static/develop/ui/compose/images/layout-with-modifiers.png?hl=ko")
    Image(
        painter = painter,
        contentDescription = "엔텔로프 캐년"
    )
//    // 보통 이렇게 쓰는데 imageLoadter와 model은 커스텀마이징이 가능함 찾아봐야할듯
//    // 참고 레퍼런스: https://medium.com/@csh153/jetpack-compose-coil-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%BA%90%EC%8B%B1-%EC%9E%98-%ED%95%98%EA%B3%A0-%EA%B3%84%EC%8B%A0%EA%B0%80%EC%9A%94-806252d9c73a
//    AsyncImage(model = , contentDescription = , imageLoader = )
}