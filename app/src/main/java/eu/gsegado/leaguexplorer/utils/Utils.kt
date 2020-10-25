package eu.gsegado.leaguexplorer.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

object Utils {

    fun hideSoftKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
