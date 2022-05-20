package com.rnk0085.android.memo.database.memo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memo: Memo)

    @Query("SELECT * FROM memo ORDER BY updated_at ASC")
    fun getAllMemos(): Flow<List<Memo>>
}
