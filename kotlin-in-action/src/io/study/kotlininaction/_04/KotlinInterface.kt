package io.study.kotlininaction._04

interface Clickable {
    fun click()

    // default 메서드
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFoucs(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    // 클래스 이름 뒤에 콜론(:)을 붙이고, 인터페이스와 클래스 이름을 적는 것으로 클래스 확장과 인터페이스 구현을 모두 처리함.
    override fun click() = println("I was clicked")

    // 메서드 시그니쳐가 동일한 인터페이스가 있으면, 직접 구현을 해줘야 한다. 그렇지 않으면 컴파일 되지 않는다.
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main() {
    val button = Button();
    button.showOff()
    button.setFoucs(true)
    button.click()
}