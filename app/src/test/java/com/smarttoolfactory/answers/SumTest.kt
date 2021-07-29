package com.smarttoolfactory.answers

import com.google.common.truth.Truth
import org.junit.Test


class SumTest {

    @Test
    fun sumTest() {
        val result: Int = sumOfMultiplies(3, 5, 15)
        Truth.assertThat(result).isEqualTo(45)
    }

    @Test
    fun sumTest2() {
        val result: Int = sumOfMultiplies(2, 5, 15)
        // 2, 4, 5, 6 8, 12, 14, 15
        Truth.assertThat(result).isEqualTo(66)
    }

}