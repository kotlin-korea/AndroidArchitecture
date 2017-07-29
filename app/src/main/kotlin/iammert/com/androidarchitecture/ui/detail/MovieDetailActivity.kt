package iammert.com.androidarchitecture.ui.detail

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

import javax.inject.Inject

import dagger.android.AndroidInjection
import iammert.com.androidarchitecture.R
import iammert.com.androidarchitecture.databinding.ActivityMovieDetailBinding

/**
 * Created by mertsimsek on 19/05/2017.
 */
class MovieDetailActivity : AppCompatActivity(), LifecycleRegistryOwner {

    companion object {

        private val KEY_MOVIE_ID = "key_movie_id"

        fun newIntent(context: Context, movieId: Int): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(KEY_MOVIE_ID, movieId)
            }
        }
    }

    private var lifecycleRegistry = LifecycleRegistry(this)

    lateinit var binding: ActivityMovieDetailBinding

    @Inject
    lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMovieDetailBinding>(
                this, R.layout.activity_movie_detail)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val movieId = intent.getIntExtra(KEY_MOVIE_ID, 0)
        movieDetailViewModel.getMovie(movieId)
                .observe(this, Observer { movieEntity ->
                    binding.movie = movieEntity
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> ActivityCompat.finishAfterTransition(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

}
