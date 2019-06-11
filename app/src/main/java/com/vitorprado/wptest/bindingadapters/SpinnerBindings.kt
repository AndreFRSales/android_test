package com.vitorprado.wptest.bindingadapters

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.vitorprado.wptest.items.CategoriesAdapter
import com.vitorprado.wptest.values.Category

object SpinnerBindings {

    @BindingAdapter(value = ["categories", "selectedCategory", "selectedCategoryAttrChanged"], requireAll = false)
    @JvmStatic
    fun Spinner.setProjects(categories: List<Category>, selectedProject: Category, listener: OnItemSelected) {
        adapter = CategoriesAdapter(context, categories)
        setCurrentSelection(selectedProject)
        setSpinnerListener(listener)
    }

    private fun Spinner.setSpinnerListener(listener: OnItemSelected) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                listener.onItemSelected(adapter.getItem(position) as Category)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) = run { }
        }
    }

    private fun Spinner.setCurrentSelection(selectedItem: Category): Boolean {
        for (index in 0 until adapter.count) {
            if (getItemAtPosition(index) as Category == selectedItem) {
                setSelection(index)
                return true
            }
        }
        return false
    }

    interface OnItemSelected {
        fun onItemSelected(category: Category)
    }
}