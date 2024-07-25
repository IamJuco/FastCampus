package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao() : WordDao

    // 데이터 베이스는 여러개 생길 필요없어서 싱글톤으로 생성
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase? {
            synchronized(AppDatabase::class.java) { // synchronized 데이터 베이스가 하나만 생성되도록 ( 단일 스레드 보장 )
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database.db" // 데이터 베이스 이름
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}