package io.study.kotlininaction._03

fun main(args: Array<String>) {
    val list = listOf("Args : ", *args)
    // * 는 코틀린에서 스프레드 연산자이다.
    println()
}