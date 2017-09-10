package iammert.com.androidarchitecture.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import javax.inject.Inject

class MovieDetailViewModel @Inject
constructor(val movieRepository: MovieRepository) : ViewModel() {

    fun getMovie(id: Int): LiveData<MovieEntity> =
            movieRepository.getMovie(id)
}
