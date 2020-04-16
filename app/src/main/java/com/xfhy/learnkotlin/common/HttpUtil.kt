package com.xfhy.learnkotlin.common

import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

/**
 * @author : xfhy
 * Create time : 2020-04-16 23:55
 * Description :
 */
object HttpUtil {
    fun sendHttpRequest(address: String, httpCallbackListener: HttpCallbackListener) {
        try {
            val urlObject = URL(address)
            //得到connection对象。
            val connection = urlObject.openConnection() as HttpsURLConnection
            //设置请求方式
            connection.requestMethod = "GET"
            //连接
            connection.connect()
            //得到响应码
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流 并将InputStream转String  getStrContent是我写的扩展方法
                httpCallbackListener.onFinish(connection.inputStream.getStrContent())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            httpCallbackListener.onError(e)
        }
    }
}

interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}
