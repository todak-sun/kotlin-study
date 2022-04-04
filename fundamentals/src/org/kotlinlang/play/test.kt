package org.kotlinlang.play

fun main() {

    infix fun Int.times(str: String) = str.repeat(this)
    println(2 times "Bye ")
    val pair = "Ferrari" to "Katrina";
    println(pair)

    infix fun String.onto(other: String) = Pair(this, other)
    val myPair = "McLaren" onto "Lucas"
    println(myPair)



    println("Hello, World")

    printMessageWithPrefix("Hello, World")
}

fun sum(x: Int, y: Int): Int {
    return x + y
}

fun sum2(x: Int, y: Int) = x * y


fun printMessageWithPrefix(message: String, prefix: String = "Info") {
    println("[$prefix] $message")
}