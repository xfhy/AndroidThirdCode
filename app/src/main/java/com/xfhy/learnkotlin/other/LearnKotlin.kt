package com.xfhy.learnkotlin.other

import java.lang.StringBuilder

/**
 * @author : xfhy
 * Create time : 2020-04-06 15:46
 * Description :
 */

fun main() {

//    print(largeNumber(1, 3))

    rangeTest()

    val list = listOf("Apple", "Banana")
    println(list.maxBy { it.length })
    println(list.map { it.toUpperCase() })
    println(list.filter { it.length > 2 })
    //是否存在一个
    println(list.any { it.length > 6 })
    //是否全部都满足
    println(list.all { it.length < 3 })

    /*Thread {
        println("我是子线程")
    }.start()*/

    doStudy()

    val withResult = with(StringBuilder()) {
        for (s in list) {
            append(s).append(" ,")
        }
        append("Ate all fruits")
        toString()
    }
    println(withResult)

    var runResult = StringBuilder().run {
        for (s in list) {
            append(s).append(" ,")
        }
        append("Ate all fruits")
        toString()
    }
    println(runResult)

    val applyResult = StringBuilder().apply {
        for (s in list) {
            append(s).append(" ,")
        }
        append("Ate all fruits")
    }.toString()
    println(applyResult)

}

fun doStudy(study: Study? = null) {
    println(study?.name)
}

fun getTextLength(text: String?): Int {
    return text?.length ?: 0
}

class Study {
    val name = "test"
}

fun largeNumber(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 76
    else -> 0
}

fun getScore1(name: String) = when {
    name.startsWith("ss") -> 89
    name == "aa" -> 86
    else -> 0
}

fun rangeTest() {
    val range = 0..10
    for (i in range) {
        print(i)
    }
    println()

    val untilTest = 0 until 10
    for (i in untilTest) {
        print(i)
    }

}

