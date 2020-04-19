package com.xfhy.learnkotlin.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author : xfhy
 * Create time : 2020-04-19 21:00
 * Description :
 */
object Repository {

    /**
     * 模拟  获取用户数据by userId
     */
    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }

}