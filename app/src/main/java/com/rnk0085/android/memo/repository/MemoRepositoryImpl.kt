package com.rnk0085.android.memo.repository

import com.rnk0085.android.memo.database.datasource.MemoDataSource
import com.rnk0085.android.memo.database.memo.Memo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoRepositoryImpl @Inject constructor(
    private val memoDataSource: MemoDataSource
) : MemoRepository {
    override suspend fun insert(memo: Memo) {
        TODO("Not yet implemented")
    }

    override fun getAllMemos(): Flow<List<Memo>> {
        TODO("Not yet implemented")
    }
}
