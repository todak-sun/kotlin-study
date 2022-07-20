package io.study.kotlininaction._06

fun sendEmailTo(email: String) {

}

fun main() {
    val email: String? = "tjsdydwn@gmail.com";

    email?.let { email -> sendEmailTo(email) }
}