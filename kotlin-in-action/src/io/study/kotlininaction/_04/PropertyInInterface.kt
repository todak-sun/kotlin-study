package io.study.kotlininaction._04

interface User5 {
    val nickname: String
    // User5 인터페이스를 구현하는 클래스가 nickname의 값을 얻을 수 있는 방법을 제공해야 한다는 뜻.
}

class PrivateUser(override val nickname: String) : User5 {}

class SubscribingUser(val email: String) : User5 {
    override val nickname: String
        get() = email.substringBefore('@') // 커스텀 게터
}

class FacebookUser(val accountId: Int) : User5 {

    override val nickname = getFacebookName(accountId) // 프로퍼티 초기화 식

    fun getFacebookName(accountId: Int): String {
        return "$accountId is your name"
    }
}