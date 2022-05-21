package com.rnk0085.android.memo.di

import com.rnk0085.android.memo.database.datasource.MemoDataSource
import com.rnk0085.android.memo.database.datasource.MemoDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindMemoDataSource(
        memoDataSourceImpl: MemoDataSourceImpl
    ) : MemoDataSource
}
