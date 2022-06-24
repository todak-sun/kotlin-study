package io.study.kotlininaction._02.imports

import io.study.kotlininaction._02.Rectangle
import io.study.kotlininaction._02.createRandomRectangle

fun main() {
    val rectangle = Rectangle(100, 100)
    println("rectangle is squre ? : ${rectangle.isSquare}")

    var randomRectangle = createRandomRectangle()
    println("randomRectangle is squre ? : ${rectangle.isSquare}")
}