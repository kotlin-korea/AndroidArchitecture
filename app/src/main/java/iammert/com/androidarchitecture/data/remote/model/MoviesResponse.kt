package iammert.com.androidarchitecture.data.remote.model


import iammert.com.androidarchitecture.data.local.entity.MovieEntity

/**
 * Created by mertsimsek on 19/05/2017.
 */

data class MoviesResponse(val results: List<MovieEntity>? = null)
