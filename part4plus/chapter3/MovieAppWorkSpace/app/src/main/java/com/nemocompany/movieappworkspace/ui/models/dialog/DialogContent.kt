package com.nemocompany.movieappworkspace.ui.models.dialog

// 보여지는 방식을 정의한 class ( 컨텐츠들 = 기본일떄, 클떄, 레이팅일때 등 )
sealed class DialogContent {
    // Default = 보여주는 내용은 같지만 보여지는 방식이 다름
    data class Default(
        val dialogText: DialogText.Default
    ) : DialogContent()

    data class Large(
        val dialogText: DialogText.Default
    ) : DialogContent()
    // 그럼 당연히 얘는 보여주는 내용 자체가 다르겠지?
    data class Rating(
        val dialogText: DialogText.Rating
    ) : DialogContent()
}