package com.vitorprado.wptest.items

import com.vitorprado.wptest.values.Category

class CategoriesPresenter(private val category: Category) {
    val name: String
        get() = category.name

    val id: Long
        get() = category.id
}