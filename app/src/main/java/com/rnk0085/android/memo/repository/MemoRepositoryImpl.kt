package com.rnk0085.android.memo.repository

import com.rnk0085.android.memo.database.datasource.MemoDataSource
import com.rnk0085.android.memo.database.entity.MemoEntity
import com.rnk0085.android.memo.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoRepositoryImpl @Inject constructor(
    private val memoDataSource: MemoDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MemoRepository {
    override suspend fun insert(memoTitle: String, memoContent: String) = withContext(ioDispatcher) {
        val date = Date()

        val title = if (memoTitle == "") "タイトル無し" else memoTitle

        val memoEntity = MemoEntity(
            title = title,
            content = memoContent,
            createdAt = date,
            updatedAt = date
        )

        memoDataSource.insert(memoEntity)
    }

    override fun getAllMemos(): Flow<List<MemoEntity>> =
        memoDataSource.getAllMemos()
            .flowOn(ioDispatcher)

    override fun getMemo(id: Int): Flow<MemoEntity> {
        TODO("Not yet implemented")
    }
}
