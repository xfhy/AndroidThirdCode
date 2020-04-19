package com.xfhy.learnkotlin.jetpack.viewmodel

import androidx.lifecycle.ViewModel

/**
 * @author : xfhy
 * Create time : 2020-04-19 14:47
 * Description :
 */
class JetpackViewModel(countReserved: Int) : ViewModel() {

    var counter = countReserved

}