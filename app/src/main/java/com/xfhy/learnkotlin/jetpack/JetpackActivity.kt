package com.xfhy.learnkotlin.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.xfhy.learnkotlin.R
import com.xfhy.learnkotlin.jetpack.lifecycles.MyObserver
import com.xfhy.learnkotlin.jetpack.viewmodel.JetpackViewModel
import com.xfhy.learnkotlin.jetpack.viewmodel.JetpackViewModelFactory
import kotlinx.android.synthetic.main.activity_jetpack.*

class JetpackActivity : AppCompatActivity() {

    private lateinit var jetpackViewModel: JetpackViewModel
    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)

        //添加生命周期观察
        val myObserver = MyObserver(lifecycle)
        lifecycle.addObserver(myObserver)
        myObserver.testGetLifecycleState()

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        jetpackViewModel = ViewModelProviders.of(this, JetpackViewModelFactory(countReserved))
            .get(JetpackViewModel::class.java)
        btnPlusOne.setOnClickListener {
            jetpackViewModel.plusOne()
        }

        btnClear.setOnClickListener {
            jetpackViewModel.clear()
        }

        //观察LiveData
        jetpackViewModel.counter.observe(this, Observer<Int> { count ->
            tvInfoText.text = count.toString()
        })
        /*jetpackViewModel.counter.observe(this) { count ->
            tvInfoText.text = count.toString()
        }*/
    }

    override fun onPause() {
        super.onPause()
        sp.edit().apply {
            putInt("count_reserved", jetpackViewModel.counter.value ?: 0)
            apply()
        }
    }
}
