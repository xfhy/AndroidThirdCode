package com.xfhy.learnkotlin.by

import kotlin.reflect.KProperty

/**
 * @author : xfhy
 * Create time : 2020-04-12 09:07
 * Description :
 */
fun <T> later(block: () -> T) = Later(block)

class Later<T>(val block: () -> T) {
    var value: Any? = null

    operator fun getValue(any: Any?, prop: KProperty<*>): T {
        if (value == null) {
            value = block()
        }
        return value as T
    }

}