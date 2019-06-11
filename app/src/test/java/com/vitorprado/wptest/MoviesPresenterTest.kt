package com.vitorprado.wptest

import com.nhaarman.mockitokotlin2.*
import com.vitorprado.wptest.actions.GetMovies
import com.vitorprado.wptest.values.Category
import com.vitorprado.wptest.values.Movie
import io.reactivex.Single
import org.amshove.kluent.`should be equal to`
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object MoviesPresenterTest : Spek({

    val dramaCategory = Category(1, "Drama")
    val sciFiCategory = Category(2, "Sci-fi")
    val defaulCategory = Category(-1, "All")
    val categoryList = listOf(defaulCategory, dramaCategory, sciFiCategory)

    describe("MoviesPresenterTest") {

        context("with failed action") {
            val contract = mock<MoviesContract>()
            val getMovies = mock<GetMovies>()

            `when`(getMovies.execute()).thenReturn(Single.error(Exception()))

            val presenter = MoviesPresenter(contract, getMovies)

            it("should not load anything") {
                verify(contract, atMost(0)).setupList((ArgumentMatchers.anyList()))
            }
        }

        context("with one category") {
            val contract = mock<MoviesContract>()
            val getMovies = mock<GetMovies>()
            `when`(getMovies.execute()).thenReturn(Single.just(listOf(Movie(0, "", "", 0f, 0, dramaCategory))))

            val presenter = MoviesPresenter(contract, getMovies)
            it("should show one category") {
                verify(contract, atLeastOnce()).setupList(ArgumentMatchers.anyList())
                presenter.categories!!.size `should be equal to` 2
                assertEquals(presenter.categories!!, listOf(defaulCategory, dramaCategory))
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
                presenter.categories!!.size `should be equal to` 3
                assertEquals(presenter.categories!!, categoryList)
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
                presenter.categories!!.size `should be equal to` 3
                assertEquals(presenter.categories!!, categoryList)
            }
        }
    }
})