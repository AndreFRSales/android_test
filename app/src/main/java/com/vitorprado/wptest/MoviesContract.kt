package com.vitorprado.wptest

import com.vitorprado.wptest.values.Movie

interface MoviesContract {
    fun setupList(movies: List<Movie>)
}