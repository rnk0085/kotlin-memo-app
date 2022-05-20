package com.rnk0085.android.memo.di

import com.rnk0085.android.memo.repository.MemoRepository
import com.rnk0085.android.memo.repository.MemoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMemoRepository(
        memoRepositoryImpl: MemoRepositoryImpl
    ) : MemoRepository
}
