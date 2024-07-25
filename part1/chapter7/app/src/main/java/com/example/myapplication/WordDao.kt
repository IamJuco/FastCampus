package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao {
    @Query("SELECT * from word ORDER BY id DESC") // id가(primaryKey) 최신으로 추가된 순서대로 위에 보임
    fun getAll(): List<Word>

    @Query("SELECT * from word ORDER BY id DESC LIMIT 1") // 최신 Id 하나만 가져옴
    fun getLatestWord() : Word

    @Insert
    fun insert(word: Word)

    @Delete
    fun delete(word: Word)

    @Update
    fun update(word: Word)
}