package com.vitorprado.wptest.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.vitorprado.wptest.R
import com.vitorprado.wptest.databinding.SpinnerCategoryItemBinding
import com.vitorprado.wptest.values.Category

class CategoriesAdapter(context: Context, private val categories: List<Category>) : ArrayAdapter<Category>(context, R.layout.spinner_category_item) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return bindView(position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return bindView(position)
    }

    private fun bindView(position: Int): View {
        val inflater = LayoutInflater.from(context)
        val binding = SpinnerCategoryItemBinding.inflate(inflater, null, false)

        binding.categoryPresenter = CategoriesPresenter(categories[position])
        return binding.root
    }

    override fun getCount(): Int = categories.size
    override fun getItem(position: Int): Category? = categories[position]
    override fun getItemId(position: Int) = position.toLong()

}