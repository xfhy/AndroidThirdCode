package com.xfhy.learnkotlin.high

import java.lang.StringBuilder

/**
 * @author : xfhy
 * Create time : 2020-04-11 18:59
 * Description :
 */

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

//给StringBuilder增加扩展方法build,需要传入一个高阶函数,这个高阶函数只作用于StringBuilder,所以需要写成StringBuilder.() -> Unit,这样可以在里面访问到StringBuilder的上下文
fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder {
    block()
    return this
}

fun main() {
    val result1 = num1AndNum2(1, 2) { n1, n2 ->
        n1 + n2
    }
    val result2 = num1AndNum2(2, 5) { n1, n2 ->
        n1 - n2
    }
    println(result1)
    println(result2)

    val list = listOf("Apple", "Banana")
    val result = StringBuilder().build {
        append("Start\n")
        for (s in list) {
            append(s).append("\n")
        }
        append("end")
    }

    println(StringBuilder().apply {
        append("Start\n")
        for (s in list) {
            append(s).append("\n")
        }
        append("end")
    })

}