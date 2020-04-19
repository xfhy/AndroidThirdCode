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

    //-----------map-------------
    private val userLiveData = MutableLiveData<User>()

    //这里假设: 需求是只需要用户名,所以不需要把User实例全部暴露出去
    //利用Transformations.map转换一下
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }
    //-----------map   end-------------

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

    //如果ViewModel中的某个LiveData对象是调用另外的方法获取的,那么我们就可以借助switchMap方法,将这个LiveData对象转换成另外一个可观察的LiveData对象
    private val userIdLiveData = MutableLiveData<String>()
    //在Activity中只需要观察这个user对象即可
    //因为Repository.getUser每次返回的都是不同的LiveData对象,所以不能直接在Activity里面直接观察它返回的LiveData,而需要通过switchMap转换一下
    //switchMap每次将Repository.getUser返回的LiveData转换成下面这个user(LiveData),于是Activity就可以观察成功
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }

}