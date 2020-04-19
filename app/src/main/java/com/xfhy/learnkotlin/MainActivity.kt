package com.xfhy.learnkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xfhy.learnkotlin.common.request
import com.xfhy.learnkotlin.common.startActivity
import com.xfhy.learnkotlin.jetpack.JetpackActivity
import com.xfhy.learnkotlin.material.MaterialHomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_request_baidu.setOnClickListener {
            GlobalScope.launch {
                Log.d("xfhy", Thread.currentThread().name)
                val response = request("https://www.baidu.com/")
                Log.d("xfhy", "网络请求结果 : $response")
            }
        }
        tvGoMaterial.setOnClickListener {
            startActivity<MaterialHomeActivity>()
        }
        tvGoJetpack.setOnClickListener {
            startActivity<JetpackActivity>()
        }
    }


}

