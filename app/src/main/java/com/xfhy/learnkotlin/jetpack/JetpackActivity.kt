package com.xfhy.learnkotlin.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.xfhy.learnkotlin.R
import com.xfhy.learnkotlin.jetpack.viewmodel.JetpackViewModel
import kotlinx.android.synthetic.main.activity_jetpack.*

class JetpackActivity : AppCompatActivity() {

    private lateinit var jetpackViewModel: JetpackViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)

        //
        jetpackViewModel = ViewModelProviders.of(this).get(JetpackViewModel::class.java)
        btnPlusOne.setOnClickListener {
            jetpackViewModel.counter++
            refreshCounter()
        }
        refreshCounter()
    }

    private fun refreshCounter() {
        tvInfoText.text = jetpackViewModel.counter.toString()
    }
}
