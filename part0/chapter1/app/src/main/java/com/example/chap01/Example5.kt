package com.example.chap01

fun main() {
    max(10,3)
    isHoliday("금")
}

fun max(a: Int, b: Int) {
    if (a > b) {
        println(a)
    } else {
        println(b)
    }

    val result = if (a > b) {
        a
    } else {
        b
    }
    println(result)
}

fun isHoliday(dayOfWeek: String) {
    val result = when(dayOfWeek) {
        "토", "일" -> true
        else -> false
    }
    println(result)
}

fun isHoliday2(dayOfWeek: Any) {
    when(dayOfWeek) {
        "토", "일" -> if (dayOfWeek == "토") "좋아" else "너무 좋아"
        in 2..4 -> {}
        in listOf("월", "화") -> {}
        else -> "안좋아"
    }
}