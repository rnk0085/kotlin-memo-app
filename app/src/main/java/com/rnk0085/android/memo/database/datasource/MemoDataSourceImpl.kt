package com.rnk0085.android.memo.database.datasource

import com.rnk0085.android.memo.database.entity.MemoEntity
import com.rnk0085.android.memo.database.dao.MemoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoDataSource {
    override suspend fun insert(memoEntity: MemoEntity) = memoDao.insert(memoEntity)

    override fun getAllMemos(): Flow<List<MemoEntity>> = memoDao.getAllMemos()

    override fun getMemo(id: Int): Flow<MemoEntity> = memoDao.getMemo(id)
}
