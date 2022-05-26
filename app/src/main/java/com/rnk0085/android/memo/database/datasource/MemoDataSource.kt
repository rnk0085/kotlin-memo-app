package com.rnk0085.android.memo.database.datasource

import com.rnk0085.android.memo.database.memo.MemoEntity
import kotlinx.coroutines.flow.Flow

interface MemoDataSource {
    suspend fun insert(memoEntity: MemoEntity)

    fun getAllMemos(): Flow<List<MemoEntity>>
}
