package com.vitorprado.wptest.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vitorprado.wptest.R
import com.vitorprado.wptest.databinding.CellMovieDetailBinding
import com.vitorprado.wptest.values.Movie

class MoviesAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.cell_movie_detail,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.modelBind = MoviePresenter(movies[position])
    }

    inner class ViewHolder(val binding: CellMovieDetailBinding) : RecyclerView.ViewHolder(binding.root)
}
