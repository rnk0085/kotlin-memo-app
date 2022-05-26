package com.rnk0085.android.memo.database.datasource

import com.rnk0085.android.memo.database.memo.MemoEntity
import com.rnk0085.android.memo.database.memo.MemoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoDataSource {
    override suspend fun insert(memoEntity: MemoEntity) = memoDao.insert(memoEntity)

    override fun getAllMemos(): Flow<List<MemoEntity>> = memoDao.getAllMemos()

}
