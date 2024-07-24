package com.example.chap02

fun main() {
    // let, run, apply, also   with
    // it -> 무조건 써야함
    // this -> 생략가능

    // 1. let
    val a = 3
    a.let {  }

    val user: User? = User("주성현", 26, true)

    // 수신객체.let { it ->
    // }
    // 마지막 줄이 return 값
    val age = user?.let {
        it.age
    }
    println(age)

    /**  */
    // 수신객체.run { this ->
    // }
    // 마지막 줄이 return 값
    val kid = User("아이", 4, false)
    val kidAge = kid.run {
        age
    }
    println(kidAge)

    // 수신객체.apply { this ->
    // }
    // return값이 수신객체 자기 자신 ( 전부다 )
    val female = User("슬기", 10, false)
    val femaleValue = female.apply {
        hasGlasses = false
    }
    println(femaleValue.hasGlasses)

    // 수신객체.also { it -> // local variable로 선언가능
    // } return값이 수신객체 자기 자신 ( 전부다 )
    val male = User("민수", 32, true, true)
    val maleValue = male.also {
        it.name
        it.hasGlasses = false
    }
    println(maleValue.hasGlasses)

    // with(수신객체)(this) {
    // }
    // 마지막 줄이 return 값
    val result = with(male) {
        hasGlasses = false
        true
    }
    println(result)
}

class User(
    val name: String,
    val age: Int,
    val gender: Boolean, // true: male, false: female
    var hasGlasses: Boolean = true
)