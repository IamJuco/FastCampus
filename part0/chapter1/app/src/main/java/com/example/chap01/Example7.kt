package com.example.chap01

fun main() {
    val list = mutableListOf(1,2,3,4, 5)
    list.add(6)
    list.addAll(listOf(7,8,9))

    val list1 = listOf(1,2,3,4)
    list1[0]

    // id가 10인걸 찾아줘
    /** list1.first { id = 10 } */
    // list1에 있는 리스트에 전부 10을 곱하기
    println(list1.map { it * 10 }.joinToString("/"))


    val diverseList = listOf(1, "안녕", 1.78, true)

    println(list)
    println(list.joinToString(","))
    println(list.joinToString(""))

    // map은 key와 value
    val map = mapOf((1 to "안녕"), (2 to "hello"))
    val map1 = mutableMapOf((1 to "안녕"), (2 to "hello"))
    map1[3] = "응"
    map1[100] = "호이"
    println(map1)
}