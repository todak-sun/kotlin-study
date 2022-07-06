package io.study.kotlininaction._04

class User6(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println(
                """
                Address was changed for $name: "$field" -> $value".
            """.trimIndent()
            )
            field = value
        }
}

class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}

fun main() {
    val user = User6("Alice")
    user.address = "장안동 128-1"

}
