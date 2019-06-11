package com.vitorprado.wptest

import androidx.annotation.VisibleForTesting
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
    var entryCategory = Category(-1, "All")
    var categories : List<Category>? = null

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
                        getCategories(it)
                    }
                },
                { /* we will ignore errors */ }
            )
            .also {
                compositeDisposable.add(it)
            }
    }

    private fun filterMoviesByCategory(category: Category) {
        contract.setupList(filteredMovies(category))
    }


    fun onChange(category: Category) {
        filterMoviesByCategory(category)
    }

    private fun filteredMovies(category: Category): List<Movie> {
        moviesList?.let {
            if(category.id == -1L) {
                return it
            }

            filteredList = it.filter { movie -> movie.category.name.contains(category.name) }
            return filteredList.orEmpty()
        }

        return emptyList()
    }

    private fun getCategories(movies: List<Movie>): List<Category> {
        val categoriesMap = movies.groupBy { movie -> movie.category }
        val categoriesArrayList = createCategoriesWithDefaultValue(categoriesMap)
        categories = categoriesArrayList
        return categories as List<Category>
    }

    private fun createCategoriesWithDefaultValue(categoriesMap: Map<Category, List<Movie>>): ArrayList<Category> {
        val categoriesArrayList = arrayListOf<Category>()
        categoriesArrayList.add(entryCategory)
        categoriesArrayList.addAll(categoriesMap.keys.toList())
        return categoriesArrayList
    }

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}