package com.xfhy.learnkotlin.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author : xfhy
 * Create time : 2020-04-20 22:51
 * Description :
 */
class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        //doWork 不会允许在主线程中
        Log.d("SimpleWorker", "do work in SimpleWorker ,thread = ${Thread.currentThread().name}")
        return Result.success()
    }
}