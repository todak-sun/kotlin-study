package io.study.kotlininaction._02

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int) = if (a > b) a else b

fun main(args: Array<String>) {
    println(max(1, 3))
    println(max2(1, 3))
}



