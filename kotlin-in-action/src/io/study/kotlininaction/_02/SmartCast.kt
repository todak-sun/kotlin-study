package io.study.kotlininaction._02

interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e // is 검사하면 컴파일러가 알아서 캐스팅을 해준다. 따라서, 자바에서 instanceof를 썼을때처럼 명시적으로 캐스팅을 해주지 않아도 된다.
        return n.value
    }
    if (e is Sum) {
        return eval(e.right) + eval(e.left)
    }
    throw IllegalArgumentException("Unknown expression")
}

fun eval2(e: Expr): Int = if (e is Num) {
    e.value
} else if (e is Sum) {
    eval2(e.right) + eval2(e.left)
} else {
    throw IllegalArgumentException("Unknown expression")
}

fun eval3(e: Expr): Int = when (e) {
    is Num -> e.value
    is Sum -> eval3(e.right) + eval3(e.left)
    else -> throw IllegalArgumentException("Unknown expression")
}

fun evalWithLogging(e: Expr): Int = when (e) {
    is Num -> {
        println("num: ${e.value}")
        e.value
    }
    is Sum -> {
        val left = evalWithLogging(e.left)
        val right = evalWithLogging(e.right)
        println("sum: $left + $right")
        left + right
    }
    else -> throw IllegalArgumentException("Unknown expression")
}

fun main() {
    // (1 + 2) + 4
    val result = evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4)))
    print("result = $result")
}