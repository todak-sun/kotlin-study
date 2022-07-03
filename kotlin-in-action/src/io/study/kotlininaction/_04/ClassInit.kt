package io.study.kotlininaction._04


// 간단한 클래스를 선언하는 방법

class User(val nickname: String)

/**
 * 클래스 이름 뒤에 오는 괄호로 둘러싸인 코드를 주 생성자(primary constructor)라고 부른다.
 */

class User2 constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }
}

class User3 {
    constructor(name: String) {

    }

    constructor(name: String, age: Int) {

    }
}

open class User4(_nickname: String) {
    val nickname = _nickname
}

class TwitterUser(nickname: String) : User4(nickname) {

}