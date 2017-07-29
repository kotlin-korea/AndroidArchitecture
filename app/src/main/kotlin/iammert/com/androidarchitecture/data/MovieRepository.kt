package iammert.com.androidarchitecture.data

import android.arch.lifecycle.LiveData
import iammert.com.androidarchitecture.data.local.dao.MovieDao
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.data.remote.MovieDBService
import iammert.com.androidarchitecture.data.remote.model.MoviesResponse
import retrofit2.Call
import javax.inject.Inject

class MovieRepository
@Inject constructor(private val movieDao: MovieDao,
                    private val movieDBService: MovieDBService) {

    fun loadPopularMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>() {
            override fun saveCallResult(item: MoviesResponse) {
                movieDao.saveMovies(item.results ?: emptyList<MovieEntity>())
            }

            override fun loadFromDb(): LiveData<List<MovieEntity>> {
                return movieDao.loadMovies()
            }

            override fun createCall(): Call<MoviesResponse> {
                return movieDBService.loadMovies()
            }

        }.asLiveData
    }

    fun getMovie(id: Int): LiveData<MovieEntity> = movieDao.getMovie(id)

}