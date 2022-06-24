package io.study.kotlininaction._02

class KotlinPerson(
    val name: String,
    var isMarried: Boolean
)

fun main(args: Array<String>) {
    val person1 = KotlinPerson("Name", true)
    person1.isMarried = false
}