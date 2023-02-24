package com.example.suraapppractice.general.extensions

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.DecimalFormat

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