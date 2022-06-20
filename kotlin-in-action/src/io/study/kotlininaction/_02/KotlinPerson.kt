package io.study.kotlininaction._02

class KotlinPerson(
    val name: String,
    var isMarried: Boolean
)

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}


fun main(args: Array<String>) {
    val person1 = KotlinPerson("Name", true)

    val rectangle = Rectangle(100, 100)
    println("rectangle is squre ? : ${rectangle.isSquare}")

    person1.isMarried = false
}