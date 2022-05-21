package com.rnk0085.android.memo.database.datasource

import com.rnk0085.android.memo.database.memo.Memo
import com.rnk0085.android.memo.database.memo.MemoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoDataSource {
    override suspend fun insert(memo: Memo) = memoDao.insert(memo)

    override fun getAllMemos(): Flow<List<Memo>> = memoDao.getAllMemos()

}
