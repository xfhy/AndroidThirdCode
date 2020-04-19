package com.xfhy.learnkotlin.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.xfhy.learnkotlin.R
import com.xfhy.learnkotlin.common.toast
import com.xfhy.learnkotlin.material.adapter.Fruit
import com.xfhy.learnkotlin.material.adapter.FruitAdapter
import kotlinx.android.synthetic.main.activity_drawer_layout.*

/**
 * 滑动菜单
 * 2020年04月18日15:38:37
 * xfhy
 */
class DrawerLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_layout)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            //让导航按钮展示出来
            it.setDisplayHomeAsUpEnabled(true)
            //设置导航按钮图标
            it.setHomeAsUpIndicator(R.drawable.icon_menu)
        }
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        fabTest.setOnClickListener {
            //toast("FAB")
            Snackbar.make(fabTest, "设置", Snackbar.LENGTH_SHORT).setAction("Undo") {
                toast("取消")
            }.show()
        }

        rvFruits.layoutManager = GridLayoutManager(this,2)
        rvFruits.adapter = FruitAdapter(this, getData())
    }

    private fun getData(): List<Fruit> {
        return listOf(
            Fruit("Apple", R.drawable.apple),
            Fruit("Banana", R.drawable.banana),
            Fruit("Watermelon", R.drawable.watermelon),
            Fruit("Pear", R.drawable.pear),
            Fruit("Grape", R.drawable.grape),
            Fruit("Pineapple", R.drawable.pineapple),
            Fruit("Strawberry", R.drawable.strawberry),
            Fruit("Cherry", R.drawable.cherry),
            Fruit("Mango", R.drawable.mango)
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            else -> {
            }
        }
        return true
    }

}
