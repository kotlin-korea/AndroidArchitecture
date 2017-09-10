package iammert.com.androidarchitecture.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.databinding.ItemMovieListBinding
import iammert.com.androidarchitecture.ui.BaseAdapter

/**
 * Created by mertsimsek on 20/05/2017.
 */

class MovieListAdapter(private val movieListCallback: MovieListCallback) : BaseAdapter<MovieListAdapter.MovieViewHolder, MovieEntity>() {

    private var movieEntities: List<MovieEntity> = emptyList()

    override fun setData(data: List<MovieEntity>) {
        this.movieEntities = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MovieViewHolder =
            MovieViewHolder.create(LayoutInflater.from(viewGroup.context), viewGroup, movieListCallback)

    override fun onBindViewHolder(viewHolder: MovieViewHolder, i: Int) {
        viewHolder.onBind(movieEntities[i])
    }

    override fun getItemCount(): Int = movieEntities.size

    class MovieViewHolder(var binding: ItemMovieListBinding, callback: MovieListCallback) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { _ ->
                callback.onMovieClicked(
                        binding.movie ?: throw IllegalStateException(),
                        binding.imageViewCover)
            }
        }

        fun onBind(movieEntity: MovieEntity) {
            binding.movie = movieEntity
            binding.executePendingBindings()
        }

        companion object {

            fun create(inflater: LayoutInflater, parent: ViewGroup, callback: MovieListCallback): MovieViewHolder {
                val itemMovieListBinding = ItemMovieListBinding.inflate(inflater, parent, false)
                return MovieViewHolder(itemMovieListBinding, callback)
            }
        }
    }
}
