package com.vitorprado.wptest

import androidx.recyclerview.widget.DividerItemDecoration
import com.vitorprado.wptest.values.Category
import com.vitorprado.wptest.values.Movie

interface MoviesContract {
    fun setupList(movies: List<Movie>)
    fun setupCategories(categories: List<Category>)
}