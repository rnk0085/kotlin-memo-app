package com.rnk0085.android.memo.repository

import com.rnk0085.android.memo.database.memo.MemoEntity
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun insert(memoTitle: String, memoContent: String)

    fun getAllMemos(): Flow<List<MemoEntity>>
}
