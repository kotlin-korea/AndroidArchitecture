package iammert.com.androidarchitecture.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import iammert.com.androidarchitecture.data.local.entity.MovieEntity
import iammert.com.androidarchitecture.databinding.ItemMovieListBinding
import iammert.com.androidarchitecture.ui.BaseAdapter
import java.lang.NullPointerException

class MovieListAdapter(val movieListCallback: MovieListCallback) :
        BaseAdapter<MovieListAdapter.MovieViewHolder, MovieEntity>() {

    private var movieEntities: List<MovieEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.create(LayoutInflater.from(parent.context), parent, movieListCallback)
    }

    override fun onBindViewHolder(viewHolder: MovieViewHolder, i: Int) {
        movieEntities ?: throw NullPointerException()
        viewHolder.onBind(movieEntities!![i])
    }

    override fun getItemCount(): Int {
        return movieEntities?.size ?: 0
    }

    override fun setData(data: List<MovieEntity>) {
        movieEntities = data
        notifyDataSetChanged()
    }

    class MovieViewHolder(private val binding: ItemMovieListBinding,
                          val callback: MovieListCallback) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                callback.onMovieClicked(
                        binding.movie ?: throw IllegalStateException(),
                        binding.imageViewCover)
            }
        }

        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup,
                       callback: MovieListCallback): MovieViewHolder {
                val binding = ItemMovieListBinding.inflate(inflater, parent, false)
                return MovieViewHolder(binding, callback)
            }
        }

        fun onBind(movieEntity: MovieEntity) {
            binding.movie = movieEntity
            binding.executePendingBindings()
        }
    }
}