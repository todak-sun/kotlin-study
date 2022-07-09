package io.study.kotlininaction._04

import java.io.File

data class Person(val name: String, val age: Int) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int = o1.name.compareTo(o2.name)
    }
}

/**
 * 코틀린은 객체 선언 기능을 통해 싱글턴은 언어에서 기본 지원한다.
 * 객체 선언은 클래스 선언과 그 클래스에 속한 단일 인스턴스의 선언을 합친 선언이다.
 *
 */

object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmployees) {
            println(person)
        }
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }

}

fun main() {
    Payroll.allEmployees.add(Person("선용주", 32))
    Payroll.calculateSalary()

//    println(Person("선용주", 32) ===  Person("강다혜", 27))

}