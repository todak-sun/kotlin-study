package io.study.kotlininaction._02

import java.io.BufferedReader
import java.io.StringReader

fun setPercentage(percentage: Int) {
    if (percentage !in 0..100) {
        throw IllegalArgumentException("A percentage value must be between 0 and 100: $percentage")
    }
}

fun setPercentage2(number: Int) {
    val percentage2 =
        if (number in 0..100) number
        else throw IllegalArgumentException("A percentage value must be between 0 and 100: $number")
}

fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }
    println(number)
}

fun main() {
    val reader = BufferedReader(StringReader("2389"))
    println(readNumber(reader))

    val reader2 = BufferedReader(StringReader("Not a Number"))
    readNumber2(reader2)

}