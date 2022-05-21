package com.rnk0085.android.memo.database.memo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rnk0085.android.memo.utils.DateConverters

@Database(entities = [Memo::class], version = 1)
@TypeConverters(DateConverters::class)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}
