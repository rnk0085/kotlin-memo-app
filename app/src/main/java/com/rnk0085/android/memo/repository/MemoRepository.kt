package com.rnk0085.android.memo.repository

import com.rnk0085.android.memo.database.entity.MemoEntity
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun insert(memoTitle: String, memoContent: String)

    fun getAllMemos(): Flow<List<MemoEntity>>

    fun getMemo(id: Int): Flow<MemoEntity>
}
