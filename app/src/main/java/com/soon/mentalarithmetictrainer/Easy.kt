package com.soon.mentalarithmetictrainer

import kotlin.random.Random

object Easy {

    fun getQuestion(): Pair<String, Int> {
        val num1 = Random.nextInt(1, 10)
        val num2 = Random.nextInt(1, 10)
        val operator = arrayOf("+", "-", "x", "/").random()

        val problem: String
        val result: Int

        when (operator) {
            "+" -> {
                problem = "$num1 $operator $num2"
                result = num1 + num2
            }
            "-" -> {
                problem = "$num1 $operator $num2"
                result = num1 - num2
            }
            "x" -> {
                problem = "$num1 $operator $num2"
                result = num1 * num2
            }
            "/" -> {
                val quotient = Random.nextInt(1, 10)
                val dividend = quotient * num2
                problem = "$dividend $operator $num2"
                result = quotient
            }
            else -> {
                problem = "Invalid Operator"
                result = 777777777
            }
        }

        return Pair(problem, result)
    }
}
