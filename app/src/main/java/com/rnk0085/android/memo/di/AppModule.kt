package com.rnk0085.android.memo.di

import android.content.Context
import androidx.room.Room
import com.rnk0085.android.memo.database.dao.MemoDao
import com.rnk0085.android.memo.database.memo.AppDatabase
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
    ) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "memo_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMemoDao(
        database: AppDatabase
    ) : MemoDao = database.memoDao()
}
