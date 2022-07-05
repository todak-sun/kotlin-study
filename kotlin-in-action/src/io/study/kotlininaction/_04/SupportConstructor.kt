package io.study.kotlininaction._04

interface Context

interface AttributeSet

/**
 * 대부분의 경우, 코틀린의 디폴트 파라미터 값과 이름 붙인 인자 문법을 사용해
 * 생성자 오버로드를 안해도 되지만 해야할 때가 있다.
 */
open class View2 {
    constructor(ctx: Context) {
        // 코드
    }

    constructor(ctx: Context, attr: AttributeSet) {
        // 코드
    }
}

class MyButton : View2 {
    constructor(ctx: Context) : super(ctx) {

    }

    constructor(ctx: Context, attr: AttributeSet) : super(ctx, attr) {

    }
}

/**
 * 클래스에 주 생성자가 없다면 모든 부 생성자는 반드시 상위 클래스를 초기화하거나
 * 다른 생성자에게 생성을 위임해야 한다.
 */