package com.example.chap01

fun main() {
    val result = test(1, c = 5)
    println(result)
}

fun test(a: Int, b: Int = 3, c: Int = 4) : Int {
    println(a + b + c)
    return a + b + c
}