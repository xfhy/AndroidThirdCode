package com.xfhy.learnkotlin.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @author : xfhy
 * Create time : 2020-04-19 15:08
 * Description : 借助ViewModelProvider.Factory可以向ViewModel构造函数传递参数
 */
class JetpackViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JetpackViewModel(countReserved) as T
    }
}