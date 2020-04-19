package com.xfhy.learnkotlin.jetpack.viewmodel

import android.content.Context
import androidx.room.*

/**
 * @author : xfhy
 * Create time : 2020-04-19 20:54
 * Description :
 */
@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    //主键  主键自动生成
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}


//所有访问数据库的操作都在Dao中封装
@Dao
interface UserDao {

    //插入时会将主键ID返回
    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Delete
    fun deleteUser(user: User)

    //:lastName 表示引用形参
    @Query("delete from User where lastName=:lastName")
    fun deleteUserByLastName(lastName: String): Int
}


//Room 底层会去自动实现
@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build().apply { instance = this }
        }

    }

}
