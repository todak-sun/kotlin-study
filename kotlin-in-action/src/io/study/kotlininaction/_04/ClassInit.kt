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

open class Button2
// 인자가 없는 디폴트 생성자가 만들어진다

// 클래스를 상속하는 경후 괄호가 들어가며, 인터페이스를 구현한 경우 괄호가 들어가지 않는다.
// 이를 보고 클래스를 구현했는지, 인터페이스를 구현했는지 알 수 있다.

class Secretive private constructor() {}
// 이 클래스의 유일한 주 생성자는 비공개다.
// 주 생성자는 비공개이므로 외부에서는 Secretive를 인스턴스화할 수 없다.
// 동반 객체 (companion object)에 대해 설명하면서 동반 객체 안에서 이런 비공개 생성자를 호출하면 좋다.