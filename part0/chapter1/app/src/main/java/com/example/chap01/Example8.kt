package com.example.chap01

fun main() {
    var name: String = "성현"
    var number: Int = 10

    var nickname: String? = "성현"
    nickname = null

    val result = nickname ?: "값이없음"
    println(result)

    val size = nickname?.length
    println(size)
}