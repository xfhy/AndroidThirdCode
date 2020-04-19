package com.xfhy.learnkotlin.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * @author : xfhy
 * Create time : 2020-04-19 14:47
 * Description :
 */
class JetpackViewModel(countReserved: Int) : ViewModel() {

    //不能将MutableLiveData暴露给外部,这样外部可随意修改里面的数据,破坏了ViewModel的封装
    val counter: LiveData<Int>
        get() = _counter

    //数据变化时 会 通知观察者
    private val _counter = MutableLiveData<Int>()

    private val userLiveData = MutableLiveData<User>()

    //这里假设: 需求是只需要用户名,所以不需要把User实例全部暴露出去
    //利用Transformations.map转换一下
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }


}