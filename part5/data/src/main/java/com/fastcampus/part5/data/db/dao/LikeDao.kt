package com.fastcampus.part5.data.db.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fastcampus.part5.data.db.entity.LikeProductEntity

@Dao
interface LikeDao {
    @Query("SELECT * FROM `like`")
    suspend fun getAll(): List<LikeProductEntity>

    // onConflict = Insert 했을때 문제가 발생한것에 대한 처리
    // OnConflictStrategy = 같은값이 들어왔을때 덜어쓰기 하겠다는 뜻
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LikeProductEntity)

    @Query("DELETE FROM `like` WHERE productId=:id")
    suspend fun delete(id: String)
}