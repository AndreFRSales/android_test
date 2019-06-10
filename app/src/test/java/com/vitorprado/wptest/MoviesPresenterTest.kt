package com.vitorprado.wptest

import com.nhaarman.mockitokotlin2.*
import com.vitorprado.wptest.actions.GetMovies
import com.vitorprado.wptest.values.Category
import com.vitorprado.wptest.values.Movie
import io.reactivex.Single
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object MoviesPresenterTest : Spek({

    val dramaCategory = Category(1, "Drama")
    val sciFiCategory = Category(2, "Sci-fi")
    val categoryList = listOf(dramaCategory, sciFiCategory)

    describe("MoviesPresenterTest") {

        context("with failed action") {
            val contract = mock<MoviesContract>()
            val getMovies = mock<GetMovies>()

            `when`(getMovies.execute()).thenReturn(Single.error(Exception()))

            val presenter = MoviesPresenter(contract, getMovies)

            it("should not load anything") {
                verify(contract, atMost(0)).setupList((ArgumentMatchers.anyList()))
                verify(contract, atMost(0)).setupCategories(argThat { size == 0 })
            }
        }

        context("with one category") {
            val contract = mock<MoviesContract>()
            val getMovies = mock<GetMovies>()
            `when`(getMovies.execute()).thenReturn(Single.just(listOf(Movie(0, "", "", 0f, 0, dramaCategory))))

            val presenter = MoviesPresenter(contract, getMovies)
            it("should show one category") {
                verify(contract, atLeastOnce()).setupList(ArgumentMatchers.anyList())
                verify(contract, atLeastOnce()).setupCategories(argThat { size == 1 && contains(dramaCategory) })
            }
        }

        context("with two different categories") {
            val contract = mock<MoviesContract>()
            val listMovies = listOf(
                Movie(0, "", "", 0f, 0, dramaCategory),
                Movie(1, "", "", 0f, 0, sciFiCategory)
            )
            val getMovies = mock<GetMovies>()
            `when`(getMovies.execute()).thenReturn(Single.just(listMovies))

            val presenter = MoviesPresenter(contract, getMovies)
            it("should has two categories when filtered") {
                verify(contract, atLeastOnce()).setupCategories(argThat {
                    size == 2 && containsAll<Category>(
                        categoryList
                    )
                })
            }
        }

        context("with movies which contains the same category") {
            val contract = mock<MoviesContract>()

            val listMovies = listOf(
                Movie(0, "", "", 0f, 0, dramaCategory),
                Movie(1, "", "", 0f, 0, sciFiCategory), Movie(2, "", "", 0f, 0, dramaCategory)
            )
            val getMovies = mock<GetMovies>()
            `when`(getMovies.execute()).thenReturn(Single.just(listMovies))

            val presenter = MoviesPresenter(contract, getMovies)

            it("should has two differents  categories") {
                verify(contract, atLeastOnce()).setupCategories(argThat { size == 2 && containsAll(categoryList) })
            }
        }
    }
})