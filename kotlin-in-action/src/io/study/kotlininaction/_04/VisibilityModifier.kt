package io.study.kotlininaction._04

interface Focusable2 {}

internal open class TalkativeButton : Focusable2 {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

/*
    fun TalkativeButton.givenSpeech() { // ERROR: public 멤버가 자신의 internal 수신 타입인 TalkativeButton을 노출.
        yell() // ERROR: yell에 접근할 수 없음. yell은 TalkativeButton의 private 멤버임
        whisper() // ERROR: whisper는 TalkativeButton의 protected 멤버임
    }

    protected 멤버는 오직 어떤 클래스나 그 클래스를 상속한 클래스 안에서만 보인다.
    클래스를 확장한 함수는 그 클래스의 private이나 protected 멤버에 접근할 수 없다.
*/