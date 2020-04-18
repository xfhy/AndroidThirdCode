package com.xfhy.learnkotlin.material

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.xfhy.learnkotlin.R
import com.xfhy.learnkotlin.common.startActivity
import com.xfhy.learnkotlin.common.toast
import kotlinx.android.synthetic.main.activity_material_home.*

class MaterialHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_home)

        //让Toolbar保持与ActionBar的外观一致
        setSupportActionBar(toolbar)
        btn_go_drawerlayout.setOnClickListener {
            startActivity<DrawerLayoutActivity>()
        }
    }

    //加载toolbar.xml菜单文件
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    //处理点击事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ic_backup -> toast("备份")
            R.id.ic_delete -> toast("删除")
            R.id.ic_settings -> toast("设置")
        }
        return true
    }

}
