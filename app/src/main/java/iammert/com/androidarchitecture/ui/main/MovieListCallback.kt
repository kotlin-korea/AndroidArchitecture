package iammert.com.androidarchitecture.ui.main

import android.view.View
import iammert.com.androidarchitecture.data.local.entity.MovieEntity

interface MovieListCallback {
    fun onMovieClicked(movieEntity: MovieEntity, sharedView: View)
}
