package io.study.kotlininaction._02

import java.util.*

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return this.height == this.width
        }
}


fun createRandomRectangle(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}

