package com.rnk0085.android.memo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rnk0085.android.memo.database.dao.MemoDao
import com.rnk0085.android.memo.database.entity.MemoEntity
import com.rnk0085.android.memo.utils.DateConverters

@Database(entities = [MemoEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}
