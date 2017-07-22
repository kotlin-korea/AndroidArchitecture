package iammert.com.androidarchitecture.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

@Database(entities = arrayOf(MovieEntity::class), version = 2)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}