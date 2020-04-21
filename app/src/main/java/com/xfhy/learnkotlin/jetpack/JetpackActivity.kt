package com.xfhy.learnkotlin.jetpack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.xfhy.learnkotlin.R
import com.xfhy.learnkotlin.jetpack.lifecycles.MyObserver
import com.xfhy.learnkotlin.jetpack.viewmodel.AppDatabase
import com.xfhy.learnkotlin.jetpack.viewmodel.JetpackViewModel
import com.xfhy.learnkotlin.jetpack.viewmodel.JetpackViewModelFactory
import com.xfhy.learnkotlin.jetpack.viewmodel.User
import com.xfhy.learnkotlin.jetpack.workmanager.SimpleWorker
import kotlinx.android.synthetic.main.activity_jetpack.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

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

        //---------测试switchMap----------
        btnGetUser.setOnClickListener {
            val userId = (0..10000).random().toString()
            jetpackViewModel.getUser(userId)
        }
        jetpackViewModel.user.observe(this) { user ->
            tvInfoText.text = user.firstName
        }

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Bda", 18)
        val user2 = User("Tom", "Hands", 18)
        btnAddData.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        btnUpdate.setOnClickListener {
            thread {
                user1.age = 19
                userDao.updateUser(user1)
            }
        }
        btnDelete.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Bda")
            }
        }
        btnQueryData.setOnClickListener {
            thread {
                val loadAllUsers = userDao.loadAllUsers()
                runOnUiThread {
                    tvInfoText.text = loadAllUsers.joinToString()
                }
            }
        }

        btnDoWork.setOnClickListener {
            //单次任务
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()
            // 周期性后台任务   最短15分钟
            val requestManyTime =
                PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()
            WorkManager.getInstance(this).enqueue(request)
        }

    }

    override fun onPause() {
        super.onPause()
        sp.edit().apply {
            putInt("count_reserved", jetpackViewModel.counter.value ?: 0)
            apply()
        }
    }
}
