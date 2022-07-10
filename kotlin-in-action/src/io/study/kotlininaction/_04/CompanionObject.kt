package io.study.kotlininaction._04

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

class UserWithCompanion private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = UserWithCompanion(email.substringBefore("@"))
        fun newFacebookUser(accountId: Int) = UserWithCompanion(getFacebookName(accountId))
    }

    fun getFacebookName(accountId: Int): String {
        return "$accountId"
    }


}

fun main() {
    A.bar()
}