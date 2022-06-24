package io.study.kotlininaction._02

fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, ${name}님 반가워요!")
    println("문자에 \$를 넣고 싶다면 이렇게")
}