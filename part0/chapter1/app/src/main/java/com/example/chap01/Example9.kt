package com.example.chap01

fun main() {
    println(check("안녕"))
    println(check(true))
    cast("안녕")
    cast(1)
    smartCast("안녕하세요")
    smartCast(10)
    smartCast(true)
}

fun check(a: Any): String{
    return when (a) {
        is String -> {
            "문자열"
        }

        is Int -> {
            "숫자"
        }

        else -> {
            "몰라요"
        }
    }
}

fun cast(a: Any) {
    val result = (a as? String) ?: "실패"
    println(result)
}

fun smartCast(a: Any) {
    val result = if (a is String) {
        a.length
    } else if (a is Int) {
        a.dec()
    } else {
        -1
    }
    println(result)
}