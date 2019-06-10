package com.vitorprado.wptest.bindingadapters

import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.vitorprado.wptest.bindingadapters.SpinnerExtensions.setSpinnerItemSelectedListener
import com.vitorprado.wptest.bindingadapters.SpinnerExtensions.setSpinnerValue
import com.vitorprado.wptest.bindingadapters.SpinnerExtensions.setSpinnersEntries


@BindingAdapter("entries")
fun Spinner.setEntries(entries: List<Any>?) {
    setSpinnersEntries(entries)
}

@BindingAdapter("onItemSelected")
fun Spinner.setOnItemSelected(itemSelectedListener: SpinnerExtensions.ItemSelectedListener?) {
    setSpinnerItemSelectedListener(itemSelectedListener)
}

@BindingAdapter("newValue")
fun Spinner.setNewValue(newValue: Any?) {
    setSpinnerValue(newValue)
}
