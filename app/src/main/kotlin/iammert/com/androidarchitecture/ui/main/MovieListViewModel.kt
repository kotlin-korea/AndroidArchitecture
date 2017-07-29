package iammert.com.androidarchitecture.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import javax.inject.Inject

import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.data.Resource
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

/**
 * Created by mertsimsek on 19/05/2017.
 */

class MovieListViewModel
@Inject
constructor(movieRepository: MovieRepository) : ViewModel() {

    val popularMovies: LiveData<Resource<List<MovieEntity>>> =
            movieRepository.loadPopularMovies()

}
