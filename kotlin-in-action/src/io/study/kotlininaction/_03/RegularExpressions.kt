package io.study.kotlininaction._03


fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePath2(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)

    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}

fun main() {
    val string = "12.345-6.A"
    val splitWithRegex = string.split("[.\\-]".toRegex())
    println("split : $splitWithRegex")
    println("=".repeat(50))

    val splitWithString = string.split(".", "-")
    println("split : $splitWithString")
    println("=".repeat(50))

    parsePath("/Users/yole/kotlin-book/chapter.adoc")
    println("=".repeat(50))

    parsePath2("/Users/yole/kotlin-book/chapter.adoc")
}