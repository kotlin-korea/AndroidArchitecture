package iammert.com.androidarchitecture.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import iammert.com.androidarchitecture.data.local.entity.MovieEntity

/**
 * Created by mertsimsek on 19/05/2017.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun loadMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getMovie(id: Int): LiveData<MovieEntity>

}
