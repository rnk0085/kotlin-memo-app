package com.rnk0085.android.memo.database.datasource

import com.rnk0085.android.memo.database.memo.Memo
import kotlinx.coroutines.flow.Flow

interface MemoDataSource {
    suspend fun insert(memo: Memo)

    fun getAllMemos(): Flow<List<Memo>>
}
