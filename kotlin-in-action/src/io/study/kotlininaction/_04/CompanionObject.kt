package io.study.kotlininaction._04

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

fun getFacebookName(accountId: Int) = "fb:$accountId"
class UserWithCompanion private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = UserWithCompanion(email.substringBefore("@"))
        fun newFacebookUser(accountId: Int) = UserWithCompanion(getFacebookName(accountId))
    }

}

fun main() {
    A.bar()

    val subscribingUser = UserWithCompanion.newSubscribingUser("bob@gmail.com")
    val facebookUser = UserWithCompanion.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(facebookUser.nickname)
}