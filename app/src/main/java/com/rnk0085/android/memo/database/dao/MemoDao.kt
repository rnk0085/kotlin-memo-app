package com.rnk0085.android.memo.database.dao

import androidx.room.*
import com.rnk0085.android.memo.database.entity.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memoEntity: MemoEntity)

    @Query("SELECT * FROM memo ORDER BY updated_at DESC")
    fun getAllMemos(): Flow<List<MemoEntity>>

    @Query("SELECT * FROM memo WHERE id = :id")
    fun getMemo(id: Int): Flow<MemoEntity>

    @Delete
    suspend fun delete(memoEntity: MemoEntity)
}
