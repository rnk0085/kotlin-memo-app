package com.rnk0085.android.memo.di

import android.content.Context
import androidx.room.Room
import com.rnk0085.android.memo.database.memo.MemoDao
import com.rnk0085.android.memo.database.memo.MemoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMemoDatabase(
        @ApplicationContext context: Context
    ) : MemoDatabase {
        return Room.databaseBuilder(
            context,
            MemoDatabase::class.java,
            "memo_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMemoDao(
        database: MemoDatabase
    ) : MemoDao = database.memoDao()
}
