package com.example.chap02

fun main() {

    val cat: Cat = BlueCat()
    val result = when(cat) {
        is BlueCat -> "blue"
        is RedCat -> "Red"
        is GreenCat -> "GreenCat"
    }
}

// when과 강력함, 사용할때 else를 사용하지 않아도됌 -> 컴파일러 단계에서 상속 받을떄 더 상속 받을게 있는지 없는지 확인 가능
// 즉 에러단계에서 사용할때 좋음
sealed class Cat
class BlueCat : Cat()
class RedCat : Cat()
class GreenCat : Cat()
//class WhiteCat : Cat()
