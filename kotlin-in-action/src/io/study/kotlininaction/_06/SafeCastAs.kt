package io.study.kotlininaction._06

/**
 * as? 연산자는 어떤 값을 지정한 타입으로 캐스트한다.
 * as? 는 값을 대상 타입으로 변환할 수 없으면 null을 반환한다.
 *
 */

class Person2(val firstName: String, val lastName: String) {

    override fun equals(other: Any?): Boolean {
        val otherPerson = other as? Person2 ?: return false
        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }

    override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
}