package com.rnk0085.android.memo.database.dao

import androidx.room.*
import com.rnk0085.android.memo.database.memo.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memoEntity: MemoEntity)

    @Query("SELECT * FROM memo ORDER BY updated_at DESC")
    fun getAllMemos(): Flow<List<MemoEntity>>
}
