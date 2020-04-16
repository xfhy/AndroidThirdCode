package com.xfhy.learnkotlin.common

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * @author : xfhy
 * Create time : 2020-04-16 23:54
 * Description :
 */

inline fun <reified T> Activity.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

/**
 * InputSteam转String
 */
fun InputStream.getStrContent(): String {
    val sb = StringBuilder()
    val br = BufferedReader(InputStreamReader(this))
    val readLines = br.readLines()
    for (readLine in readLines) {
        sb.append(readLine)
    }
    return sb.toString()
}

/**
 * 利用suspendCoroutine简化网络请求中的回调
 */
suspend fun request(address: String): String {
    return suspendCoroutine { continuation ->
        HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
            override fun onFinish(response: String) {
                continuation.resume(response)
            }

            override fun onError(e: Exception) {
                continuation.resumeWithException(e)
            }
        })
    }
}