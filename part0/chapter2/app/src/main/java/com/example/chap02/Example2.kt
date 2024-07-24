package com.example.chap02

fun main() {
    // 확장함수 란 = Extension function
    // 기존에 정의 되어있는 클래스에 함수를 추가하는 기능
    val test = Test()
    Test().hello()
    test.hi()
}
// 확장함수, Test class에 hi 함수를 추가한것과 동일
fun Test.hi() = println("하이")

class Test() {
    fun hello() = println("안녕")
    fun bye() = println("잘가")
}