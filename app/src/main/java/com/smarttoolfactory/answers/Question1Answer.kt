package com.smarttoolfactory.answers

fun main() {
    val result = sumOfMultiplies(3, 5, 15)
    println("RESULT: $result")
}

/**
 * Sums numbers between 1 and [index] included that are multiples
 * of either [multiply1] or [multiply1] but not both.
 */
fun sumOfMultiplies(multiply1: Int, multiply2: Int, index: Int): Int {

    if (index < 1) throw ArithmeticException("Index cannot be lower than 1")

    if (multiply1 < 1 || multiply2 < 1) throw ArithmeticException("Multiplies cannot be lower than 1")

    val multiply = multiply1 * multiply2

    var sum = 0

    for (i in 1..index) {
        if ((i % multiply1 == 0 || i % multiply2 == 0) && i % multiply != 0) {
            sum += i
        }
    }

    return sum
}