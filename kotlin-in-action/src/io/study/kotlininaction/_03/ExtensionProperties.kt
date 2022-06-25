package io.study.kotlininaction._03

val String.lastChar2: Char
    get() = get(length - 1)


var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

fun main() {
    println("\"Kotlin\".lastChar2 => ${"Kotlin".lastChar2}")
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    print(sb)
}