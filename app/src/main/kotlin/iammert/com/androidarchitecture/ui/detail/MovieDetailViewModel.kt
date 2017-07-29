package iammert.com.androidarchitecture.ui.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import javax.inject.Inject

import iammert.com.androidarchitecture.data.MovieRepository
import iammert.com.androidarchitecture.data.Resource
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

/**
 * Created by mertsimsek on 21/05/2017.
 */

class MovieDetailViewModel
@Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val movieDetail = MutableLiveData<Resource<MovieEntity>>()

    fun getMovie(id: Int): LiveData<MovieEntity> = movieRepository.getMovie(id)

}
