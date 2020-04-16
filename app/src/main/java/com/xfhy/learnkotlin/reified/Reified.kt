package com.xfhy.learnkotlin.reified

/**
 * @author : xfhy
 * Create time : 2020-04-13 23:16
 * Description :
 */

inline fun <reified T> getGenericType() = T::class.java

fun main() {
    val result1 = getGenericType<Int>()
    val result2 = getGenericType<String>()

    println(result1)
    println(result2)
}
