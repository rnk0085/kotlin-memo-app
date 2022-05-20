package com.rnk0085.android.memo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rnk0085.android.memo.database.memo.Memo
import com.rnk0085.android.memo.database.memo.MemoDao

@Database(entities = [Memo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}
