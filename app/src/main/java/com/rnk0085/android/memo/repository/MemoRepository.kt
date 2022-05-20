package com.rnk0085.android.memo.repository

import com.rnk0085.android.memo.database.memo.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun insert(memo: Memo)

    fun getAllMemos(): Flow<List<Memo>>
}
