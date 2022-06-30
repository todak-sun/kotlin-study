package io.study.kotlininaction._04

/**
 * 취약한 기반 클래스(fragile base class)
 * 하위 클래스가 기반 클래스에 대해 가졌던 가정이 기반 클래스를 변경함으로써 깨져버린 경우.
 *
 * 다시 말해, 부모 클래스의 의도와 다르게
 * 자식 클래스가 부모 클래스를 상속하여, 기존 의도와는 다르게 오버라이딩 될 경우
 * 많은 문제가 생긴다.
 *
 * 이를 막기 위해 코틀린의 class와 메서드는 기본적으로 final이다.
 */

interface Clickable2 {
    fun click()
    fun showOff() = println("I'm clickable!")
}

open class RichButton : Clickable2 { // 열려(open)있는 클래스. 상속 가능

    fun disable() {} // 이 메서드는 final이다. 하위 클래스가 이 메서드를 오브라이드 할 수 없다.

    open fun animate() {} // 이 함수는 열려있다. 하위 클래스에서 이 메서드를 오버라이드해도 된다.

    override fun click() { // 이 함수는 열려있는 메서드를 오버라이드 한다. 오버라이드한 메서드는 기본적으로 열려있다.
        TODO("Not yet implemented")
    }
    // 해당 메서드의 상속을 다시 막고싶다면, 앞에 final 키워드를 붙이면 된다.
}

abstract class Animated {
    abstract fun animate() // 추상 함수이므로, 하위 클래스에서 반드시 구현이 필요하다.

    open fun stopAnimating() {
        // 추상 클래스에 속했더라도, 비추상 함수는 기본적으로 파이널이지만 원한다면 open으로 오버라이드를 허용할 수 있다.
    }

    fun animateTwice() {
        // final이다.
    }

}


