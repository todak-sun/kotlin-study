package io.study.kotlininaction._03

fun String.lastChar(): Char = this[this.length - 1]


fun <T> Collection<T>.joinToString(separator: String = ", ", prefix: String = "", postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, char) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(char)
    }
    result.append(postfix)
    return result.toString()
}

open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

fun main() {
    val lastChar = "kotlin".lastChar()
    println("lastChar : $lastChar")
    println("=".repeat(50))

    println(listOf("Hello", "World", "Haha").joinToString("="))
    println("=".repeat(50))

    val view: View = Button()
    view.click()
    println("=".repeat(50))

    val view2: View = Button()
    view2.showOff()
    println("=".repeat(50))

}