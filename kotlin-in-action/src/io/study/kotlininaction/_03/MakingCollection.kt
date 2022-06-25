package io.study.kotlininaction._03

val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

fun main() {
    println("set => $set, javaClass => ${set.javaClass}")
    println("=".repeat(50))
    println("list => $list, javaClass => ${list.javaClass}")
    println("=".repeat(50))
    println("map => $map, javaClass => ${map.javaClass}")

    val strings = listOf("first", "second", "fourteenth")
    println("last of strings => ${strings.last()}")
    println("=".repeat(50))

    val numbers = setOf(1, 14, 2)
    println("max of numbers => ${numbers.max()}")





}