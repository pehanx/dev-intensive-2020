package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(){
	val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	val view = getWindow().getDecorView().findViewById(android.R.id.content) as View
	imm.hideSoftInputFromWindow(view.windowToken, 0)
	imm.showSoftInput(view, 0)
}