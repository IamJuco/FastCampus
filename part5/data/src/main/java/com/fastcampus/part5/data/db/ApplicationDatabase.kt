package com.fastcampus.part5.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fastcampus.part5.data.db.dao.BasketDao
import com.fastcampus.part5.data.db.dao.LikeDao
import com.fastcampus.part5.data.db.dao.PurchaseDao
import com.fastcampus.part5.data.db.entity.BasketProductEntity
import com.fastcampus.part5.data.db.entity.LikeProductEntity
import com.fastcampus.part5.data.db.entity.PurchaseProductEntity

@Database(
    entities = [
        PurchaseProductEntity::class,
        LikeProductEntity::class,
        BasketProductEntity::class
    ],
    version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "applicationDatabase.db"
    }

    abstract fun purchaseDao(): PurchaseDao
    abstract fun basketDao(): BasketDao
    abstract fun likeDao(): LikeDao
}