package io.study.kotlininaction._04

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person2(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Person2 = Person2(jsonText)
    }

//    companion object : JSONFactory<Person2> {
//        override fun fromJSON(jsonText: String): Person2 {
//            return Person2(jsonText)
//        }
//    }
}

fun main() {

}