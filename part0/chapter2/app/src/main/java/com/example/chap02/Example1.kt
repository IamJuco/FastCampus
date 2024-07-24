package com.example.chap02

fun main() {
    // 람다식 = 함수형 패러다임을 잘 쓰는것
    // 1. 람다식의 정의 = 익명함수 ( 함수의 이름이 없다. )
    // 2. 변수 처럼 사용 되서, 함수의 argument(전달인자)가 될 수 있고 return이 될 수 있다.
    // 3. 한번 사용 되고 재 사용 되지 않는 함수

    val a = fun() { println("hello") }
    val b: (Int) -> Int = {
        it * 10
    }
    println(b)
    println(b(10))

    val c = { i: Int, j:Int -> i * j }
    println(c(2,3))

    val d: (Int, String, Boolean) -> String = { _, b, _ -> b }
    println(d(3,"1",true))

    hello(10, b)

}

fun hello(a: Int, b:(Int) -> Int) : (Int) -> Int {
    println(a)
    println(b(20))
    return b
}