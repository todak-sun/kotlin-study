package com.group.libraryapp.calcurator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CalculatorTest {

    @Test
    fun addTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        assertEquals(8, calculator.number)
    }

    @Test
    fun minusTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(2)

        // then
        assertEquals(3, calculator.number)
    }

    @Test
    fun multiplyTest() {
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(5)

        // then
        assertEquals(25, calculator.number)
    }

    @Test
    fun divideTest() {
        // given
        val calculator = Calculator(10)

        // when
        calculator.divide(2)

        // then
        val exception = assertThrows<IllegalArgumentException> { calculator.divide(0) }
        assertEquals("0으로 나눌 수 없습니다.", exception.message)
        assertEquals(5, calculator.number)
    }

}