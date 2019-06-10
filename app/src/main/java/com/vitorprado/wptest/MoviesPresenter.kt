package com.vitorprado.wptest

import com.vitorprado.wptest.actions.GetMovies
import com.vitorprado.wptest.values.Category
import com.vitorprado.wptest.values.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviesPresenter(
    private val contract: MoviesContract,
    private val getMovies: GetMovies = GetMovies()
) {

    private val compositeDisposable by lazy { CompositeDisposable() }
    private var moviesList: List<Movie>? = null
    private var filteredList: List<Movie>? = null

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        getMovies.execute()
            .subscribe(
                {
                    moviesList = it
                    with(contract) {
                        setupList(it)
                        setupCategories(getCategories(it))
                    }
                },
                { /* we will ignore errors */ }
            )
            .also {
                compositeDisposable.add(it)
            }
    }

    fun filterMoviesByCategory(category: Category) {
        contract.setupList(filteredMovies(category))
    }

    fun clearFilter() {
        filteredList = null
    }

    private fun filteredMovies(category: Category): List<Movie> {
        filteredList =  moviesList?.filter { it.category.name.contains(category.name) }
        return filteredList.orEmpty()
    }

    private fun getCategories(movies: List<Movie>): List<Category> {
        val categoriesMap = movies.groupBy { movie -> movie.category }
        return categoriesMap.keys.toList()
    }

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}