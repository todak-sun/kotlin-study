package io.study.kotlininaction._03


fun main() {
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    println("map : $map")

    val (number, name) = 1 to "one"
    println("number : $number, name : $name")

}

