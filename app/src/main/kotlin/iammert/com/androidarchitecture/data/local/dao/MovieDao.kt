package iammert.com.androidarchitecture.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun loadMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM movies WHERE id=:id")
    fun getMovie(id: Int): LiveData<MovieEntity>

}