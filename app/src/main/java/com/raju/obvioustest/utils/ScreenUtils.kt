package com.raju.obvioustest.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager

class ScreenUtils {

    companion object{
        fun getScreenWidth(): Int {
            return Resources.getSystem().displayMetrics.widthPixels
        }

        fun getScreenHeight(): Int {
            return Resources.getSystem().displayMetrics.heightPixels
        }

        fun hideKeyboard(view: View, context: Context) {
            val `in` = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            `in`.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        fun convertDpToPx(marginInDp: Float): Int {
            val r: Resources = Resources.getSystem()
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                marginInDp,
                r.displayMetrics
            ).toInt()
        }

    }

}