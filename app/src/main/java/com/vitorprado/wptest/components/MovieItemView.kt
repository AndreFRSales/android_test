package com.vitorprado.wptest.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.vitorprado.wptest.R
import com.vitorprado.wptest.databinding.ItemMovieBinding
import com.vitorprado.wptest.items.MoviePresenter


class MovieItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: ItemMovieBinding

    init {
        val dataBinding = DataBindingUtil.inflate<ItemMovieBinding>(LayoutInflater.from(context),
            R.layout.item_movie,
            this,
            true)
            binding = dataBinding
    }

    fun setupComponent(presenter: MoviePresenter) {
            binding.presenter = presenter
    }
}