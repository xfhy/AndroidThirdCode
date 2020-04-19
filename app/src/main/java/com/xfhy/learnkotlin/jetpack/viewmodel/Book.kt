package com.xfhy.learnkotlin.jetpack.viewmodel

import androidx.room.*

/**
 * @author : xfhy
 * Create time : 2020-04-19 22:31
 * Description :
 */
@Entity
data class Book(var name: String, var pages: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Dao
interface BookDao {
    @Insert
    fun insert(book: Book): Long

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>
}