package com.example.favorites.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.core_data.model.MovieModel
import com.example.favorites.databinding.ListItemBinding

class FavoriteMoviesListAdapter: ListAdapter<MovieModel, MoviesHolder>(
    MovieItemDiffCallBack
) {
    var onItemClickListener: ((MovieModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviesHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: MoviesHolder, position: Int) {
        val movieItem = getItem(position)
        val binding = viewHolder.binding
        val context = viewHolder.itemView.context

        if (movieItem.isFavorite) {
            binding.buttonIsFavorite.visibility = View.VISIBLE
        }

        Glide.with(context)
            .load(movieItem.imagePoster)
            .into(binding.imageMoviePoster)

        binding.movieTitle.text = movieItem.title
        binding.movieGenreTitle.text = String.format(
            context.getString(com.example.core_ui.R.string.movie_genre_sample_text), movieItem.genre, movieItem.year
        )

        binding.movieListItem.setOnClickListener {
            onItemClickListener?.invoke(movieItem)
        }

    }
}
