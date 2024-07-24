package com.example.chap02

lateinit var text1: String

val test2: Int by lazy {
    println("초기화 중")
    100
}

fun main() {
    println("메인 함수 실행")
    println("초기화 한 값 $test2")
    text1 = "하이"
    println(text1)
}