package com.xfhy.learnkotlin.by

import kotlin.concurrent.thread

/**
 * @author : xfhy
 * Create time : 2020-04-12 08:46
 * Description :
 */

//MySet中有所有Set接口中的功能,和HashSet保持一致, 并且isEmpty是自己实现的
class MySet<T>(val helperSet: HashSet<T>) : Set<T> by helperSet {

    val p by lazy { "a" }
    val p2 by later { "a" }

    fun hello() = println("hello")

    //演示,,,,
    override fun isEmpty(): Boolean {
        return true
    }

}


fun main() {

    thread {

    }

    val mySet = MySet(hashSetOf(1, 2, 3))
    mySet.hello()
    println(mySet.isEmpty())
}
