package com.rnk0085.android.memo.database.memo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memoEntity: MemoEntity)

    @Query("SELECT * FROM memo ORDER BY updated_at DESC")
    fun getAllMemos(): Flow<List<MemoEntity>>
}
