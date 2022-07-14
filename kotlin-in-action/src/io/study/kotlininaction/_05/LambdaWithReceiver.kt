package io.study.kotlininaction._05

fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know alphabet!")
    return result.toString()
}