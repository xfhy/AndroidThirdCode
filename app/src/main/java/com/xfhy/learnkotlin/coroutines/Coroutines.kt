package com.xfhy.learnkotlin.coroutines

import android.util.Log
import com.xfhy.learnkotlin.common.HttpCallbackListener
import com.xfhy.learnkotlin.common.HttpUtil
import com.xfhy.learnkotlin.common.request
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author : xfhy
 * Create time : 2020-04-14 22:50
 * Description :
 */
fun main() {
    /*GlobalScope.launch {
        println("codes run is coroutine scope")
        //这里不是主线程
        println(Thread.currentThread().name)

        delay(1000)
        println("延迟之后的输出")

    }*/

    //主线程
    //println(Thread.currentThread().name)
    //Thread.sleep(50)

    /*runBlocking {

        println("延迟之前的输出")

        delay(2000)
        println("延迟之后的输出")
    }*/

    //创建多个协程
    /*runBlocking {
        launch {
            println("launch1 ${Thread.currentThread().name}")
            delay(1000)
            println("launch1 finished")
        }
        launch {
            println("launch2 ${Thread.currentThread().name}")
            delay(1000)
            println("launch2 finished")
        }
    }*/

    /*val startTime = System.currentTimeMillis()
    runBlocking {
        repeat(100000) {
            launch {
                println(".")
            }
        }
    }
    val endTime = System.currentTimeMillis()
    println("时间 = ${endTime - startTime}")*/

    //创建100000个线程   没打印完就结束了,
    /*val startTime = System.currentTimeMillis()
    repeat(100000) {
        thread {
            println(".")
        }
    }
    val endTime = System.currentTimeMillis()
    println("时间 = ${endTime - startTime}")*/

    /*runBlocking {
        launch {
            printDot()
        }
    }

    println("main 中的输出")

    val job = GlobalScope.launch { }
    job.cancel()*/

    /*val job = Job()
    //返回的是CoroutineScope对象  这里是调用的CoroutineScope方法
    val scope = CoroutineScope(job)
    scope.launch {

    }*/

    /*runBlocking {
        val result = async {
            delay(100)
            5 + 5
        }.await()
        println(result)
    }*/

    /*runBlocking {
       val start = System.currentTimeMillis()
       val deferred1 = async {
           delay(1000)
           5 + 5
       }
       val deferred2 = async {
           delay(1000)
           6 + 6
       }
       println("结果是 ${deferred1.await() + deferred2.await()}")
       val end = System.currentTimeMillis()
       println("花费时间: ${end - start}")
   }*/

    runBlocking {
        val result = withContext(Dispatchers.Default) {
            5 + 5
        }
        println(result)
        val request = request("https://www.baidu.com")
    }

}






suspend fun printDot() {
    coroutineScope {
        launch {
            println(".")
            delay(1000)
            println("延迟之后的输出")
        }
    }
}
