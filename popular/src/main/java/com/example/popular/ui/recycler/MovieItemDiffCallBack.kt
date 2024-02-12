package com.example.popular.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_data.model.MovieModel

object MovieItemDiffCallBack: DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}
