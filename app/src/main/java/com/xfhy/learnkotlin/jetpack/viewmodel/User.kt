package com.xfhy.learnkotlin.jetpack.viewmodel

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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
@Database(version = 2, entities = [User::class, Book::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao

    companion object {

        //更新时  需要建表
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Book(id integer primary key autoincrement not null,name text not null,pages integer not null)")
            }
        }
        //更新时  需要加字段
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table Book add column author text not null default 'unknown'")
            }
        }

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
            )
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build().apply { instance = this }
        }

    }

}
