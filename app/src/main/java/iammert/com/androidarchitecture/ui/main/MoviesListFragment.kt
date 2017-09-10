package iammert.com.androidarchitecture.ui.main

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.databinding.FragmentMovieListBinding
import iammert.com.androidarchitecture.ui.detail.MovieDetailActivity
import javax.inject.Inject

class MovieListFragment : LifecycleFragment(), MovieListCallback {

    @Inject
    lateinit var movieListViewModel: MovieListViewModel

    lateinit var binding: FragmentMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false).apply {
            recyclerView.layoutManager = GridLayoutManager(activity, 2)
            recyclerView.adapter = MovieListAdapter(this@MovieListFragment)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieListViewModel
                .popularMovies
                .observe(this, Observer { listResource -> binding.resource = listResource })
    }

    override fun onMovieClicked(movieEntity: MovieEntity, sharedView: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, sharedView, getString(R.string.shared_image))
        startActivity(MovieDetailActivity.newIntent(activity, movieEntity.id), options.toBundle())
    }

    companion object {

        fun newInstance(): MovieListFragment = MovieListFragment().apply { arguments = Bundle() }
    }
}
