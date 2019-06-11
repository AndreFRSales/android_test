package com.vitorprado.wptest.items

import com.vitorprado.wptest.values.Category
import com.vitorprado.wptest.values.Movie
import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.`should be`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object MoviHUePresenterTest : Spek( {

    describe("MoviePresenterTest") {

        context("with Fight Club movie") {
            val presenter = MoviePresenter(fightClubMovie())

            it("should have 5 stars") {
                presenter.stars `should be equal to` 5f
            }

            it("should show correct name and year") {
                presenter.title `should be equal to` "Fight Club (1999)"
            }

            it("should be the correct category") {
                presenter.category `should be equal to` "Drama"
            }

            it("should has url") {
                presenter.poster `should be equal to` "https://m.media-amazon.com/images/M/MV5BMjJmYTNkNmItYjYyZC00MGUxLWJhNWMtZDY4Nzc1MDAwMzU5XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,676,1000_AL_.jpg"
            }
        }
    }

})

fun fightClubMovie() = Movie(
    id = 1,
    title = "Fight Club",
    poster = "https://m.media-amazon.com/images/M/MV5BMjJmYTNkNmItYjYyZC00MGUxLWJhNWMtZDY4Nzc1MDAwMzU5XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SY1000_CR0,0,676,1000_AL_.jpg",
    stars = 5f,
    year = 1999,
    category = Category(1, "Drama")
)