package com.xfhy.learnkotlin.`fun`

/**
 * @author : xfhy
 * Create time : 2020-04-07 07:30
 * Description :
 */

class User(var name: String, private val age: Int) {
    fun agePrint() {
        println(age)
    }
}

fun main() {
    val user = User("张三", 22)

    //let和run都会返回闭包的执行结果,区别在于let有闭包参数,而run没有
    val letResult = user.let { "let 输出点东西 ${it.name}" }
    println(letResult)
    //run 可以使用this访问 user的公有变量
    val runResult = user.run { "run 输出点东西 ${this.name}" }
    println(runResult)

    //also和apply都不返回闭包的执行结果,返回的是当前执行的对象,这里返回的是user
    //also有闭包参数,而apply没有闭包参数
    user.also { it.name }.apply { this.name }.also { it.name }.also { it.name }

    //takeIf的闭包返回一个判断结果,为false时,takeIf会返回空
    //takeUnless与takeIf刚好相反,闭包的判断结果,为true时函数会返回空
    user.takeIf { it.name.isNotEmpty() }?.also { println("姓名为${it.name}") } ?: println("姓名为空")
    user.takeUnless { it.name.isNotEmpty() }?.also { println("姓名为空") } ?: println("姓名为${user.name}")

    //重复执行当前包  it是当前的次数(0..4)
    repeat(5) {
        println("重复输出 ${user.name} $it")
    }

    //with 比较特殊,不是以扩展方法的形式存在的,而是一个顶级函数
    //eg: 可以在这里传入一个View,然后赋值一些初始化什么的
    with(user) {
        this.name = "lisi"
    }

}
