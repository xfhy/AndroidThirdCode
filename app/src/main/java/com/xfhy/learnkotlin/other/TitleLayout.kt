package com.xfhy.learnkotlin.other

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.xfhy.learnkotlin.R

/**
 * @author : xfhy
 * Create time : 2020-04-08 23:10
 * Description :
 */
class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.activity_main, this)
    }

}