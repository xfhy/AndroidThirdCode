package com.xfhy.learnkotlin.sealed

import java.lang.Exception

/**
 * @author : xfhy
 * Create time : 2020-04-09 23:07
 * Description :
 */
sealed class Result

class Success(val msg: String) : Result()
class Failure(val error: Exception) : Result()

fun getResultMsg(result: Result) = when (result) {
    is Success -> result.msg
    is Failure -> "Error is ${result.error.message}"
}

class TestExpand(private val name: String, val age: Int)

fun TestExpand.expandMethod() {
    //扩展函数不能访问原有类的私有属性
    println(age)
}

fun main() {
}
