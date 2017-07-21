package iammert.com.androidarchitecture.data

import android.arch.lifecycle.LiveData

import javax.inject.Inject

import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.data.remote.MovieDBService
import iammert.com.androidarchitecture.data.remote.model.MoviesResponse
import retrofit2.Call

/**
 * Created by mertsimsek on 19/05/2017.
 */

class MovieRepository
@Inject constructor(private val movieDao: MovieDao,
                    private val movieDBService: MovieDBService) {

    fun loadPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {

            override fun saveCallResult(item: MoviesResponse) {
                movieDao.saveMovies(item.results ?: emptyList())
            }

            override fun loadFromDb(): LiveData<List<MovieEntity>> =
                    movieDao.loadMovies()

            override fun createCall(): Call<MoviesResponse> =
                    movieDBService.loadMovies()

        }.asLiveData
    }

    fun getMovie(id: Int): LiveData<MovieEntity> = movieDao.getMovie(id)
}
