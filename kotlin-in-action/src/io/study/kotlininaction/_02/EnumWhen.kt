package io.study.kotlininaction._02

import io.study.kotlininaction._02.Color.*

enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}

fun getMnemonic(color: Color) = when (color) {
    RED -> "Richard"
    ORANGE -> "Of"
    YELLOW -> "York"
    GREEN -> "Gave"
    BLUE -> "Battle"
    INDIGO -> "In"
    VIOLET -> "Vain"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    else -> throw Exception("Dirty color")
}

fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == YELLOW) || (c2 == RED && c1 == YELLOW) -> ORANGE
        (c1 == YELLOW && c2 == BLUE) || (c2 == YELLOW && c1 == BLUE) -> GREEN
        (c1 == BLUE && c2 == VIOLET) || (c2 == BLUE && c1 == VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")
    }

fun getWarmth(color: Color) = when (color) {
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}


fun main() {
    println(BLUE.rgb())
    println(getMnemonic(BLUE))
    println(getWarmth(ORANGE))
    println(mix(RED, YELLOW))
    println(mixOptimized(RED, YELLOW))
}




