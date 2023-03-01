package com.example.suraapppractice.general.extensions

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.suraapppractice.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Activity.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.showMessage(messageRes: Int) {
    Toast.makeText(this, messageRes, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceFragment(idContainer: Int, fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(idContainer, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}

val Double?.orZero get() = this ?: 0.0

fun Double.toCurrency(): String {
    val formatter = DecimalFormat("$ ###,###,###,##0.00 MXN")
    return formatter.format(this)
}

fun Fragment.showMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Double.toMovCurrency(sign: Char): String {
    val formatter = DecimalFormat("$sign $ ###,###,###,##0.00")
    return formatter.format(this)
}

fun getResourcesFromMov(type: String): Pair<Int, Int> {
    return if (type == "in") {
        val icon = R.drawable.sd_ic_in_movement
        val intColor = R.color.sura_demo_color_green
        Pair(icon, intColor)
    } else {
        val icon = R.drawable.sd_ic_out_movement
        val intColor = R.color.sura_demo_color_red
        Pair(icon, intColor)
    }
}

fun Long.toFormatDate(): String {
    val formatter = SimpleDateFormat("dd 'de' MMMM yyyy", Locale.getDefault())
    return formatter.format(this)
}

fun View.show(shouldShow: Boolean){
    val visibility = if (shouldShow){
        View.VISIBLE
    } else {
        View.GONE
    }
    this.visibility = visibility
}